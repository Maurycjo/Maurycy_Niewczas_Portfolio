package pl.edu.pwr.internetapp.window;

import pl.edu.pwr.internetapp.entity.ServiceType;
import pl.edu.pwr.internetapp.service.implementation.ServiceTypeService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServicePanel extends JPanel {

    private MainWindow mainWindow;

    private ServiceTypeService serviceTypeService;
    JScrollPane serviceScrollPane;
    String[] serviceColumnNames = {"Id", "Nazwa", "Cena"};
    DefaultTableModel serviceTableModel = new DefaultTableModel(serviceColumnNames, 0);
    JTable serviceTable = new JTable(serviceTableModel);

    ServicePanel(MainWindow mainWindow, ServiceTypeService serviceTypeService){

        this.mainWindow = mainWindow;
        this.serviceTypeService = serviceTypeService;
        JButton backButton = new JButton("Powrót");
        JButton addServiceButton = new JButton("Dodaj serwis");
        JButton editServiceButton = new JButton("Edytuj serwis");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton, FlowLayout.LEFT);
        buttonPanel.add(addServiceButton);
        buttonPanel.add(editServiceButton);

        serviceScrollPane = new JScrollPane(serviceTable);
        fillServiceTable(serviceTypeService);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                mainWindow.changeToStartPanel();
            }
        });

        editServiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        addServiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                DialogFrame dialogFrame = new DialogFrame(2);
                dialogFrame.setWindowActionName("Dodaj nowy Serwis");
                ArrayList<String> labelNamesArrayList = new ArrayList<>(Arrays.asList("Nazwa", "Cena"));
                dialogFrame.setLabelNamesInWindow(labelNamesArrayList);



                dialogFrame.setActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {


                        ArrayList<String> dataArrayList = dialogFrame.getTextDataFromTextFields();
                        serviceTypeService.addServiceType(dataArrayList.get(0), Float.parseFloat(dataArrayList.get(1)));
                        dialogFrame.dispose();
                        fillServiceTable(serviceTypeService);
                    }
                });



            }
        });

        this.add(serviceScrollPane, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void fillServiceTable(ServiceTypeService serviceTypeService){

        serviceTableModel = (DefaultTableModel) serviceTable.getModel();
        serviceTableModel.setRowCount(0);

        List<ServiceType> serviceTypeList = serviceTypeService.getAllServiceTypes();

        for(var serviceType : serviceTypeList){

            String[] serviceTypeFields ={Long.toString(serviceType.getId()), serviceType.getServiceName(), String.valueOf(serviceType.getPrice())};
            serviceTableModel.addRow(serviceTypeFields);
        }

        serviceTable.repaint();
    }

}
