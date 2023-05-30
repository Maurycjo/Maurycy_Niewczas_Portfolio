package pwr.edu.pl.parser;

import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import pwr.edu.pl.football.BipPoznanPl;
import pwr.edu.pl.football.Items;

import java.io.File;
import java.io.StringWriter;


public class JAXBParser implements XmlParser{


    File file;
    JAXBContext jaxbContext;
    String outputToDisplay;
    BipPoznanPl bipPoznanPl = null;

    @Override
    public void load(File file) {

        this.file = file;

    }

    @Override
    public String getOutput() {
        if(outputToDisplay == null){
            outputToDisplay = "First deserialize";
        }

        return outputToDisplay;

    }

    @Override
    public void serialize() {

        outputToDisplay = null;
        Marshaller marshaller = null;
        StringWriter stringWriter = new StringWriter();
        try {
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(bipPoznanPl, stringWriter);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        outputToDisplay = stringWriter.toString();

    }

    @Override
    public void deserialize() {

        outputToDisplay = null;
        try {
            jaxbContext = JAXBContext.newInstance(BipPoznanPl.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            bipPoznanPl = (BipPoznanPl) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        Items items = bipPoznanPl.getData().getInformationCards().getItems();

        StringBuilder stringBuilder = new StringBuilder();

            for(var card : items.getCardList()){
                stringBuilder.append(card).append("\n");
            }

            outputToDisplay = stringBuilder.toString();

    }

}
