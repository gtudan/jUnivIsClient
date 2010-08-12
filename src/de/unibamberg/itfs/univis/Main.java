/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.unibamberg.itfs.univis;

import de.unibamberg.itfs.univis.domain.Lecture;
import java.util.List;

/**
 *
 * @author gtudan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // this should be autoinjected by grails in production....
        UnivIsConnection conn = new UnivIsConnection("http://univis.uni-bamberg.de");
        
        UnivIsQuery q = conn.buildQuery(Lecture.class);
        q.addParameter("name", "--Seminar / Proseminar II / Übung");
       // q.setSemester("2010w");
        List list = q.execute();

        System.out.println(list.size());
        System.out.println(list.get(0).getClass().getSimpleName() + ((Lecture) list.get(0)).getKey());
    }
}
