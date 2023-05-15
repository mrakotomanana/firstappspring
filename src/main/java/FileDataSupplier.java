import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Supplier;

public class FileDataSupplier implements Supplier<String> {

    public String data;

    public void readData() throws IOException {
        StringBuilder lines = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader("data.txt"));
        String line = br.readLine();
        while (line != null) {
            lines.append(line);
            line = br.readLine();
        }
        data = lines.toString();
    }


    public static void main(String[] args) throws IOException {
        FileDataSupplier dataSupplier = new FileDataSupplier();
        dataSupplier.readData();
        System.out.println(dataSupplier.get());
    }

    @Override
    public String get() {
        return data;
    }
}
