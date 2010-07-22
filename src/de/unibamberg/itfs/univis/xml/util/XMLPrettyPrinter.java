package de.unibamberg.itfs.univis.xml.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;

/**
 *
 * @author gtudan
 */
public class XMLPrettyPrinter {

    public static void printXML(Document doc) {
        StringWriter out = new StringWriter();
        Source source = new DOMSource(doc);
        Result result = new StreamResult(out);

        Transformer transformer;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XMLPrettyPrinter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(XMLPrettyPrinter.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(out.toString());
    }
}
