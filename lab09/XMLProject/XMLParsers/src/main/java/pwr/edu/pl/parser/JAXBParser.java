package pwr.edu.pl.parser;

import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import pwr.edu.pl.football.BipPoznanPl;
import pwr.edu.pl.football.Items;

import java.io.File;


public class JAXBParser implements XmlParser{


    JAXBParser(){

    }

    @Override
    public void load() {

        JAXBContext jaxbContext;
        File file;
        try {
            file = new File("/home/mniewczas/Desktop/xml/bip.poznan.pl.xml");
            jaxbContext = JAXBContext.newInstance(BipPoznanPl.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            BipPoznanPl bipPoznanPl = (BipPoznanPl) unmarshaller.unmarshal(file);

            Items items = bipPoznanPl.getData().getInformationCards().getItems();

            for(var card : items.getCardList()){
                System.out.println(card.getData());
            }

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void display() {

    }

    @Override
    public void serialize() {

    }

    @Override
    public void deserialize() {

    }

    public static void main(String[] args) {

        JAXBParser jaxbParser = new JAXBParser();
        jaxbParser.load();

    }


}
