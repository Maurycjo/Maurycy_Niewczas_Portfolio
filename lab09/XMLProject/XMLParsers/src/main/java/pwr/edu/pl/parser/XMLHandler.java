package pwr.edu.pl.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {

    private boolean isInCardElement = false;
    private StringBuilder currentElementValue;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("karta_informacyjna")) {
            isInCardElement = true;
        }
        currentElementValue = new StringBuilder();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentElementValue.append(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("karta_informacyjna")) {
            isInCardElement = false;
            System.out.println("Zakończono przetwarzanie karty informacyjnej");
        } else if (isInCardElement) {
            if (qName.equalsIgnoreCase("link")) {
                String link = currentElementValue.toString();
                System.out.println("Link: " + link);
            } else if (qName.equalsIgnoreCase("id")) {
                String id = currentElementValue.toString();
                System.out.println("ID: " + id);
            } else if (qName.equalsIgnoreCase("data")) {
                String data = currentElementValue.toString();
                System.out.println("Data: " + data);
            } else if (qName.equalsIgnoreCase("skrot_organizacja")) {
            String data = currentElementValue.toString();
            System.out.println("short " + data);
            } else if (qName.equalsIgnoreCase("komponent_srodowiska")) {
                String data = currentElementValue.toString();
                System.out.println("komponent" + data);
            } else if (qName.equalsIgnoreCase("typ_karty")) {
                String data = currentElementValue.toString();
                System.out.println("typ  " + data);
            } else if (qName.equalsIgnoreCase("rodzaj_karty")) {
                String data = currentElementValue.toString();
                System.out.println("rodzaj " + data);
            } else if (qName.equalsIgnoreCase("nr_wpisu")) {
                String data = currentElementValue.toString();
                System.out.println("nr " + data);
            }else if (qName.equalsIgnoreCase("znak_sprawy")) {
                String data = currentElementValue.toString();
                System.out.println("znak: " + data);
            }else if (qName.equalsIgnoreCase("dane_wnioskodawcy")) {
                String data = currentElementValue.toString();
                System.out.println("data " + data);
            }

        }
    }


}
