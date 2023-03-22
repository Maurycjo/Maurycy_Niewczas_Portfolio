package pl.pwr.edu.window;

import pl.pwr.edu.music.ArtistLoader;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;




public class MainWindow extends JFrame {

    CardLayout cardLayout = new CardLayout();
    JPanel cards = new JPanel(cardLayout); //panel that contains cards

    Bundle bundle = new Bundle();


    ArtistLoader artistLoaderFromFile = new ArtistLoader();
    //AuthorQuestionPanel card1 = new AuthorQuestionPanel(bundle, artistLoaderFromFile);
    //AlbumQuestionPanel card1 = new AlbumQuestionPanel(bundle, artistLoaderFromFile);

    JMenuBar menuBar;
    JMenuItem menuPl, menuEng;

    JButton newQuizButton = new JButton();
    JButton checkButton = new JButton();

    final int numberOfQuestion = 4;
    private int currentPanelIndex=0;
    ArrayList<QuestionPanel> questionPanels = new ArrayList<>();

    QuestionPanel currentPanel;

    public MainWindow()
    {

        setTextOnElements();
        setSize(600, 400);



        questionPanels.add(new AuthorQuestionPanel(bundle, artistLoaderFromFile));
        questionPanels.add(new AlbumQuestionPanel(bundle, artistLoaderFromFile));
        questionPanels.add(new AuthorQuestionPanel(bundle, artistLoaderFromFile));
        questionPanels.add(new AlbumQuestionPanel(bundle, artistLoaderFromFile));

        currentPanel= questionPanels.get(currentPanelIndex);

        for(int i=0;i<numberOfQuestion;i++){
            cards.add(questionPanels.get(i));
        }


        menuBar = new JMenuBar();
        menuPl = new JMenuItem(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(bundle.language== Bundle.Language.ENGLISH){
                    bundle.changeLanguage();
                    setTextOnElements();
                    currentPanel.setBundle(bundle);
                    currentPanel.setLanguage();
                }


            }
        });


        menuPl.setText("PL");

        menuEng = new JMenuItem(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(bundle.language==Bundle.Language.POLISH){
                    bundle.changeLanguage();
                    setTextOnElements();
                    currentPanel.setBundle(bundle);
                    currentPanel.setLanguage();

                }
            }
        });
        menuEng.setText("ENG");

        menuBar.add(menuPl);
        menuBar.add(menuEng);
        setJMenuBar(menuBar);

        //Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newQuizButton);
        buttonPanel.add(checkButton);

        // add ActionListeners
        checkButton.addActionListener(new ActionListener()
        {
            //refresh, checking checksums, deletions, additions
            public void actionPerformed(ActionEvent arg0)
            {
                if(currentPanel.checkIfCorrectSelect()){
                    String message = currentPanel.getAcceptedMessage();
                    JOptionPane.showMessageDialog(getContentPane(), message,
                            "", JOptionPane.INFORMATION_MESSAGE);
                    System.out.println("Dobra odpowiedz");
                }
                else {
                    String message = currentPanel.getRejectedMessage();
                    JOptionPane.showMessageDialog(getContentPane(), message,
                            "", JOptionPane.INFORMATION_MESSAGE);

                    System.out.println("zla odpowiedz");
                }

                currentPanelIndex+=1;
                currentPanel=questionPanels.get(currentPanelIndex);
                cardLayout.next(cards);

            }
        });

        newQuizButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {

            }
        });

        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        getContentPane().add(cards, BorderLayout.CENTER);
    }

    private void setTextOnElements(){

        newQuizButton.setText(bundle.getRb().getString("Reset"));
        checkButton.setText(bundle.getRb().getString("Check"));
        setTitle(bundle.getRb().getString("Title"));

        newQuizButton.repaint();
        checkButton.repaint();
    }


}