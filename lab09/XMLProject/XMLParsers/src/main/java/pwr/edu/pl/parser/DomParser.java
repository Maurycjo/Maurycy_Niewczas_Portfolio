package pwr.edu.pl.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pwr.edu.pl.football.Card;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser extends XmlParser{

    private DocumentBuilder builder;
    private Document document;


    @Override
    public void load(File file) {

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            builder = documentBuilderFactory.newDocumentBuilder();
            document = builder.parse(file);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deserialize() {

        //DOM
        NodeList cardList = document.getElementsByTagName("karta_informacyjna");
        List <Card> cardArrayList= new ArrayList<>();

        for (int i = 0; i < cardList.getLength(); i++) {
            Node cardNode = cardList.item(i);
            if (cardNode.getNodeType() == Node.ELEMENT_NODE) {
                Element cardElement = (Element) cardNode;

                String link = getTextValue(cardElement, "link");
                String id = getTextValue(cardElement, "id");
                String date = getTextValue(cardElement, "data");
                String shortcut = getTextValue(cardElement, "skrot_organizacja");
                String component = getTextValue(cardElement, "komponent_srodowiska");
                String cardLetter = getTextValue(cardElement, "typ_karty");
                String cardType = getTextValue(cardElement, "rodzaj_karty");
                String number = getTextValue(cardElement, "nr_wpisu");
                String sign = getTextValue(cardElement, "znak_sprawy");
                String data = getTextValue(cardElement, "dane_wnioskodawcy");

                cardArrayList.add(new Card(link, id, date, shortcut, component, cardType, cardLetter, number, sign, data));

            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        for(var card : cardArrayList){
            stringBuilder.append(card).append("\n");
        }

        outputToDisplay = stringBuilder.toString();


    }

    private static String getTextValue(Element parentElement, String elementName) {
        NodeList nodeList = parentElement.getElementsByTagName(elementName);
        if (nodeList.getLength() > 0) {
            Element element = (Element) nodeList.item(0);
            return element.getTextContent();
        }
        return "";
    }


}
