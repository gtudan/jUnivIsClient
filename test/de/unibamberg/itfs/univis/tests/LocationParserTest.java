/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.unibamberg.itfs.univis.tests;

import de.unibamberg.itfs.univis.domain.Person;
import de.unibamberg.itfs.univis.domain.Location;
import de.unibamberg.itfs.univis.xml.XMLParser;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gtudan
 */
public class LocationParserTest {

    Person person;
    Location location;

    public LocationParserTest() throws Exception {
        File file = new File("testFiles/room.xml");
        Document doc = new XMLParser().loadXml(file);
        NodeList nl = doc.getDocumentElement().getElementsByTagName("Person");

        JAXBContext context = JAXBContext.newInstance("de.unibamberg.itfs.univis.domain");
        Unmarshaller um = context.createUnmarshaller();

        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            person = (Person) um.unmarshal(n);
            location = person.getLocations().get(0);
        }
    }

    @Test
    public void testEmail() {
        assertEquals("dekanat.wiai@uni-bamberg.de", location.getEmail());
    }

    
}
