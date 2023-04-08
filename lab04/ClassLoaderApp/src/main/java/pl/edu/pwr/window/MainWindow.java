package pl.edu.pwr.window;

import pl.edu.pwr.file.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.*;

public class MainWindow extends JFrame {

    FileHandler fileHandler = new FileHandler();
    JList<Object> jFileList = new JList<>();
    JList<Object> jMethodList = new JList<>();
    JTextField pathField = new JTextField();
    private final JTextArea contentJtextArea;
    private final JTextArea infoJtextArea;
    JScrollPane sp ;
    JScrollPane contentSp;
    DefaultListModel fileListModel = new DefaultListModel();
    DefaultListModel methodListModel = new DefaultListModel();



    JTextField classNameTextField;
    JTextField classMethodNameTextField;
    JTextField methodInfoTextField;
    JTextField methodInputTextField;
    JTextField resultOutputTextField;




    private void updateJfileList(){
        //updating JFileList with item from current path and .md5 path
        fileHandler.fillFilesPathList();
        jFileList.removeAll();
        fileListModel.clear();
        for(Path path:fileHandler.getFilesPath()){

            String text;
            if(Files.isDirectory(path)){
                text="Dir     |";
            } else if(path.toString().endsWith(".class")){
                text="Class |";
            } else{
                text="File    |";
            }

            fileListModel.addElement(text+path.getFileName());
        }
        jFileList.setModel(fileListModel);

        jFileList.repaint();
        pathField.setText(fileHandler.getCurrentPath().toString());
    }

    private void updateJmethodList(JavaClassFile javaClassFile){

        jMethodList.removeAll();
        methodListModel.clear();

        methodListModel.addElement("Klasa: "+javaClassFile.getClassName());

        for(String methodName:javaClassFile.getMethodArrayList()){

            methodListModel.addElement(methodName);
        }
        jMethodList.setModel(methodListModel);
        jMethodList.repaint();
    }

    private void clearJmethodList(){
        jMethodList.removeAll();
        methodListModel.clear();
        methodListModel.addElement("Klasa: Brak");
        jMethodList.setModel(methodListModel);
        jMethodList.repaint();


    }

    public MainWindow()
    {
        //JPanel cardPanel = new JPanel();
        setTitle("Aplikacja Ładowacza Klas");
        setSize(1000, 400);

        pathField.setText(fileHandler.getCurrentPath().toString());
        pathField.setEditable(false);

        JPanel jp = new JPanel();
        updateJfileList();
        clearJmethodList();
        jFileList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt){
                JList jFileList = (JList)evt.getSource();
                if(evt.getClickCount()==2){

                    fileHandler.clearFiles();
                    fileHandler.fillFilesList();

                    //after 2 clicking two times on item in jFile list change directory if possible
                    int index = jFileList.locationToIndex(evt.getPoint());


                    ElementInFileSystem clickedElement;
                    //dir
                    if(Files.isDirectory(fileHandler.getFilesPath().get(index))){

                        clickedElement = new DirElement(fileHandler.getFilesPath().get(index));
                        fileHandler.setCurrentPath(clickedElement.getFilePath());

                    } else if(fileHandler.getFilesPath().get(index).toString().endsWith(".class")){


                        //csv file
                        clickedElement = new JavaClassFile(fileHandler.getFilesPath().get(index));
                        JavaClassFile javaClassFile = (JavaClassFile)clickedElement;

                        //check if csv is in memory


                        //metoda wywołująca metody załadowanej klasy
                        updateJmethodList(javaClassFile);

                    } else {
                        clickedElement = new FileElement(fileHandler.getFilesPath().get(index));

                    }
                }
                updateJfileList();
                }
        });

        sp = new JScrollPane(jFileList);
        contentSp = new JScrollPane(jMethodList);

        //jtextArea for file content
        contentJtextArea = new JTextArea();
        contentJtextArea.setVisible(true);

        contentJtextArea.setPreferredSize(new Dimension(100, 100));

        //jtextArea for hash, from memory or disc
        infoJtextArea = new JTextArea();
        infoJtextArea.setVisible(true);


        //additional info
        JPanel additionalInfoPanel = new JPanel();
        additionalInfoPanel.setLayout(new BoxLayout(additionalInfoPanel, BoxLayout.Y_AXIS));


        classNameTextField = new JTextField("Nazwa Klasy: " );
        classMethodNameTextField = new JTextField("Nazwa Metody: ");
        methodInfoTextField = new JTextField("Operacja: ");
        methodInputTextField = new JTextField();
        resultOutputTextField = new JTextField("Wynik: ");

        additionalInfoPanel.add(classNameTextField);
        additionalInfoPanel.add(classMethodNameTextField);
        additionalInfoPanel.add(methodInfoTextField);
        additionalInfoPanel.add(methodInputTextField);
        additionalInfoPanel.add(resultOutputTextField);


        classNameTextField.setEditable(false);
        classMethodNameTextField.setEditable(false);
        methodInfoTextField.setEditable(false);
        methodInputTextField.setEditable(true);
        resultOutputTextField.setEditable(false);


        //Buttons
        JPanel buttonPanel = new JPanel();
        JButton backButton = new JButton("Powrót");
        JButton refreshButton = new JButton("Odśwież");

        buttonPanel.add(backButton);
        buttonPanel.add(refreshButton);

        // add ActionListeners
        backButton.addActionListener(new ActionListener()
        {
            //go 1 level up in directory
            public void actionPerformed(ActionEvent arg0)
            {
              fileHandler.parentPath();
              updateJfileList();
            }
        });

        refreshButton.addActionListener(new ActionListener()
        {
            //refresh, checking checksums, deletions, additions
            public void actionPerformed(ActionEvent arg0)
            {
                updateJfileList();
            }
        });

        getContentPane().add(pathField, BorderLayout.NORTH);
        getContentPane().add(sp, BorderLayout.WEST);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(contentSp, BorderLayout.CENTER);
        getContentPane().add(additionalInfoPanel, BorderLayout.EAST);
    }

     private void loadAdditionalInfo(JavaClassFile javaClassFile){

         classNameTextField.setText("Nazwa Klasy: ");
         classMethodNameTextField.setText("Nazwa metody: ");
         methodInfoTextField.setText("Operacja: ");
         resultOutputTextField.setText("Wynik: ");


     }



}