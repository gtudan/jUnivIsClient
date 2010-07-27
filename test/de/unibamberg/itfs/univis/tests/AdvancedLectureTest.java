/*
 */

package de.unibamberg.itfs.univis.tests;

import de.unibamberg.itfs.univis.domain.Lecture;
import de.unibamberg.itfs.univis.xml.XMLParser;
import java.awt.event.ItemEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
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
public class AdvancedLectureTest {

    private static Lecture parentLecture;
    private static Lecture childLecture;
    public AdvancedLectureTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
         File file = new File("testFiles/advancedLecture.xml");
        Document doc = new XMLParser().loadXml(file);
        NodeList nl = doc.getDocumentElement().getElementsByTagName("Lecture");

        JAXBContext context = JAXBContext.newInstance(Lecture.class);
        Unmarshaller um = context.createUnmarshaller();

        for (int i=0; i<nl.getLength();i++) {
            //System.out.println(nl.item(i).getAttributes().getNamedItem("key").getTextContent());
        }
        childLecture = (Lecture) um.unmarshal(nl.item(0));
        parentLecture = childLecture.getParentLecture();
        
    }


    @Test
    public void testKey(){
        assertEquals("Lecture.sprach.abteil_1.zentr.tutori", childLecture.getKey());
        assertEquals("Lecture.sprach.abteil_1.zentr.spanis_1", parentLecture.getKey());
    }

    @Test
    public void testParentLecture(){
        assertNotNull(childLecture.getParentLecture());
    }

    @Test
    public void testCourses(){
        assertEquals(3, parentLecture.getCourses().size());
    }

    @Test
    public void testFacultative() {
        assert parentLecture.isFacultative();
    }
    
    @Test
    public void testCertificate() {
        assert parentLecture.isCertificate();
    }

    @Test
    public void testBeginners() {
        assert parentLecture.isBeginners();
    }

    @Test
    public void testEcts(){
        assert parentLecture.isEcts();
    }

    @Test
    public void testEctsCredits(){
        assertEquals(4, parentLecture.getEctsCredits());
    }

    @Test
    public void testTurnout(){
        assertEquals(25, parentLecture.getTurnout());
    }

    @Test
    public void testComment(){
        assert parentLecture.getComment().startsWith("Verwendbarkeit:");
    }

    @Test
    public void testOrganizational(){
        assert parentLecture.getOrganizational().startsWith("Der Kurs richtet sich");
    }

    @Test
    public void testStartDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("2010-11-04",sdf.format(childLecture.getStartDate()));
    }

    @Test
    public void testEndDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        assertEquals("2011-01-20",sdf.format(childLecture.getEndDate()));
    }

}