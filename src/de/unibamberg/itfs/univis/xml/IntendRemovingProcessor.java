/*
 */
package de.unibamberg.itfs.univis.xml;

import java.util.logging.Level;
import java.util.logging.Logger;
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
public class IntendRemovingProcessor implements XMLProcessor {

    public void process(Document doc) {
        try {
            doc.normalizeDocument();
            XPathFactory xpathFactory = XPathFactory.newInstance();

            // XPath to find empty text nodes.
            XPathExpression xpathExp = xpathFactory.newXPath().compile("//text()[normalize-space(.) = '']");
            NodeList emptyTextNodes = (NodeList) xpathExp.evaluate(doc, XPathConstants.NODESET);

            // Remove each empty text node from document.
            for (int i = 0; i < emptyTextNodes.getLength(); i++) {
                Node emptyTextNode = emptyTextNodes.item(i);
                emptyTextNode.getParentNode().removeChild(emptyTextNode);
            }
        } catch (XPathExpressionException ex) {
            Logger.getLogger(IntendRemovingProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
