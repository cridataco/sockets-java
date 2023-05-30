package co.edu.uptc.connection;

import co.edu.uptc.utils.ColorUtils;

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
        connection = new Connection("server", host, port);
        connection.connect();
        socketList = new LinkedList<>();
        System.out.println("El servidor " + connection.getHost());
        Thread runConnection = new Thread(() -> {
            while (true)
                tryConnection();
        });
        runConnection.start();
    }

    private void tryConnection() {
        try {
            System.out.println(ColorUtils.GREEN + "Esperando..." + ColorUtils.RESET);
            Socket socket = connection.getServerSocket().accept();
            synchronized (socketList) {
                socketList.add(socket);
            }
            System.out.println(ColorUtils.GREEN + "Conectado" + ColorUtils.RESET);
            System.out.println(ColorUtils.PURPLE + "receiveData: " + socketList.size() + ColorUtils.RESET);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void start() {
        System.out.println(ColorUtils.GREEN + "Direccion del servidor " + connection.getHost());
        System.out.println(ColorUtils.GREEN + "Puerto del servidor " + connection.getPort());
        Thread runConnection = new Thread(() -> {
            while (true) {
                tryConnection();
            }
        });
        runConnection.start();
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