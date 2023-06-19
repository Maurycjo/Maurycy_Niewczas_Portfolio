package pwr.edu.pl.parser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class XmlSAXParser extends XmlParser{

    File file;
    private String outputToDisplay = null;
    @Override
    public void load(File file) {
    this.file = file;

    }

    @Override
    public String getOutput() {
        return outputToDisplay;
    }

    @Override
    public void deserialize() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser parser;

        try {
            XMLHandler handler = new XMLHandler();
            parser = saxParserFactory.newSAXParser();
            parser.parse(file, handler);


            StringBuilder stringBuilder = new StringBuilder();

            for(var card : handler.getCardList()){
                stringBuilder.append(card).append("\n");
            }

            this.outputToDisplay = stringBuilder.toString();

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
