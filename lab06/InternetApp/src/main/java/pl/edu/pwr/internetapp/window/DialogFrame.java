package pl.edu.pwr.internetapp.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogFrame extends JDialog {


    private ArrayList<JTextField> dataTextFields = new ArrayList<>();
    private ArrayList<JLabel> dataTextLabel = new ArrayList<>();

    private ArrayList<JPanel> rowPanels = new ArrayList<>();
    private JPanel centralPanel = new JPanel();

    private JLabel nameWindowPanel = new JLabel();
    private JButton okButton = new JButton("Zatwierdz");

    private ArrayList<String> fieldsDataArrayList;

    ActionListener actionListener;

    private int numbersOfFields;
    DialogFrame(int numbersOfFields){


        this.setVisible(true);

        this.numbersOfFields = numbersOfFields;
        this.setSize(new Dimension(320, 100*numbersOfFields));
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));


        for(int i=0;i<numbersOfFields; i++){

            dataTextLabel.add(new JLabel());
            dataTextFields.add(new JTextField());

            dataTextLabel.get(i).setPreferredSize(new Dimension(200, 20));
            dataTextFields.get(i).setPreferredSize(new Dimension(200, 20));

            rowPanels.add(new JPanel(new FlowLayout(FlowLayout.LEFT)));


            rowPanels.get(i).add(dataTextLabel.get(i));
            rowPanels.get(i).add(dataTextFields.get(i));

            centralPanel.add(rowPanels.get(i));
        }



        this.getContentPane().add(nameWindowPanel, BorderLayout.NORTH);
        this.getContentPane().add(centralPanel, BorderLayout.CENTER);
        this.getContentPane().add(okButton, BorderLayout.SOUTH);

        this.repaint();
    }

    public void setActionListener(ActionListener actionListener){
        this.actionListener = actionListener;
        okButton.addActionListener(actionListener);
    }

    public ArrayList<String> getFieldsDataArrayList() {
        return fieldsDataArrayList;
    }

    public void setWindowActionName(String name){
        nameWindowPanel.setText(name);
    }

    public void setLabelNamesInWindow(ArrayList<String> labelNamesArrayList){

        for(int i=0;i<numbersOfFields;i++){
            dataTextLabel.get(i).setText(labelNamesArrayList.get(i));
        }

    }

    public ArrayList<String> getTextDataFromTextFields(){

        ArrayList<String> dataArrayList = new ArrayList<>();

        for(int i=0;i<numbersOfFields;i++){
            dataArrayList.add(dataTextFields.get(i).getText());
        }
        return dataArrayList;
    }


//    public static void main(String[] args) {
//
//        DialogFrame dialogFrame = new DialogFrame(3);
//        dialogFrame.setVisible(true);
//
//        dialogFrame.setWindowActionName("Dodaj nowego Klienta");
//
//        ArrayList<String> labelNamesArrayList = new ArrayList<>(Arrays.asList("Id", "Imie", "Nazwisko"));
//
//        dialogFrame.setLabelNamesInWindow(labelNamesArrayList);
//
//
//    }


}
