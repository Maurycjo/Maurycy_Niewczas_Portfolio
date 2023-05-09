package pl.edu.pwr.internetapp.window;

import pl.edu.pwr.internetapp.entity.Charge;
import pl.edu.pwr.internetapp.entity.Payment;
import pl.edu.pwr.internetapp.service.implementation.ChargeService;
import pl.edu.pwr.internetapp.service.implementation.ClientService;
import pl.edu.pwr.internetapp.service.implementation.PaymentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PaymentPanel extends JPanel {

    private MainWindow mainWindow;
    private ChargeService chargeService;
    private PaymentService paymentService;

    JScrollPane paymentScrollPane;
    JScrollPane chargeScrollPane;
    String[] paymentColumnNames = {"Id","Dzien Wpłaty", "Wpłata"};
    String[] chargeColumnNames = {"Id","Do Dnia", "Opłata"};
    DefaultTableModel paymentTableModel = new DefaultTableModel(paymentColumnNames, 0);
    DefaultTableModel chargeTableModel = new DefaultTableModel(chargeColumnNames, 0);
    JTable paymentTable = new JTable(paymentTableModel);
    JTable chargeTable = new JTable(chargeTableModel);
    Long installationId;

    PaymentPanel(MainWindow mainWindow, ChargeService chargeService, PaymentService paymentService, Long installationId){

        this.mainWindow = mainWindow;
        this.chargeService = chargeService;
        this.paymentService = paymentService;
        this.installationId = installationId;

        JButton backButton = new JButton("Powrót");
        JButton addChargeButton = new JButton("Nowa Opłata");
        JButton addPaymentButton = new JButton("Nowa Wpłata");
        JButton deleteChargeButton = new JButton("Usuń Opłatę");
        JButton deletePaymentButton = new JButton("Usuń Wpłatę");
        JButton editChargeButton = new JButton("Edytuj Opłatę");
        JButton editPaymentButton = new JButton("Edytuj Wpłatę");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton, FlowLayout.LEFT);
        buttonPanel.add(addChargeButton);
        buttonPanel.add(deleteChargeButton);
        buttonPanel.add(editChargeButton);
        buttonPanel.add(addPaymentButton);
        buttonPanel.add(deletePaymentButton);
        buttonPanel.add(editPaymentButton);

        paymentScrollPane = new JScrollPane(paymentTable);
        chargeScrollPane = new JScrollPane(chargeTable);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow.changeToInstallationPanel(installationId);
            }
        });


        this.add(chargeScrollPane, BorderLayout.EAST);
        this.add(paymentScrollPane, BorderLayout.WEST);
        this.add(buttonPanel, BorderLayout.SOUTH);

    }

    private void fillPaymentTable(ChargeService chargeService, PaymentService paymentService){


        chargeTableModel = (DefaultTableModel) chargeTable.getModel();
        paymentTableModel = (DefaultTableModel) paymentTable.getModel();
        chargeTableModel.setRowCount(0);
        paymentTableModel.setRowCount(0);

        List<Charge> chargeList = chargeService.getAllChargesByInstallationId(installationId);
        List<Payment> paymentList = paymentService.getPaymentByInstallationId(installationId);

        for(var charge : chargeList){

            String[] fields ={Long.toString(charge.getId()), String.valueOf(charge.getPaymentDeadline()), Float.toString(charge.getAmountToPay())};
            chargeTableModel.addRow(fields);
        }


        for(var payment : paymentList){

            String[] fields ={Long.toString(payment.getId()), String.valueOf(payment.getPaymentDate()), Float.toString(payment.getAmountPaid())};
            chargeTableModel.addRow(fields);
        }

        chargeTable.repaint();
        paymentTable.repaint();


    }



}
