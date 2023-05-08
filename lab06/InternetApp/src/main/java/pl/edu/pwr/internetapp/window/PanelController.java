package pl.edu.pwr.internetapp.window;

import pl.edu.pwr.internetapp.entity.Client;
import pl.edu.pwr.internetapp.entity.Installation;

public class PanelController {

    enum PanelType{
        StartPanel,
        ClientPanel,
        ServicePanel,
        InstallationPanel,
        PaymentPanel
    }

    private PanelType panelType;
    private MainWindow mainWindow;

    PanelController(MainWindow mainWindow){

        panelType = PanelType.StartPanel;
        this.mainWindow = mainWindow;
    }

    private Long clientId;
    private Long installationId;

    public PanelType getPanelType() {
        return panelType;
    }

    public void goToStartPanel(){

        clientId = null;
        panelType = PanelType.StartPanel;

    }

    public void goToServicePanel(){
        panelType = PanelType.ServicePanel;
    }

    public void goToClientPanel(){
        panelType = PanelType.ClientPanel;
        clientId = null;
    }

    public void goToInstallationPanel(Long clientId){
        this.clientId = clientId;
        this.installationId = null;
        panelType = PanelType.InstallationPanel;
    }

    public void goToPaymentPanel(Long installationId){
        this.installationId = installationId;
        panelType = PanelType.PaymentPanel;
    }

}
