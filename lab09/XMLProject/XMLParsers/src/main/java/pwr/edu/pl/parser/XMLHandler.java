package pwr.edu.pl.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import pwr.edu.pl.football.Card;

import java.util.ArrayList;
import java.util.List;

public class XMLHandler extends DefaultHandler {

    private String mCurrentTagName;
    private Card mCard;
    String outputToDisplay =null;
    private List <Card> cardList = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        this.mCurrentTagName = qName;
        if("karta_informacyjna".equals(this.mCurrentTagName)){
            this.mCard = new Card();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {



        if("link".equals(this.mCurrentTagName)){
            String link = new String(ch, start, length);
            if(link.startsWith("https"))
                this.mCard.setLink(link);
        }
        if("id".equals(this.mCurrentTagName)){
            String id = new String(ch, start, length);
            this.mCard.setId(id);
        }
        if("data".equals(this.mCurrentTagName)){
            String date = new String(ch, start, length);
            if(mCard !=null){
                this.mCard.setDate(date);
            }
        }
        if("skrot_organizacja".equals(this.mCurrentTagName)){
            String shortcut = new String(ch, start, length);
            this.mCard.setShortcut(shortcut);
        }
        if("komponent_srodowiska".equals(this.mCurrentTagName)){
            String component = new String(ch, start, length);
            this.mCard.setComponent(component);
        }
        if("typ_karty".equals(this.mCurrentTagName)){
            String cardLetter = new String(ch, start, length);
            this.mCard.setCardLetter(cardLetter);
        }
        if("rodzaj_karty".equals(this.mCurrentTagName)){
            String cardType = new String(ch, start, length);
            this.mCard.setCardType(cardType);
        }
        if("nr_wpisu".equals(this.mCurrentTagName)){
            String number = new String(ch, start, length);
            this.mCard.setNumber(number);
        }
        if("znak_sprawy".equals(this.mCurrentTagName)){
            String sign = new String(ch, start, length);
            this.mCard.setSign(sign);
        }
        if("dane_wnioskodawcy".equals(this.mCurrentTagName)){
            String data = new String(ch, start, length);
            this.mCard.setData(data);
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("karta_informacyjna".equals(qName)) {
            this.cardList.add(this.mCard);
            this.mCard = null;
        }
        this.mCurrentTagName = null;
    }

    public List<Card> getCardList() {
        return cardList;
    }
}
