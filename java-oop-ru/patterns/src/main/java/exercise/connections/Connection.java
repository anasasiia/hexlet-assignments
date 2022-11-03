package exercise.connections;

public interface Connection {
    // BEGIN
    void connect();
    void disconnect();
    boolean write();
    String getState();
    // END
}
