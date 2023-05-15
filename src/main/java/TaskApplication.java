import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class TaskApplication {


    @Bean(initMethod = "readData")
    public Supplier<String> filDataSupplier() {
        return new FileDataSupplier();
    }

    @Bean
    public Supplier<String> dataSupplier() {
        return new HardcodedSupplier();
    }

    @Bean
    public Runnable task(Supplier<String> dataSupplier) {
        return new WriterService(dataSupplier);
    }

    @Bean
    public List<Runnable> tasks(List<Supplier<String>> dataSuppliers) {
        List<Runnable> tasks = new ArrayList<>();
        for (Supplier<String> supplier : dataSuppliers) {
            tasks.add(new WriterService(supplier));
        }
        return tasks;
    }

    @Bean(destroyMethod = "shutdown")
    public Executor executor(List<Runnable> tasks) {
        Executor executor = Executors.newCachedThreadPool();
        for (Runnable task : tasks) {
            executor.execute(task);
        }
        return executor;
    }

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskApplication.class)) {
            context.getBean(Runnable.class).run();
        }
    }
}
