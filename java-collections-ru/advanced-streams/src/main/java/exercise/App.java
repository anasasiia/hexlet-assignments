package exercise;

import java.util.Arrays;
import java.util.stream.Collectors;

// BEGIN
class App {
    public static String getForwardedVariables(String content) {
        String[] arrayContent = content.split("\n");
        return Arrays.stream(arrayContent)
                .filter(realContent -> realContent.startsWith("environment="))
                .map(variable -> variable.replaceAll("environment=", "").replaceAll("\"", "").split(","))
                .flatMap(Arrays::stream)
                    .filter(variable -> variable.startsWith("X_FORWARDED_"))
                    .map(variable -> variable.replaceAll("X_FORWARDED_", ""))
                    .collect(Collectors.joining(","));
    }
}
//END
