import java.util.function.Supplier;

public class WriterService implements Runnable {
    private Supplier<String> supplier;

    public WriterService(Supplier<String> supplier) {
        this.supplier = supplier;
    }

    @Override
    public void run() {
        System.out.println(supplier.get());
    }
}
