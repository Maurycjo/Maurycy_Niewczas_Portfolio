package pl.edu.pwr.internetapp.window;

import pl.edu.pwr.internetapp.entity.Client;
import pl.edu.pwr.internetapp.service.implementation.ClientService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ClientPanel extends JPanel {

    private MainWindow mainWindow;

    private ClientService clientService;
    JScrollPane clientScrollPane;
    String[] clientColumnNames = {"ID", "Imie", "Nazwisko"};
    DefaultTableModel clientTableModel = new DefaultTableModel(clientColumnNames, 0);
    JTable clientTable = new JTable(clientTableModel);


    ClientPanel(MainWindow mainWindow, ClientService clientService){

        this.mainWindow = mainWindow;

        JButton backButton = new JButton("Powrót");
        JButton addClientButton = new JButton("Dodaj Klienta");
        JButton deleteClientButton = new JButton("Usuń Klienta");
        JButton selectClientButton = new JButton("Wybierz");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton, FlowLayout.LEFT);
        buttonPanel.add(addClientButton, FlowLayout.CENTER);
        buttonPanel.add(deleteClientButton, FlowLayout.RIGHT);
        buttonPanel.add(selectClientButton);

        clientScrollPane = new JScrollPane(clientTable);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.changeToStartPanel();

            }
        });




        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                DialogFrame dialogFrame = new DialogFrame(2);
                dialogFrame.setWindowActionName("Dodaj nowego Klienta");
                ArrayList<String> labelNamesArrayList = new ArrayList<>(Arrays.asList("Imie", "Nazwisko"));
                dialogFrame.setLabelNamesInWindow(labelNamesArrayList);



                dialogFrame.setActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        ArrayList<String> dataArrayList = dialogFrame.getTextDataFromTextFields();
                        clientService.addClient(dataArrayList.get(0), dataArrayList.get(1));
                        dialogFrame.dispose();
                        fillClientTable(clientService);
                    }
                });

            }
        });

        deleteClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            int index = clientTable.getSelectedRow();

            Long clientId = Long.valueOf(String.valueOf(clientTableModel.getValueAt(index, 0)));
            clientService.deleteClient(clientId);


            fillClientTable(clientService);


            }
        });

        selectClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int index = clientTable.getSelectedRow();
                Long clientId = Long.valueOf(String.valueOf(clientTableModel.getValueAt(index, 0)));
                System.out.println(clientId);
                mainWindow.changeToInstallationPanel(clientId);

            }
        });

        fillClientTable(clientService);

        this.add(clientScrollPane, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void fillClientTable(ClientService clientService){

        clientTableModel = (DefaultTableModel) clientTable.getModel();
        clientTableModel.setRowCount(0);


        List<Client> clientList = clientService.getAllClients();

        for(var client : clientList){

            String[] clientFields = {Long.toString(client.getId()), client.getFirstName(), client.getLastName()};
            clientTableModel.addRow(clientFields);
        }

        clientTable.repaint();
    }


}
