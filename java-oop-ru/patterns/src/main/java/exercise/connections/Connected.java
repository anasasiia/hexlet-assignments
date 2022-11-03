package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {
    private TcpConnection tcpConnection;

    public Connected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    @Override
    public void connect() {
        System.out.println("Error");
    }

    @Override
    public void disconnect() {
        this.tcpConnection.setConnection(new Disconnected(this.tcpConnection));
    }

    @Override
    public boolean write() {
        return true;
    }

    @Override
    public String getState() {
        return "connected";
    }
}
// END
