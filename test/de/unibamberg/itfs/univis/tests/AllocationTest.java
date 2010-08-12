package de.unibamberg.itfs.univis.tests;

import de.unibamberg.itfs.univis.domain.Allocation;
import de.unibamberg.itfs.univis.domain.Person;
import de.unibamberg.itfs.univis.xml.XMLParser;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author gtudan
 */
public class AllocationTest {

    private static Allocation allocation;

    @BeforeClass
    public static void setUpClass() throws Exception {
        File file = new File("testFiles/allocations.xml");
        Document doc = new XMLParser().loadXml(file);
        NodeList nl = doc.getDocumentElement().getElementsByTagName("Allocation");

        JAXBContext context = JAXBContext.newInstance("de.unibamberg.itfs.univis.domain");
        Unmarshaller um = context.createUnmarshaller();

        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            allocation = (Allocation) um.unmarshal(n);
        }
    }

    @Test
    public void testTitle() {
        assertEquals("Algorithmen und Datenstrukturen (Übung, Gruppe 7)", allocation.getTitle());
    }

    @Test
    public void testRooms() {
        assertFalse(allocation.getRooms().isEmpty());
        assertEquals("RZ0.06", allocation.getRooms().get(0).getRoom().getShortName());
    }

    @Test
    public void testContact() {
        assertEquals("Person.wiai.bereic.lehrst_1.blankd", allocation.getContact().getPerson().getKey());
    }

    @Test
    public void testEnddate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        assertNotNull(allocation.getEndDate());
        assertEquals("2010-05-28", sdf.format(allocation.getEndDate()));
    }

    @Test
    public void testStartdate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        assertNotNull(allocation.getStartDate());
        assertEquals("2010-05-28", sdf.format(allocation.getStartDate()));
    }

    @Test
    public void testEndTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        assertNotNull(allocation.getEndDate());
        assertEquals("10:00", sdf.format(allocation.getEndDate()));
    }

    @Test
    public void testStartTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        assertNotNull(allocation.getStartDate());
        assertEquals("08:00", sdf.format(allocation.getStartDate()));
    }
}
