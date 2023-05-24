package co.edu.uptc.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Client {
    private Connection connection;
    private DataInputStream dataInputStream;

    public Client(String host, int port) {
        init(host, port);
        tryConnection();
    }

    private void tryConnection() {
        try {
            dataInputStream = new DataInputStream(connection.getSocket().getInputStream());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void init(String host, int port) {
        connection = new Connection("client", host, port);
        connection.connect();
    }

    public void closeStream() {
        try {
            dataInputStream.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }
}
