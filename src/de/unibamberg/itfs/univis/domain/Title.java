/*
 */

package de.unibamberg.itfs.univis.domain;

import javax.xml.bind.annotation.*;

/**
 * This class is broken, since SAX somehow gets confused by the structure
 * 
 * <Title>
 *  <title>the title</title>
 * </Title>
 *
 * @author gtudan
 */
@XmlRootElement(name="Title")
@XmlAccessorType(XmlAccessType.FIELD)
public class Title {
    @XmlAttribute
    String key;
    @XmlElement(name="parent-title")
    Title parentTitle;
    String text;
    String title;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Title getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(Title parentTitle) {
        this.parentTitle = parentTitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    
}
