package co.edu.uptc.presenter;

import co.edu.uptc.model.ModelManagerClient;
import co.edu.uptc.model.ModelManagerServer;
import co.edu.uptc.utils.Utils;
import co.edu.uptc.view.View;

public class GeneralManager {
    private Contract.View view;
    private Contract.Presenter presenter;
    private Contract.Model model;

    public GeneralManager(String connectionType, String ip, int port) {
        view = new View();
        presenter = new Presenter();
        switch (connectionType.toLowerCase()){
            case "client" : model = new ModelManagerClient(ip, port);
            case "server" : model = new ModelManagerServer(ip, port);
        }
        createMVP();
    }

    private void runProject() {
        view.start();
        presenter.loadData();
    }

    public void createMVP() {
        presenter.setView(view);
        presenter.setModel(model);
        view.setPresenter(presenter);
        model.setPresenter(presenter);
    }

    public static void main(String[] args) {
        if (args.length >= 2) {
            String connectionType = args[0];
            String ip = args[1];
            int port = Integer.parseInt(args[2]);
            System.out.println(Utils.getGreenMessage() + "\nConexi\u00f3n: " + connectionType + "\nIP: " + ip + "\nPuerto: " + port + "\n" + Utils.getResetMessage());
            new GeneralManager(connectionType, ip, port).runProject();
        } else {
            System.out.println(Utils.getRedMessage() + "\nError: Debe ingresar el tipo de conexi\u00f3n y la direcci\u00f3n IP y el puerto del servidor");
            System.out.println("Ejemplo: java -jar Cliente.jar client 172.0.0.1 8080\n" + Utils.getResetMessage());
        }
    }
}
