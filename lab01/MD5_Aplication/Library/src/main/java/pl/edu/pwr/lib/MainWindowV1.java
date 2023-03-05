package pl.edu.pwr.lib;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindowV1 extends JFrame{

    FileHandler fHander = new FileHandler();
    JPanel cards;
   // static JList fileList;
    public MainWindowV1() {
        cards = new JPanel(new CardLayout());
        // The data
        fHander.fillFilesList();
        //JList fileList = new JList<String>();
        JFrame frame = new JFrame();
        frame.setSize(400, 200);

        frame.add( new JScrollPane( new JList(fHander.getFiles().toArray())));
        frame.add(cards, BorderLayout.SOUTH);


        frame.add( new JButton("Usuń pamięć programu"){{
            addActionListener( new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Usunieto");
                }
            });
        }}, BorderLayout.AFTER_LINE_ENDS);

        frame.add( new JButton("Odśwież"){{
            addActionListener( new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("odswiezono");
                }
            });
        }}, BorderLayout.AFTER_LINE_ENDS);

        //frame.pack();
        frame.setVisible( true );
    }
}
