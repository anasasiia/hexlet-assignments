package exercise;

import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] numbers) {
        MaxThread thread1 = new MaxThread(numbers);
        thread1.start();
        LOGGER.log(Level.INFO, "Thread " + thread1.getName() + " started");

        MinThread thread2 = new MinThread(numbers);
        thread2.start();
        LOGGER.log(Level.INFO, "Thread " + thread2.getName() + " started");

        try {
            thread1.join();
            LOGGER.log(Level.INFO, "Thread " + thread1.getName() + " finished");
            thread2.join();
            LOGGER.log(Level.INFO, "Thread " + thread2.getName() + " finished");
        } catch (InterruptedException e) {
            System.out.println("Thread was stopped");
        }

        return Map.of("min", thread2.getMinNumber(), "max", thread1.getMaxNumber());
    }
    // END
}
