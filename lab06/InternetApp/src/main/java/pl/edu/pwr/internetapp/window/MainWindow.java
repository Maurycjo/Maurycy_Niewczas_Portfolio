package pl.edu.pwr.internetapp.window;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.pwr.internetapp.service.implementation.*;

import javax.swing.*;

@Controller
public class MainWindow extends JFrame {

    StartPanel startPanel;
    ClientPanel clientPanel;
    ServicePanel servicePanel;
    InstallationPanel installationPanel;
    PaymentPanel paymentPanel;

    private ClientService clientService;
    private ChargeService chargeService;
    private InstallationService installationService;
    private PaymentService paymentService;
    private ServiceTypeService serviceTypeService;

    @Autowired
    public MainWindow(ClientService clientService, ChargeService chargeService, InstallationService installationService, PaymentService paymentService, ServiceTypeService serviceTypeService){

        this.clientService = clientService;
        this.chargeService = chargeService;
        this.installationService = installationService;
        this.paymentService = paymentService;
        this.serviceTypeService = serviceTypeService;

        setSize(800, 600);
        startPanel = new StartPanel(this);
        getContentPane().add(startPanel);


    }

    public void changeToClientPanel(){


        clientPanel = new ClientPanel(this, clientService);

        if(installationPanel==null){

            getContentPane().remove(startPanel);
            startPanel=null;
        }
        else{

            getContentPane().remove(installationPanel);
            installationPanel = null;

        }


        getContentPane().add(clientPanel);
        this.repaint();
        clientPanel.revalidate();
    }

    public void changeToStartPanel(){

        startPanel = new StartPanel(this);

        if(clientPanel!=null){
            getContentPane().remove(clientPanel);
            clientPanel = null;
        }else {
            getContentPane().remove(servicePanel);
            servicePanel = null;
        }

        getContentPane().add(startPanel);
        this.repaint();
        startPanel.revalidate();
    }

    public void changeToServicePanel() {

        servicePanel = new ServicePanel(this, serviceTypeService);
        getContentPane().remove(startPanel);
        startPanel = null;
        getContentPane().add(servicePanel);
        this.repaint();
        servicePanel.revalidate();

    }

    public void changeToInstallationPanel(Long clientId){

        installationPanel = new InstallationPanel(this, installationService, clientId);

        if(clientPanel==null){
            getContentPane().remove(paymentPanel);
            paymentPanel=null;
        }
        else{

            getContentPane().remove(clientPanel);
            clientPanel=null;
        }


        getContentPane().add(installationPanel);
        this.repaint();
        installationPanel.revalidate();
    }

    public void changeToPaymentPanel(Long installationId){

        paymentPanel = new PaymentPanel(this, chargeService, paymentService, installationId);
        getContentPane().remove(installationPanel);
        getContentPane().add(paymentPanel);
        this.repaint();
        paymentPanel.revalidate();

    }

}
