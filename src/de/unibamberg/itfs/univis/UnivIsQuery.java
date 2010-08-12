package de.unibamberg.itfs.univis;

import de.unibamberg.itfs.univis.domain.UnivIsEntity;
import de.unibamberg.itfs.univis.xml.XMLParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author Gregor Tudan
 */
public final class UnivIsQuery<E extends UnivIsEntity> {

    private UnivIsConnection conn;
    private String database;
    private String semester = "";
    private Map<String, String> parameters = new HashMap<String, String>();
    Class c;

    // CONSTRUCTORS
    public UnivIsQuery(UnivIsConnection conn, Class c) {
        this.conn = conn;
        this.database = c.getSimpleName().toLowerCase() + 's';
    }

    public UnivIsQuery(UnivIsConnection conn, Class c, String semester) {
        this.conn = conn;
        this.database = c.getSimpleName().toLowerCase() + 's';
        this.setSemester(semester);
    }

    public UnivIsQuery(UnivIsConnection conn, Class c, Map<String, String> parameters) {
        this(conn, c);
        this.setParameters(parameters);
    }

    public UnivIsQuery(UnivIsConnection conn, Class c, Map<String, String> parameters, String semester) {
        this(conn, c, parameters);
        this.setSemester(semester);
    }
    // END CONSTRUCTORS

    public String getSemester() {
        return this.semester;
    }

    /**
     * Set the semester for searching
     * @param semester should be a year (4 digits) followed by 's' or 'w' (ie: '2010w')
     */
    public final void setSemester(String semester) {
        if (Pattern.matches("\\d{4}[ws]", semester)) {
            this.semester = semester;
        } else {
            throw new IllegalArgumentException("SemesterString should be a year (4digits) followed by 's' or 'w' (ie '2010w')");
        }
    }

    /**
     * Remove the semester-parameter from the query and search the default (probably the current) semester
     */
    public void clearSemester() {
        this.semester = "";
    }

    private String buildSearchString(Map<String, String> parameters) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();

        for (String s : parameters.keySet()) {
            sb.append(s);
            sb.append('=');
            sb.append(URLEncoder.encode(parameters.get(s), "UTF-8"));
            sb.append('&');
        }
        // Drop the last '&'
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public List<E> execute() throws JAXBException, IOException {
        List<E> resultList = new ArrayList<E>();

        // Initiale Unmarshaller
        JAXBContext context = JAXBContext.newInstance("de.unibamberg.itfs.univis.domain");
        Unmarshaller um = context.createUnmarshaller();

        // Fetch and parse
        InputStream io = conn.fetch(database, this.buildSearchString(parameters), semester);
        Document doc = new XMLParser().loadXml(io);
        NodeList nl = doc.getDocumentElement().getChildNodes();

        for (int i = 0; i < nl.getLength(); i++) {
            resultList.add((E) um.unmarshal(nl.item(i)));
        }

        return resultList;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(String key, String value) {
        parameters.put(key, value);
    }

    public void removeParameter(String key) {
        parameters.remove(key);
    }
}
