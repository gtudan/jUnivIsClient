package de.unibamberg.itfs.univis.tests;

import de.unibamberg.itfs.univis.xml.XMLParser;
import de.unibamberg.itfs.univis.domain.Room;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import static org.junit.Assert.*;

/**
 *
 * @author gtudan
 */
public class RoomParserTest {

    static Room room;

    public RoomParserTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        File file = new File("testFiles/room.xml");
        Document doc = new XMLParser().loadXml(file);
        NodeList nl = doc.getDocumentElement().getElementsByTagName("Room");

        JAXBContext context = JAXBContext.newInstance(Room.class);
        Unmarshaller um = context.createUnmarshaller();

        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            room = (Room) um.unmarshal(n);
        }
    }

    @Test
    public void testName() {
        assertEquals("PC - Pool", room.getName());
    }

    @Test
    public void testShortName() {
        assertEquals("RZ0.06", room.getShortName());
    }

    @Test
    public void testAddress() {
        assertEquals("Feldkirchenstraße 21", room.getAddress());
    }

    @Test
    public void testDescription() {
        assertEquals("Rechnerraum, 1 Beamer, Overheadprojektor, HiFi-Anlage", room.getDescription());
    }

    @Test
    public void testID() {
        assertEquals(40419904L, room.getId());
    }

    @Test
    public void testKey() {
        assertEquals("Room.wiai.zentr.zentr.rz006", room.getKey());
    }

    @Test
    public void testOrgName() {
        assertEquals("Fakultät  Wirtschaftsinformatik / Angewandte Informatik", room.getOrgName());
    }

    @Test
    public void testSize() {
        assertEquals(35, room.getSize());

    }

    @Test
    public void testOrgUnits() {
        String[] orgUnits = {"Fakultät  Wirtschaftsinformatik / Angewandte Informatik"};
        assertArrayEquals(orgUnits, room.getOrgUnits());
    }

    @Test
    public void testContacts() {
        assertFalse(room.getContacts().isEmpty());
        System.out.println(room.getContacts().get(0));
        assertEquals("Person.wiai.zentr.zentr.schaib", room.getContacts().get(0).getPerson().getKey());
    }

}
