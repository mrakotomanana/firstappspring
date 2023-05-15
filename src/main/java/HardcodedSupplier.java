import java.util.function.Supplier;

public class HardcodedSupplier implements Supplier<String> {
    @Override
    public String get() {
        return "Task application -- hello world";
    }
}
