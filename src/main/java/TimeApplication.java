import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.time.LocalTime;

public class TimeApplication {
    @Bean
    @Scope("prototype")
    public LocalTime maintenant() {
        return LocalTime.now();
    }

    public static void main(String[] args) throws InterruptedException {
        try (AnnotationConfigApplicationContext appCtx
                     = new AnnotationConfigApplicationContext(TimeApplication.class)) {
            int index = 0;
            while (index < 10) {
                Thread.sleep(1000);
                LocalTime time = appCtx.getBean("maintenant", LocalTime.class);
                System.out.println("time to start : " + time);
                index++;
            }
        }
    }
}
