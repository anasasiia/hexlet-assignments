package exercise;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection {
    private Connection connection;
    private final String ipAddress;
    private final int port;
    private List<String> dataCollector = new ArrayList<>();

    public TcpConnection(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.connection = new Disconnected(this);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getCurrentState() {
        return getConnection().getState();
    }

    public void connect() {
        getConnection().connect();
    }

    public void disconnect() {
        getConnection().disconnect();
    }

    public void write(String data) {
        if (getConnection().write()) {
            dataCollector.add(data);
        }
    }
}
// END
