package de.unibamberg.itfs.univis.tests;


import de.unibamberg.itfs.univis.domain.Lecture;
import de.unibamberg.itfs.univis.xml.XMLParser;
import java.io.File;
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
public class LectureTest {

    private static Lecture lecture;

    @BeforeClass
    public static void setUpClass() throws Exception {
        File file = new File("testFiles/lecture.xml");
        Document doc = new XMLParser().loadXml(file);
        NodeList nl = doc.getDocumentElement().getElementsByTagName("Lecture");

        JAXBContext context = JAXBContext.newInstance(Lecture.class);
        Unmarshaller um = context.createUnmarshaller();

        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            lecture = (Lecture) um.unmarshal(n);
        }
    }
    
    @Test
    public void testOrigin() {
        assertEquals("flexnow:16459", lecture.getOrigin());
    }

    @Test
    public void testGuestAuditors() {
        assertTrue(lecture.isGuestAuditors());
    }

    @Test
    public void testClassification(){
        assertNotNull(lecture.getClassification());
    }

    @Test
    public void testLecturers(){
        assertFalse(lecture.getLecturers().isEmpty());
        assertEquals("Person.wiai.bereic_2.lehrst.weitze", lecture.getLecturers().get(0).getPerson().getKey());
    }


    @Test
    public void testLiterature(){
        assert(lecture.getLiterature().contains("Henderson, B.D. und Venkatraman, N.: \"Strategic alignment: leveraging information technology for transforming organizations\", in: IBM Systems Journal (32:1) 1993, S. 4-16."));
    }

    @Test
    public void testName(){
        assertEquals("ISDL-3-M: Informationssysteme in Dienstleistungsbereichen III: IT-Wertschöpfung",lecture.getName());
    }

    @Test
    public void testSummary(){
        assert(lecture.getSummary().startsWith("Gegenstand der Lehrveranstaltung sind Ansätze,"));
    }

    @Test
    public void testSws(){
        assertEquals(2, lecture.getSws());
    }

    @Test
    public void testType(){
        assertEquals("V",lecture.getType());
    }

    @Test
    public void testTerms(){
        assertFalse(lecture.getTerms().isEmpty());
    }

}
