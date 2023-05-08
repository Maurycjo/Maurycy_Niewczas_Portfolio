package pl.edu.pwr.internetapp.window;

import pl.edu.pwr.internetapp.entity.Installation;
import pl.edu.pwr.internetapp.service.implementation.InstallationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InstallationPanel extends JPanel {

    private MainWindow mainWindow;
    private InstallationService installationService;
    private Long clientId;
    JScrollPane installationScrollPane;
    String[] installationColumnNames = {"Id", "Adres", "Numer Routera", "Typ Serwisu"};
    DefaultTableModel installationTableModel = new DefaultTableModel(installationColumnNames, 0);
    JTable installationTable = new JTable(installationTableModel);

    InstallationPanel(MainWindow mainWindow, InstallationService installationService, Long clientId){

        this.mainWindow = mainWindow;
        this.installationService = installationService;

        this.clientId = clientId;

        JButton backButton = new JButton("Powrót");
        JButton paymentButton = new JButton("Opłaty i wpłaty");
        JButton addInstallationButton = new JButton("Dodaj");
        JButton editInstallationButton = new JButton("Edytuj");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton, FlowLayout.LEFT);
        buttonPanel.add(paymentButton);
        buttonPanel.add(addInstallationButton);
        buttonPanel.add(editInstallationButton);

        installationScrollPane = new JScrollPane(installationTable);
        fillInstallationTable(installationService);

        this.add(installationScrollPane, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mainWindow.changeToClientPanel();

            }
        });


    }

    private void fillInstallationTable(InstallationService installationService){

        installationTableModel = (DefaultTableModel) installationTable.getModel();
        installationTableModel.setRowCount(0);

        List<Installation> installationList = installationService.getAllInstallationsByClientId(clientId);

        for(var installation : installationList){


            String[] installationTypeFields ={Long.toString(installation.getId()),
                    installation.getAddress(),
                    installation.getRouterNumber(),
                    ""};

            installationTableModel.addRow(installationTypeFields);
        }

       installationTable.repaint();

    }







}
