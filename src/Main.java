import co.edu.uptc.presenter.GeneralManager;
import co.edu.uptc.utils.ColorUtils;

public class Main {
    public static void main(String[] args) {
        if(args.length >2){
            String connectionType = args[0];
            String ip = args[1];
            int port = Integer.parseInt(args[2]);
            GeneralManager generalManager = new GeneralManager(connectionType, ip, port);
            generalManager.runProject();
        } else{
            System.out.println(ColorUtils.RED + "\nError: Debe ingresar el tipo de conexi\u00f3n y la direcci\u00f3n IP y el puerto del servidor" + ColorUtils.RESET);
        }
    }
}
