package pl.edu.pwr.internetapp.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel {

    private MainWindow mainWindow;

    StartPanel(MainWindow mainwindow){

        this.mainWindow = mainwindow;

        JButton clientButton = new JButton("Klienci");
        JButton serviceButton = new JButton("Serwisy");

        JLabel welcomeLabel = new JLabel("Witaj w aplikacji zarzadzajacej klientami w serwisie internetowym");

        JPanel buttonPanel = new JPanel();

        buttonPanel.add(clientButton, BorderLayout.EAST);
        buttonPanel.add(serviceButton, BorderLayout.WEST);

        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Klienci");
                mainwindow.changeToClientPanel();

            }
        });

        serviceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Serwisy");
                mainwindow.changeToServicePanel();
            }
        });


        this.add(welcomeLabel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.repaint();
    }

}
