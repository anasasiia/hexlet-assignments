package exercise;

class SafetyList {
    // BEGIN
    private int length = 10;
    private int count = 0;
    private long[] numbers = new long[length];

    public synchronized void add(long num) {
        if (getSize() < length) {
            numbers[getSize()] = num;
            count++;
            return;
        }
        length = length * 2;
        long[] newNumbers = new long[length];
        System.arraycopy(numbers, 0, newNumbers, 0, getSize());
        numbers = newNumbers;
        numbers[getSize()] = num;
        count++;
    }

    public long get(int index) {
        return numbers[index];
    }

    public int getSize() {
        return count;
    }
    // END
}
