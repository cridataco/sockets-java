package co.edu.uptc.model;

import co.edu.uptc.connection.Client;
import co.edu.uptc.pojo.InfoFromServer;
import co.edu.uptc.presenter.Contract;
import com.google.gson.Gson;

import java.awt.*;
import java.io.DataInput;
import java.io.IOException;

public class ModelManagerClient implements Contract.Model {
    private Contract.Presenter presenter;
    private Client client;
    private Rectangle rectangle;
    private InfoFromServer infoFromServer;

    public ModelManagerClient(String ip, int port) {
        client = new Client(ip, port);
        rectangle = new Rectangle();
        infoFromServer = new InfoFromServer();
    }

    @Override
    public void setPresenter(Contract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadData() {
        Thread repaintThread = new Thread(this::receiveData);
        repaintThread.start();
    }

    public void receiveData() {
        String info;
        DataInput dataInputStream = client.getDataInputStream();
        Gson gson = new Gson();
        try {
            while (((info = dataInputStream.readUTF()) != null)) {
                System.out.println(info);
                //infoFromServer = gson.fromJson(info, InfoFromServer.class);
                //rectangle = infoFromServer.getFigureInformation().getRectangle();
                presenter.updateView();
            }
            client.closeStream();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    @Override
    public void updateRectanglePosition(Point point) {

    }

    @Override
    public int getColorRectangle() {
        return infoFromServer.getFigureInformation().getColor();
    }

    @Override
    public int getColorPanel() {
        return infoFromServer.getPanelInformation().getColor();
    }
}
