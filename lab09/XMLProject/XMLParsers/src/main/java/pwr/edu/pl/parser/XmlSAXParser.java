package pwr.edu.pl.parser;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class XmlSAXParser implements XmlParser{

    File file;
    @Override
    public void load(File file) {
    this.file = file;

    }

    @Override
    public String getOutput() {
        return null;
    }

    @Override
    public void serialize() {

    }

    @Override
    public void deserialize() {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser parser;
        try {
            XMLHandler handler = new XMLHandler();
            parser = saxParserFactory.newSAXParser();
            parser.parse(file, handler);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
