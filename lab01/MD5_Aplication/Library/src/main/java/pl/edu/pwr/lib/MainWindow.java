package pl.edu.pwr.lib;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
public class MainWindow extends JFrame{


    MainWindow() {
        // The data
        final Object [] data = {"A","B","C"};


        JFrame frame = new JFrame();
        frame.setSize(400, 200);
        frame.add( new JScrollPane( new JList( data )));

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
