package exercise;

import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        Set<String> keySet = storage.toMap().keySet();
         for (String key : keySet) {
            String value = storage.get(key, "default");
            storage.unset(key);
            storage.set(value, key);
        }
    }
}
// END
