package de.unibamberg.itfs.univis.xml;

import org.w3c.dom.Document;

/**
 *
 * @author gtudan
 */
public interface XMLProcessor {

    /**
     * Do something with the XML, but make sure it still passes validation!
     * @param doc the Document to process
     */
    public void process (Document doc);
}
