package pl.pwr.edu.window;

import pl.pwr.edu.music.ArtistLoader;
import pl.pwr.edu.window.panel.AlbumQuestionPanel;
import pl.pwr.edu.window.panel.AuthorQuestionPanel;
import pl.pwr.edu.window.panel.QuestionPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;


public class MainWindow extends JFrame {

    CardLayout cardLayout = new CardLayout();
    JPanel cards = new JPanel(cardLayout); //panel that contains cards
    Bundle bundle = new Bundle();
    JPanel resultPanel = new JPanel();
    JTextField resultTextField = new JTextField();
    ArtistLoader artistLoaderFromFile = new ArtistLoader();
    JMenuBar menuBar;
    JMenuItem menuPl, menuEng;
    JButton newQuizButton = new JButton();
    JButton checkButton = new JButton();
    final int numberOfQuestion = 4;
    private int numberOfGoodAnswers=0;
    private int currentPanelIndex=0;
    ArrayList<QuestionPanel> questionPanels = new ArrayList<>();

    QuestionPanel currentPanel;


    public MainWindow()
    {

        setTextOnElements();
        setSize(600, 400);
        resultTextField.setEditable(false);
        resultTextField.setFont(new Font("Arial", 10, 20 ));
        resultPanel.add(resultTextField, BorderLayout.CENTER);

        createPanels();
        currentPanel= questionPanels.get(currentPanelIndex);

        menuBar = new JMenuBar();
        menuPl = new JMenuItem(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(bundle.language== Bundle.Language.ENGLISH){
                    bundle.changeLanguage();
                    setTextOnElements();
                    currentPanel.setBundle(bundle);
                    currentPanel.setLanguage();

                    if(currentPanelIndex==numberOfQuestion){
                        setTextOnResultPanel();
                    }
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

                    if(currentPanelIndex==numberOfQuestion){
                        setTextOnResultPanel();
                    }
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
                    numberOfGoodAnswers+=1;
                }
                else {
                    String message = currentPanel.getRejectedMessage();
                    JOptionPane.showMessageDialog(getContentPane(), message,
                            "", JOptionPane.INFORMATION_MESSAGE);
                }
                currentPanelIndex+=1;
                if(currentPanelIndex==numberOfQuestion){
                    checkButton.setVisible(false);
                    setTextOnResultPanel();
                }
                else{
                    currentPanel=questionPanels.get(currentPanelIndex);
                    currentPanel.setBundle(bundle);
                    currentPanel.setLanguage();
                }
                cardLayout.next(cards);
            }
        });

        newQuizButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                createPanels();
                checkButton.setVisible(true);
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

    private void createPanels(){

        numberOfGoodAnswers=0;
        currentPanelIndex=0;
        questionPanels.clear();
        cards.removeAll();

        questionPanels.add(new AuthorQuestionPanel(bundle, artistLoaderFromFile));
        questionPanels.add(new AlbumQuestionPanel(bundle, artistLoaderFromFile));
        questionPanels.add(new AuthorQuestionPanel(bundle, artistLoaderFromFile));
        questionPanels.add(new AlbumQuestionPanel(bundle, artistLoaderFromFile));

        for(int i=0;i<numberOfQuestion;i++){
            cards.add(questionPanels.get(i));
        }
        cards.add(resultPanel);
    }

    private void setTextOnResultPanel(){

        String resultMessage;
        if(numberOfGoodAnswers==0){
            resultMessage = bundle.getRb().getString("Result.zero");
        }
        else if(numberOfGoodAnswers==1){
            resultMessage = bundle.getRb().getString("Result.one");
        }
        else if(numberOfGoodAnswers<5){
            resultMessage = bundle.getRb().getString("Result.m");
        }
        else{
            resultMessage = bundle.getRb().getString("Result.g");
        }

        resultMessage=resultMessage.replace("...", Integer.toString(numberOfGoodAnswers));
        resultTextField.setText(resultMessage);

    }

}