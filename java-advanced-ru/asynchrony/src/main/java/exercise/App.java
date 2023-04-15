package exercise;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class App {

    // BEGIN
    private static Path getFullPath(String filepath) {
        return Paths.get(filepath).toAbsolutePath().normalize();
    }

    public static CompletableFuture<String> unionFiles(String file1, String file2, String file3)
                                                                throws ExecutionException, InterruptedException {

        CompletableFuture<String> futureFile1 = CompletableFuture.supplyAsync(() -> {
            String text = "";
            try {
                text = Files.readString(getFullPath(file1));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return text;
        });

        CompletableFuture<String> futureFile2 = CompletableFuture.supplyAsync(() -> {
            String text = "";
            try {
                text = Files.readString(getFullPath(file2));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return text;
        });

        return futureFile1.thenCombine(futureFile2, (textFile1, textFile2) -> {
            String content = textFile1 + textFile2;

            try {
                Files.writeString(getFullPath(file3), content, StandardOpenOption.CREATE);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return content;
        }).exceptionally(ex -> {
            System.out.println("We have an exception " + ex.getMessage());
            return "Unknown";
        });
    }


    public static CompletableFuture<Long> getDirectorySize(String path) {
        return CompletableFuture.supplyAsync(() -> {
            long size = 0;

            try {
                size = Files.walk(getFullPath(path), 1)
                        .filter(Files::isRegularFile)
                        .mapToLong(file -> {
                            try {
                                return Files.size(file);
                            } catch (Exception e) {
                                System.out.printf("Failed to get size of %s", file);
                                return 0;
                            }
                        })
                        .sum();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return size;
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> result = App.unionFiles(
                "src/main/resources/file1.txt",
                "src/main/resources/file2.txt",
                "src/main/resources/file3.txt");
        CompletableFuture<Long> size = App.getDirectorySize("src/test/java/exercise");
        System.out.println(size.get());
        // END
    }
}

