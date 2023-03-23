package pl.pwr.edu.window.panel;

import pl.pwr.edu.music.ArtistFromApi;
import pl.pwr.edu.music.ArtistLoader;
import pl.pwr.edu.window.Bundle;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.ResourceBundle;

public abstract class QuestionPanel extends JPanel {

    JTextField questionTextField=new JTextField();
    final int numOfAnswerRadioButtons = 4;
    int randomCorrectNumber;
    ArtistLoader artistLoader;
    Bundle bundle;
    JRadioButton[] radioButtons;

    ArtistFromApi currentArtist;

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    public QuestionPanel(Bundle bundle, ArtistLoader artistLoader){

        this.artistLoader =artistLoader;
        this.bundle=bundle;
        setLayout(new BorderLayout());

        loadArtist();
        randomizeAnswer();
        addComponentsToPanel();

    }

    private void loadArtist(){

        this.artistLoader.shuffleArtistArrayList();
        String artistName = artistLoader.getArtistArrayList().get(0).getArtistName();
        String url = this.artistLoader.getArtistArrayList().get(0).getUrl();
        currentArtist = new ArtistFromApi(artistName, url);
    }

    private void randomizeAnswer(){

        Random random = new Random();
        randomCorrectNumber=random.nextInt(numOfAnswerRadioButtons);

    }

    private void addComponentsToPanel(){

        add(questionTextField);
        questionTextField.setEditable(false);
        questionTextField.setFont(new Font("Arial", 10, 20 ));

        JPanel radioButtonPanel = new JPanel(new GridLayout(numOfAnswerRadioButtons, 1));
        radioButtons = new JRadioButton[numOfAnswerRadioButtons];

        final ButtonGroup group = new ButtonGroup();
        for(int i=0;i<numOfAnswerRadioButtons;i++){

            radioButtons[i] = new JRadioButton();

            //JButton checkButton = null;
            group.add(radioButtons[i]);
            radioButtonPanel.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);

        add(questionTextField, BorderLayout.NORTH);
        add(radioButtonPanel, BorderLayout.CENTER);

    }

    public boolean checkIfCorrectSelect(){
        return radioButtons[randomCorrectNumber].isSelected();
    }

    public abstract void loadQuestion();

    public abstract void setLanguage();

    public abstract String getAcceptedMessage();

    public abstract String getRejectedMessage();
}
