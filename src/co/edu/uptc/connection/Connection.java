package co.edu.uptc.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {
    private ServerSocket serverSocket;
    private Socket socket;
    private String type;
    private String host;
    private int port;

    public Connection(String type, String host, int port) {
        this.type = type;
        this.host = host;
        this.port = port;
    }

    public void connect() {
        try {
            internalConnect();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void internalConnect() throws IOException {
        switch (type) {
            case "server" -> {
                serverSocket = new ServerSocket(port);
                socket = new Socket();
            }
            case "client" -> {
                socket = new Socket(host, port);
            }
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}