package pl.edu.pwr.internetapp.window;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ClientPanel extends JPanel {

    private MainWindow mainWindow;

    JScrollPane clientScrollPane;
    String[] clientColumnNames = {"ID", "Imie", "Nazwisko"};
    DefaultTableModel clientTableModel = new DefaultTableModel(clientColumnNames, 0);
    JTable clientTable = new JTable(clientTableModel);

    ClientPanel(MainWindow mainWindow){

        this.mainWindow = mainWindow;

        JButton backButton = new JButton("Powrót");
        JButton addClientButton = new JButton("Dodaj Klienta");
        JButton deleteClientButton = new JButton("Usuń Klienta");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton, FlowLayout.LEFT);
        buttonPanel.add(addClientButton, FlowLayout.CENTER);
        buttonPanel.add(deleteClientButton, FlowLayout.RIGHT);

        clientScrollPane = new JScrollPane(clientTable);
        fillClientTable();



        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Powrot");
                mainWindow.changeToStartPanel();

            }
        });


        this.add(clientScrollPane, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void fillClientTable(){

        clientTableModel = (DefaultTableModel) clientTable.getModel();

        //test data
        String[] objs ={"1", "Maurycy", "Niewczas"};
        clientTableModel.addRow(objs);
        clientTable.repaint();

    }


}
