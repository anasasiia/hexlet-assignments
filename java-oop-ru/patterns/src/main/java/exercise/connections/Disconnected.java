package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {
    private TcpConnection tcpConnection;

    public Disconnected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void connect() {
        this.tcpConnection.setConnection(new Connected(this.tcpConnection));
    }

    @Override
    public void disconnect() {
        System.out.println("Error");
    }

    @Override
    public boolean write() {
        System.out.println("Error");
        return false;
    }

    @Override
    public String getState() {
        return "disconnected";
    }
}
// END
