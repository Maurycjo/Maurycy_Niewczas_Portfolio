package pwr.edu.pl.football;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Data {

    @XmlElement(name = "karty_informacyjne")
    private InformationCards informationCards;

    public InformationCards getInformationCards() {
        return informationCards;
    }

    public void setInformationCards(InformationCards informationCards) {
        this.informationCards = informationCards;
    }
}
