package co.edu.uptc.presenter;

import co.edu.uptc.model.ModelManagerClient;
import co.edu.uptc.model.ModelManagerServer;
import co.edu.uptc.view.MyFrame;

public class GeneralManager {
    private Contract.View view;
    private Contract.Presenter presenter;
    private Contract.Model model;

    public GeneralManager(String connectionType, String ip, int port) {
        view = new MyFrame();
        presenter = new Presenter();
        switch (connectionType.toLowerCase()) {
            case "client":
                model = new ModelManagerClient(ip, port);
                break;
            case "server":
                model = new ModelManagerServer(ip, port);
                break;
        }
        createMVP();
        if (connectionType.equalsIgnoreCase("server")) {
            model.startServer();
        }
    }

    public void runProject() {
        view.start();
        presenter.loadData();
    }

    public void createMVP() {
        presenter.setView(view);
        presenter.setModel(model);
        model.setPresenter(presenter);
        view.setPresenter(presenter);
    }
}
