package co.edu.uptc.connection;

import co.edu.uptc.utils.Utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Server {
    private Connection connection;
    private List<Socket> socketList;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

    public Server(String host, int port) {
        init(host, port);
        socketList = new LinkedList<>();
        System.out.println("estado del server " + connection.getHost());
        Thread runConnection = new Thread(() -> {
            while (true)
                tryConnection();
        });
        runConnection.start();
    }

    private void tryConnection() {
        try {
            System.out.println(Utils.getGreenMessage() + "Esperando..." + Utils.getResetMessage());
            Socket socket = connection.getServerSocket().accept();
            synchronized (socketList) {
                socketList.add(socket);
            }
            System.out.println(Utils.getGreenMessage() + "Conectado" + Utils.getResetMessage());
            System.out.println(Utils.getPurpleMessage() + "receiveData: " + socketList.size() + Utils.getResetMessage());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void init(String host, int port) {
        connection = new Connection("server", host, port);
        connection.connect();
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setSocketList(List<Socket> socketList) {
        this.socketList = socketList;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    public Connection getConnection() {
        return connection;
    }

    public List<Socket> getSocketList() {
        return socketList;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }
}