package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// BEGIN
class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> where) {
        List<Map<String, String>> bookList = new ArrayList<>();
        int whereMapSize = where.size();
        int matchesCount;

        for (Map<String, String> book: books) {
            matchesCount = 0;

            for (String key: where.keySet()) {
                if (book.get(key).equals(where.get(key))) {
                    matchesCount++;
                }
            }

            if (matchesCount == whereMapSize) {
                bookList.add(book);
            }
        }
        return bookList;
    }
}
//END
