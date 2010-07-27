/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.unibamberg.itfs.univis.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author gtudan
 */
public class XMLParser {

    private List<XMLProcessor> preProcessors = new ArrayList();

    public XMLParser() {
        //NOTE: the intendProcessor is quiet expensive. Since Univis does
        //not intend its output, it might not be needed.
        this.preProcessors.add(new IntendRemovingProcessor());
        this.preProcessors.add(new UnivIsRefProcessor());
    }



    /**
     * Register a new PreProcessor
     * @param p the PreProcessor to register
     */
    public void registerPreprocessor(XMLProcessor p) {
        preProcessors.add(p);
    }

    /**
     * Get the list of preProcessors
     * This list is unmutable, use register/deregister method for changing
     * @return an unmutable list of processors
     */
    public List<XMLProcessor> getPreprocessors() {
        return Collections.unmodifiableList(preProcessors);
    }

    /**
     * Unregister a preprocessor
     * @param p the preprocessor to remove
     */
    public void unregisterPreprocessor(XMLProcessor p) {
        preProcessors.remove(p);
    }

    private Document preprocess(Document doc) {
        for (XMLProcessor p : preProcessors) {
            p.process(doc);
        }
        return doc;
    }

    /**
     * Parses an inputsource to a document for processing
     * 
     * @param is an InputStream for reading the data from
     * @return the document or null if an error occured
     */
    public Document loadXml(InputStream is) {
        DocumentBuilder docB;
        Document doc = null;
        try {
            docB = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = docB.parse(is);
        } catch (ParserConfigurationException ex) {
            String msg = "A problem occured while configuring the docBuilder.";
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, msg, ex);
        } catch (IOException ex) {
            String msg = "The XML-input could not be read. Check path or url.";
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, msg, ex);
        } catch (SAXException ex) {
            String msg = "Error parsing the XML. Check your input source.";
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, msg, ex);
        }

        // Send the  file through the preProcessors
        if (doc != null) {
            preprocess(doc);
        }
        
        return doc;
    }

    public Document loadXml(File file) {
        Document doc = null;
        try {
            doc = this.loadXml(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            String msg = "File " + file.getPath() + " could not be found!";
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, msg, ex);
        }

        return doc;
    }

    public Document loadXml(URL url) {
        Document doc = null;
        try {
            doc = this.loadXml(url.openStream());
        } catch (IOException ex) {
            String msg = "A problem occured while trying to access url " + url.toString();
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, msg, ex);
        }
        return doc;
    }
}
