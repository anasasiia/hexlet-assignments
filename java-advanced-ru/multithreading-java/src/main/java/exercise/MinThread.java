package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {
    private int[] numbers;

    private int minNumber;

    @Override
    public void run() {
        minNumber = Arrays.stream(numbers).min().orElseThrow();
    }

    public MinThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public int getMinNumber() {
        return minNumber;
    }
}
// END
