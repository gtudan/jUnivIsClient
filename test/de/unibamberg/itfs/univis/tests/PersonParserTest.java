/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.unibamberg.itfs.univis.tests;

import de.unibamberg.itfs.univis.domain.Person;
import de.unibamberg.itfs.univis.domain.Room;
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
public class PersonParserTest {

    Person person;

    public PersonParserTest() throws Exception {
        File file = new File("testFiles/room.xml");
        DocumentBuilder docb = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = docb.parse(file);
        NodeList nl = doc.getDocumentElement().getElementsByTagName("Person");

        JAXBContext context = JAXBContext.newInstance(Person.class);
        Unmarshaller um = context.createUnmarshaller();

        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            person = (Person) um.unmarshal(n);
        }
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @Test
    public void testID() {
        assertEquals(20254986L, person.getId());
    }

    @Test
    public void testKey() {
        assertEquals("Person.wiai.zentr.zentr.schaib", person.getKey());
    }

    @Test
    public void testFirstName() {
        assertEquals("Babette", person.getFirstName());
    }

    @Test
    public void testLastName() {
        assertEquals("Schaible", person.getLastName());
    }

    @Test
    public void testLocations(){
        assertFalse(person.getLocations().isEmpty());
    }
}
