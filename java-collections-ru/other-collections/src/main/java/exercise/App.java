package exercise;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


// BEGIN
class App {
    public static Map<String, Object> genDiff(Map<String, Object> first, Map<String, Object> second) {
        Set<String> keys = Stream.concat(first.entrySet().stream(), second.entrySet().stream())
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        Map<String, Object> result = new LinkedHashMap<>();
        for (var key: keys) {
                if (first.containsKey(key) && second.containsKey(key)) {
                    if (first.get(key).equals(second.get(key))) {
                        result.put(key, "unchanged");
                    } else {
                        result.put(key, "changed");
                    }
                } else if (first.containsKey(key) && !second.containsKey(key)) {
                    result.put(key, "deleted");
                } else {
                    result.put(key, "added");
                }
            }
        return result;

    }
}
//END
