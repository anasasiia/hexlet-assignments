package exercise;

// BEGIN
public class ListThread extends Thread {
    private SafetyList safetyList;
    ListThread(SafetyList safetyList) {
        this.safetyList = safetyList;
    }

    @Override
    public void run() {
        for (var i = 0; i < 1000; i++) {
            safetyList.add(i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
// END
