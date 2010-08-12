package de.unibamberg.itfs.univis;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import org.apache.log4j.Logger;


/**
 *
 * @author Gregor Tudan
 */
public class UnivIsConnection {
    final String baseURL;
    Logger log = Logger.getLogger(this.getClass().getName());

    public UnivIsConnection(String url) {
        baseURL = url;
    }

    public UnivIsQuery buildQuery(Class c){
      return new UnivIsQuery(this, c);
    }

    public UnivIsQuery buildQuery(Class c, String semester) {
        return new UnivIsQuery(this, c , semester);
    }

    public UnivIsQuery buildQuery(Class c, Map<String,String> parameters) {
        return new UnivIsQuery(this, c, parameters);
    }

    public UnivIsQuery buildQuery(Class c, Map<String,String> parameters, String semester) {
        return new UnivIsQuery(this, c, parameters, semester);
    }


    protected InputStream fetch(String database, String searchString) throws IOException{
        return fetch(database, searchString, "");
    }

    protected InputStream fetch(String database, String searchString, String semester) throws IOException{
       semester = semester.isEmpty() ? "" : "&sem="+semester;
       URL url = new URL(baseURL + "/prg?" + "search=" + database + "&" + searchString + semester + "&show=xml");
       log.debug("Loading " + url.toString());
       return url.openConnection().getInputStream();
    }


}
