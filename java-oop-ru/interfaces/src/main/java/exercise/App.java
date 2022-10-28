package exercise;

import java.util.List;


// BEGIN
public class App {
    public static List<String> buildAppartmentsList(List<Home> appartments, int elementsCount) {
        return appartments.stream()
                .sorted(Home::compareTo)
                .limit(elementsCount)
                .map(Home::toString).toList();

    }
}
// END
