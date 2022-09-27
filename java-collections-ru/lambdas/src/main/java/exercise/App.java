package exercise;

import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
class App {
    public static String[][] enlargeArrayImage(String[][] image) {
        return Arrays.stream(image)
                .map(array1 -> Arrays.stream(array1)
                        .flatMap(element -> Stream.of(element, element))
                        .toArray(String[]::new))
                .flatMap(array1 -> Stream.of(array1, array1))
                .toArray(String[][]::new);
    }
}
// END
