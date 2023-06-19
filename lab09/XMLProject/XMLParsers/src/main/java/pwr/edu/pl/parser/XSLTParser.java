package pwr.edu.pl.parser;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringWriter;

public class XSLTParser extends XmlParser{

    private File xsltFile;

    @Override
    public String getOutput() {
        return outputToDisplay;
    }

    @Override
    public void load(File file) {
        super.load(file);
    }

    public void loadXsltFile(File file){
        this.xsltFile = file;
    }

    @Override
    public void deserialize() {

        StringWriter stringWriter = null;
        try {

            if(xsltFile == null){
                outputToDisplay = "Wczytaj plik XSLT";
                return;
            }

            Source xsltSource = new StreamSource(xsltFile);
            Source xmlSource = new StreamSource(file);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(xsltSource);

            stringWriter = new StringWriter();
            Result htmlResult = new StreamResult(stringWriter);
            transformer.transform(xmlSource, htmlResult);

        } catch (Exception e) {
            e.printStackTrace();
        }
        this.outputToDisplay = stringWriter.toString();
    }
}
