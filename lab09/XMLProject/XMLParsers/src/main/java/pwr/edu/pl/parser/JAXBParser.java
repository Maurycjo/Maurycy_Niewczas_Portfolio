package pwr.edu.pl.parser;

import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import pwr.edu.pl.football.BipPoznanPl;
import pwr.edu.pl.football.Items;

import java.io.StringWriter;


public class JAXBParser extends XmlParser{


    JAXBContext jaxbContext;
    String outputToDisplay;
    BipPoznanPl bipPoznanPl = null;


    @Override
    public String getOutput() {
        if(outputToDisplay == null){
            outputToDisplay = "Najpierw Deserializacja";
        }

        return outputToDisplay;

    }


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

        if(file == null){
            outputToDisplay = "Wczytaj plik";
            return;
        }
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
