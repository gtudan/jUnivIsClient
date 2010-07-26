package de.unibamberg.itfs.univis.tests;

import de.unibamberg.itfs.univis.xml.XMLParser;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import de.unibamberg.itfs.univis.domain.Title;
import java.io.File;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gtudan
 */
public class TitleTest {

    private static Title title;

    @BeforeClass
    public static void setUpClass() throws Exception {
        File file = new File("testFiles/title.xml");
        Document doc = new XMLParser().loadXml(file);
        NodeList nl = doc.getDocumentElement().getElementsByTagName("Title");

        JAXBContext context = JAXBContext.newInstance(Title.class);
        Unmarshaller um = context.createUnmarshaller();

        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            title = (Title) um.unmarshal(n);
        }
    }

    @Test
    public void testKey() {
        assertEquals("Title.fakult.diplom", title.getKey());
    }

    @Test
    public void testTitle() {
        assertEquals("Bachelor-/Master-/Diplomstudiengänge Angewandte Informatik, Wirtschaftsinformatik, Wirtschaftspädagogik mit Schwerpunkt Wirtschaftsinformatik", title.getTitle());
    }

    @Test
    public void testParent() {
        assertNotNull(title.getParentTitle());
        assertEquals("Title.fakult", title.getParentTitle().getKey());
    }

    public void testText() {
        assert (title.getText().startsWith("Die Lehrveranstaltungen in den Fächern \"Betriebswirtschaftslehre\", "));
    }
}
