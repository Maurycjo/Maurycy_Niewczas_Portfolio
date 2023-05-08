package pl.edu.pwr.internetapp.window;

import javax.swing.*;

public class MainWindow extends JFrame {

    StartPanel startPanel;
    ClientPanel clientPanel;

    ServicePanel servicePanel;

    public MainWindow(){

        setSize(300, 300);
        startPanel = new StartPanel(this);
        getContentPane().add(startPanel);
    }

    public void changeToClientPanel(){

        clientPanel = new ClientPanel(this);
        getContentPane().remove(startPanel);
        startPanel = null;
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

        servicePanel = new ServicePanel(this);
        getContentPane().remove(startPanel);
        startPanel = null;
        getContentPane().add(servicePanel);
        this.repaint();
        servicePanel.revalidate();

    }
}
