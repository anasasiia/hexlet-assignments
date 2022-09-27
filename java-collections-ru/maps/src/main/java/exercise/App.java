package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
class App {
    public static Map<String, Integer> getWordCount(String sentence) {
        Map<String, Integer> wordsMap = new HashMap<>();
        String[] words = sentence.split(" ");

        if (sentence.equals("")) {
            return wordsMap;
        }

        for (var word: words) {
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }
        return wordsMap;
    }

    public static String toString(Map<String, Integer> wordsMap) {
        if (wordsMap.isEmpty()) {
            return "{}";
        }

        var result = new StringBuilder("{\n");
        String wordStringType;

        for (Map.Entry<String, Integer> word: wordsMap.entrySet()) {
            wordStringType = "  " + word.getKey() + ": " + word.getValue() + "\n";
            result.append(wordStringType);
        }
        result.append("}");
        return result.toString();
    }
}
//END
