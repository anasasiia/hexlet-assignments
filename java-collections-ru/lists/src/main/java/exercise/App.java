package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {
    public static boolean scrabble(String charArray, String word) {
        List<String> list = new ArrayList<>();

        for (var i = 0; i < charArray.length(); i++) {
            String element = String.valueOf(charArray.charAt(i));
            list.add(element);
        }

        String wordToLowerCase = word.toLowerCase();

        for (var j = 0; j < wordToLowerCase.length(); j++) {
            String letter = String.valueOf(wordToLowerCase.charAt(j));
            if (list.contains(letter)) {
                list.remove(letter);
            } else {
                return false;
            }
        }
        return true;
    }
}
//END
