package pwr.edu.pl.parser;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringWriter;

public class XSLTParser{



    public String transform(){

        Result result = null;
        StringWriter stringWriter = null;
        try {
            String xsltPath = "/home/mniewczas/Desktop/xml/abcd.xsl";
            String xmlPath = "/home/mniewczas/Desktop/xml/bip.poznan.pl.xml";

            File xsltFile = new File(xsltPath);
            File xmlFile = new File(xmlPath);


            Source xsltSource = new StreamSource(xsltFile);
            Source xmlSource = new StreamSource(xmlFile);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(xsltSource);

            stringWriter = new StringWriter();
            Result htmlResult = new StreamResult(stringWriter);
            transformer.transform(xmlSource, htmlResult);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringWriter.toString();
    }

    public static void main(String[] args) {
        XSLTParser xsltParser = new XSLTParser();
        xsltParser.transform();
    }

}
