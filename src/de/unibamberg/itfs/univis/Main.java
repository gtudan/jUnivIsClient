/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.unibamberg.itfs.univis;

import de.unibamberg.itfs.univis.domain.Room;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author gtudan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        File file = new File("testFiles/room.xml");
        DocumentBuilder docb = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = docb.parse(file);
        NodeList nl = doc.getDocumentElement().getChildNodes();

        for (int i = 0; i < nl.getLength(); i++) {
            Node n = nl.item(i);
            JAXBContext context = JAXBContext.newInstance(Class.forName("de.unibamberg.itfs.univis.domain.Room"));

            Unmarshaller um = context.createUnmarshaller();
            if (n.getNodeName().equals("Room")) {
                Room room = (Room) um.unmarshal(n);
                System.out.println(room.getOrgUnits()[0]);
            }
        }

    }
}
