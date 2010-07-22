package de.unibamberg.itfs.univis.xml;

import de.unibamberg.itfs.univis.xml.util.XMLPrettyPrinter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author gtudan
 */
public class UnivIsRefProcessor implements XMLProcessor {

    private final boolean REMOVE_REFERED_NODES = true;

    /**
     * This replaces all UnivIsRefs with the real entities from the document
     * 
     * @param doc
     * @throws XPathExpressionException
     */
    public void process(Document doc) {

        // System.out.println("Document before changing:");
        // printXML(doc);
        List<Node> cleanup = new ArrayList<Node>();
        XPath xpath = XPathFactory.newInstance().newXPath();

        try {
            // Find all references
            XPathExpression xPathRefNodes = xpath.compile("//UnivISRef");
            NodeList refNodes = (NodeList) xPathRefNodes.evaluate(doc, XPathConstants.NODESET);
            // replace references by the originals from the doc root
            for (int i = 0; i < refNodes.getLength(); i++) {
                Node n = refNodes.item(i);
                String type = n.getAttributes().getNamedItem("type").getTextContent();
                String key = n.getAttributes().getNamedItem("key").getTextContent();
                XPathExpression xPathReferedNode = xpath.compile("/UnivIS/" + type + "[@key='" + key + "']");
                Node referencedNode = (Node) xPathReferedNode.evaluate(doc, XPathConstants.NODE);
                n.getParentNode().replaceChild(referencedNode.cloneNode(true), n);
                cleanup.add(referencedNode);
            }

        } catch (XPathExpressionException ex) {
            Logger.getLogger(UnivIsRefProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Remove referenced Nodes from DocRoot
        if (REMOVE_REFERED_NODES) {
            for (Node n : cleanup) {
                doc.getDocumentElement().removeChild(n);
            }
        }

//        System.out.println("Document after changing:");
//        XMLPrettyPrinter.printXML(doc);
    }
}
