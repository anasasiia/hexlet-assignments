package exercise;

import java.util.List;

// BEGIN
class App {
    public static List<String> FREE_DOMAINS = List.of("gmail.com", "yandex.ru", "hotmail.com");

    public static long getCountOfFreeEmails(List<String> emails) {
        return emails.stream()
                .map(email -> email.split("@")[1])
                .filter(domain -> FREE_DOMAINS.contains(domain))
                .count();

    }
}
// END
