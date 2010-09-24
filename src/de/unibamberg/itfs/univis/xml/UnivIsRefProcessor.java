package de.unibamberg.itfs.univis.xml;

import de.unibamberg.itfs.univis.xml.util.XMLPrettyPrinter;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.log4j.Logger;
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
        Logger log = Logger.getLogger(this.getClass().getName());

    /**
     * This replaces all UnivIsRefs with the real entities from the document
     *
     * I could not get the references to work with the ID/IDREF annotations
     * and typesafe parsing would have been very hard to implement. Since I
     * don't think that cyclic references will occur, let's reconstruct the
     * original doc....
     * 
     * @param doc
     * @throws XPathExpressionException
     */
    public void process(Document doc) {

		if (log.isDebugEnabled()) {
			// This is expensive, so check if it is really necessary
			log.debug("Document before changing: \n" + XMLPrettyPrinter.printXML(doc));
		}
		Set<String> ignoredKeyReferences = new LinkedHashSet<String>();
        Set<Node> cleanup = new LinkedHashSet<Node>();
        XPath xpath = XPathFactory.newInstance().newXPath();

        try {
            // Find all references
            XPathExpression xPathRefNodes = xpath.compile("//UnivISRef");
            NodeList refNodes;
            
            // FIXME: This is a real resource hog, since it continues to recurse into nodes that will be replaced anyway.
            // Referenced nodes from the doc-root should be removed immediatly
            do {
                refNodes = (NodeList) xPathRefNodes.evaluate(doc, XPathConstants.NODESET);
                // replace references by the originals from the doc root
                for (int i = 0; i < refNodes.getLength(); i++) {

                    Node n = refNodes.item(i);

                    String type = n.getAttributes().getNamedItem("type").getTextContent();
                    String key = n.getAttributes().getNamedItem("key").getTextContent();
					
					// if we already failed to resolve this key, skip
					if (ignoredKeyReferences.contains(key)) continue;
					
                    XPathExpression xPathReferedNode = xpath.compile("/UnivIS/" + type + "[@key='" + key + "']");

                    Node referencedNode = (Node) xPathReferedNode.evaluate(doc, XPathConstants.NODE);
					
					if (referencedNode != null) {
						n.getParentNode().replaceChild(referencedNode.cloneNode(true), n);
						cleanup.add(referencedNode);
					} else {
						log.warn("Could not find node of type " + type + " with key " + key + " when trying to resolve references!");
						ignoredKeyReferences.add(key);
					}
                }
            } while (refNodes.getLength() != 0 && allRefNodesIgnored(refNodes, ignoredKeyReferences) == false );

        } catch (XPathExpressionException ex) {
            log.error(ex);
        }


        // Remove referenced Nodes from DocRoot
        if (REMOVE_REFERED_NODES) {
            for (Node n : cleanup) {
                doc.getDocumentElement().removeChild(n);
            }
        }

		if (log.isDebugEnabled()) {
			// This is expensive, so check if it is really necessary
			log.debug("Document after changing: \n" + XMLPrettyPrinter.printXML(doc));
		}
    }
	
	/**
	 * This checks if the remaining nodes can be resolved or if they are ignored
	 */
	private boolean allRefNodesIgnored(NodeList refNodes, Set<String> ignoredKeyReferences) {
		if (refNodes == null || refNodes.getLength() == 0 || 
			ignoredKeyReferences == null || ignoredKeyReferences.size() == 0) {
			return false;
		} else {
			for (int i=0;i<refNodes.getLength();i++){
				String key = refNodes.item(i).getAttributes().getNamedItem("key").getTextContent();
				if (!ignoredKeyReferences.contains(key)) {
						return false;
				} 
			}
			return true;
		}
	}
}
