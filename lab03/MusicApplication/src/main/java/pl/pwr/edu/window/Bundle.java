package pl.pwr.edu.window;

import java.util.Locale;
import java.util.ResourceBundle;

public class Bundle {

    public enum Language{
        POLISH,
        ENGLISH
    }

    Language language = Language.POLISH;
    private ResourceBundle rb;

    public Language getLanguage() {
        return language;
    }

    public ResourceBundle getRb() {
        return rb;
    }


    public Bundle(){

        rb = ResourceBundle.getBundle("MessageBundle_PL", new Locale("pl", "PL"));
    }

    public void changeLanguage(){

        if(language==Language.ENGLISH){
            rb = ResourceBundle.getBundle("MessageBundle_PL", new Locale("pl", "PL"));
            language=Language.POLISH;
        }
        else {
            rb = ResourceBundle.getBundle("MessageBundle_EN", new Locale("en", "EN"));
            language=Language.ENGLISH;
        }

    }

}
