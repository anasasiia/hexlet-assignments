package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread {
    private int[] numbers;

    private int maxNumber;

    @Override
    public void run() {
        maxNumber = Arrays.stream(numbers).max().orElseThrow();
    }

    public MaxThread(int[] numbers) {
        this.numbers = numbers;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public int getMaxNumber() {
        return maxNumber;
    }
}
// END
