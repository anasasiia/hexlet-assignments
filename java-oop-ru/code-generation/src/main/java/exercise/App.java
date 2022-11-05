package exercise;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

// BEGIN
public class App {
    public static void save(Path filepath, Car car) {
        String content = car.serialize();
        try {
            Files.write(filepath, content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car extract(Path filepath) {
        String content;
        try {
            content = Files.readString(filepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Car.unserialize(content);
    }
}
// END
