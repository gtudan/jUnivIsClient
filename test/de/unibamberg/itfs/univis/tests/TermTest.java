/*
 */
package de.unibamberg.itfs.univis.tests;

import java.text.SimpleDateFormat;
import de.unibamberg.itfs.univis.xml.XMLParser;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import de.unibamberg.itfs.univis.domain.Term;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gtudan
 */
public class TermTest {

    private static Term term;
    private final SimpleDateFormat SDF_TIME = new SimpleDateFormat("HH:mm");
    private final SimpleDateFormat SDF_DATE = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeClass
    public static void setUpClass() throws Exception {
        File file = new File("testFiles/term.xml");
        Document doc = new XMLParser().loadXml(file);
        NodeList nl = doc.getDocumentElement().getElementsByTagName("term");

        JAXBContext context = JAXBContext.newInstance(Term.class);
        Unmarshaller um = context.createUnmarshaller();

        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            term = (Term) um.unmarshal(n);
        }
    }

    @Test
    public void testStartTime() {
        assertEquals("16:00", SDF_TIME.format(term.getStartTime()));
    }

    @Test
    public void testEndTime() {
        assertEquals("18:00", SDF_TIME.format(term.getEndTime()));
    }

    @Test
    public void testEndStart() {
        assertEquals("2010-07-08", SDF_DATE.format(term.getStartDate()));
    }

    @Test
    public void testEndDate() {
        assertEquals("2010-07-08", SDF_DATE.format(term.getEndDate()));
    }

    @Test
    public void exclude(){
        assertEquals("vac", term.getExclude());
    }

    @Test
    public void testRepeat(){
        assertEquals("s1", term.getRepeat());
    }

    @Test
    public void testRoom(){
        assertNotNull(term.getRoom());
        assertEquals("Room.wiai.zentr.zentr.f381", term.getRoom().getKey());
    }
}
