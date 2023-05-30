package pwr.edu.pl.football;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Card {

    @XmlElement(name = "link")
    private String link;
    @XmlElement(name = "id")
    private String id;
    @XmlElement(name = "data")
    private String date;
    @XmlElement(name = "skrot_organizacja")
    private String shortcut;
    @XmlElement(name = "komponent_srodowiska")
    private String component;
    @XmlElement(name= "typ_karty")
    private String cardLetter;
    @XmlElement(name = "rodzaj_karty")
    private String cardType;
    @XmlElement(name = "nr_wpisu")
    private String number;

    @XmlElement(name = "znak_sprawy")
    private String sign;

    @XmlElement(name = "dane_wnioskodawcy")
    private String data;

    public Card(String link, String id, String date, String shortcut, String component, String cardLetter, String cardType, String number, String sign, String data) {
        this.link = link;
        this.id = id;
        this.date = date;
        this.shortcut = shortcut;
        this.component = component;
        this.cardLetter = cardLetter;
        this.cardType = cardType;
        this.number = number;
        this.sign = sign;
        this.data = data;
    }

    public Card() {
    }

    public String getLink() {
        return link;
    }


    public void setLink(String link) {
        this.link = link;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getShortcut() {
        return shortcut;
    }


    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }


    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }


    public String getCardLetter() {
        return cardLetter;
    }

    public void setCardLetter(String cardLetter) {
        this.cardLetter = cardLetter;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Card{" +
                "link='" + link + '\'' +
                ", id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", shortcut='" + shortcut + '\'' +
                ", component='" + component + '\'' +
                ", cardLetter='" + cardLetter + '\'' +
                ", cardType='" + cardType + '\'' +
                ", number='" + number + '\'' +
                ", sign='" + sign + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
