package exercise;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String file1, String file2, String file3)
                                                                throws ExecutionException, InterruptedException {

        CompletableFuture<String> futureFile1 = CompletableFuture.supplyAsync(() -> {
            Path filepath1 = Paths.get(file1);
            if (!Files.exists(filepath1)) {
                try {
                    throw new NoSuchFileException(file1);
                } catch (NoSuchFileException e) {
                    throw new RuntimeException(e);
                }
            }
            String text = "";
            try {
                text = Files.lines(filepath1).collect(Collectors.joining(" "));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return text;
        }).exceptionally(ex -> {
            System.out.println("We have an exception" + ex.getMessage());
            return null;
        });

        futureFile1.get();

        CompletableFuture<String> futureFile2 = CompletableFuture.supplyAsync(() -> {
            Path filepath2 = Paths.get(file2);
            if (!Files.exists(filepath2)) {
                try {
                    throw new NoSuchFileException(file2);
                } catch (NoSuchFileException e) {
                    throw new RuntimeException(e);
                }
            }
            String text = "";
            try {
                text = Files.lines(filepath2).collect(Collectors.joining(" "));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return text;
        }).exceptionally(ex -> {
            System.out.println("We have an exception" + ex.getMessage());
            return null;
        });

        futureFile2.get();

        return futureFile1.thenCombine(futureFile2, (textFile1, textFile2) -> {
            Path filepath3 = Paths.get(file3);
            String allText = textFile1 + " " + textFile2;
            if (!Files.exists(filepath3)) {
                File file = new File(file3);
            }
            try {
                Files.write(filepath3, allText.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return allText;
        });
    }


    public static CompletableFuture<Long> getDirectorySize(String path) {
        return CompletableFuture.supplyAsync(() -> {
            Path dirPath = Paths.get(path);
            long size = 0;

            try (Stream<Path> walk = Files.walk(dirPath)) {
                size = walk.filter(Files::isRegularFile)
                        .mapToLong(file -> {
                            try {
                                return Files.size(file);
                            } catch (IOException e) {
                                System.out.printf("Failed to get size of %s", file);
                                return 0;
                            }
                        })
                        .sum();
            } catch (IOException e) {
                System.out.printf("IO errors %s", e);
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

