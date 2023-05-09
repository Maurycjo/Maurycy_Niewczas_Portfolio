package pl.edu.pwr.internetapp.window;

import pl.edu.pwr.internetapp.entity.Installation;
import pl.edu.pwr.internetapp.service.implementation.InstallationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstallationPanel extends JPanel {

    private MainWindow mainWindow;
    private InstallationService installationService;
    private Long clientId;
    JScrollPane installationScrollPane;
    String[] installationColumnNames = {"Id", "Adres", "Numer Routera"};
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
        JButton deleteInstallationButton = new JButton("Usuń");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton, FlowLayout.LEFT);
        buttonPanel.add(paymentButton);
        buttonPanel.add(addInstallationButton);
        //buttonPanel.add(editInstallationButton);
        buttonPanel.add(deleteInstallationButton);

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

        addInstallationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DialogFrame dialogFrame = new DialogFrame(3);
                dialogFrame.setWindowActionName("Dodaj nową Instalacje");
                ArrayList<String> labelNamesArrayList = new ArrayList<>(Arrays.asList("Adres", "Numer Routera", "Nazwa serwisu"));
                dialogFrame.setLabelNamesInWindow(labelNamesArrayList);



                dialogFrame.setActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        ArrayList<String> dataArrayList = dialogFrame.getTextDataFromTextFields();
                        installationService.addInstallation(dataArrayList.get(0), dataArrayList.get(1), dataArrayList.get(2), clientId);
                        dialogFrame.dispose();
                        fillInstallationTable(installationService);
                    }
                });




            }
        });
        editInstallationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



            }
        });
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int index = installationTable.getSelectedRow();

                Long installationId = Long.valueOf(String.valueOf(installationTableModel.getValueAt(index, 0)));
                mainWindow.changeToPaymentPanel(installationId);

            }
        });

        deleteInstallationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int index = installationTable.getSelectedRow();

                Long installationId = Long.valueOf(String.valueOf(installationTableModel.getValueAt(index, 0)));
                installationService.deleteInstallation(installationId);

                fillInstallationTable(installationService);

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
                    };

            installationTableModel.addRow(installationTypeFields);
        }

       installationTable.repaint();

    }







}
