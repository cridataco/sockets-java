package co.edu.uptc.model;

import co.edu.uptc.connection.Server;
import co.edu.uptc.presenter.Contract;
import co.edu.uptc.utils.Utils;
import com.google.gson.Gson;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

public class ModelManagerServer implements Contract.Model {
    private Contract.Presenter presenter;
    private Rectangle rectangle;
    private File file;
    private FileOutputStream fileOutputStream;
    private Server server;
    private List<Socket> socketList;
    private byte[] imageBytes;

    public ModelManagerServer(String ip, int port) {
        rectangle = new Rectangle();
        server = new Server(ip, port);
        socketList = new LinkedList<>();
        file = new File("assets/gato.png");
    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadData() {
        updateAllClients();
        SecureRandom random = Utils.getSecureRandom("DRBG");
        rectangle = new Rectangle(random.nextInt(100), random.nextInt(100), 50, 50);
        presenter.updateView();
        Thread repaintThread = new Thread(() -> {
            while (true)
                updateAllClients();
        });
        repaintThread.start();
    }

    public void updateAllClients() {
        socketList = server.getSocketList();
        if (/*!*/socketList.isEmpty()) {
            //Gson gson = new Gson();
            //String info = gson.toJson(rectangle);
            //sendInfo(info);

            try {
                fileOutputStream = new FileOutputStream(file);
                imageBytes = Files.readAllBytes(Paths.get("gato.png"));
                System.out.println("El tamaño del array es " + imageBytes.length);
                sendPhoto();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private void sendInfo(String info) {
        DataOutputStream dataOutputStream;
        synchronized (socketList) {
            for (Socket socket : socketList) {
                try {
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataOutputStream.writeUTF(info);
                } catch (SocketException socketException) {
                    socketList.remove(socket);
                    System.out.println(Utils.getCyanMessage() + "Client disconnected" + Utils.getResetMessage());
                    System.out.println(Utils.getPurpleMessage() + "Clients connected: " + socketList.size() + Utils.getResetMessage());
                    break;
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    private void sendPhoto() {
        DataOutputStream dataOutputStream;
        synchronized (socketList) {
            for (Socket socket : socketList) {
                try {
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    DataOutputStream dataOutputStream1 = new DataOutputStream(fileOutputStream);
                    dataOutputStream.writeUTF("assets/gato.png");
                    dataOutputStream1.write(imageBytes);


                    System.out.println("El tamaño del array es " + imageBytes.length);

                    //dataOutputStream1.close();
                    //dataOutputStream.close();

                    OutputStream out = socket.getOutputStream();
                    out.write(imageBytes);

                } catch (SocketException socketException) {
                    socketList.remove(socket);
                    System.out.println(Utils.getCyanMessage() + "Client disconnected" + Utils.getResetMessage());
                    System.out.println(Utils.getPurpleMessage() + "Clients connected: " + socketList.size() + Utils.getResetMessage());
                    break;
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public void updateRectanglePosition(Point point) {
        rectangle.setLocation(point);
        presenter.updateView();
    }

    @Override
    public int getColorRectangle() {
        return 0;
    }

    @Override
    public int getColorPanel() {
        return 0;
    }
}
