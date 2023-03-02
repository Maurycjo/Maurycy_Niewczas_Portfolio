package pl.edu.pwr.lib;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MainWindow extends JFrame{

    FileHandler fHander = new FileHandler();
   // static JList fileList;
    public MainWindow() {
        // The data
        fHander.fillFilesList();
        //JList fileList = new JList<String>();
        JFrame frame = new JFrame();
        frame.setSize(400, 200);

        frame.add( new JScrollPane( new JList(fHander.getFiles().toArray())));

        frame.add( new JButton("Usuń pamięć programu"){{
            addActionListener( new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Usunieto");
                }
            });
        }}, BorderLayout.SOUTH);
        //frame.pack();
        frame.setVisible( true );
    }
}
