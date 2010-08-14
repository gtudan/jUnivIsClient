/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.unibamberg.itfs.univis;

import de.unibamberg.itfs.univis.domain.UnivIsEntity;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // Print help
        if (args.length < 2) {
            System.out.println("Please give an entity as first an a list of comma seperated parameters as second argument.");
            System.out.println("For example: java -jar UnivIsClient.jar Lecture name=ISDL,room=RZ0.05");
            System.exit(0);
        }

        // Get Class
        Class entityClass = Class.forName("de.unibamberg.itfs.univis.domain." + args[0]);
        if (entityClass == null || entityClass.getSuperclass() != UnivIsEntity.class) {
            System.out.println("First argument must be an UnivIsEntity (Lecture, Person, Room, Allocation)");
        }

        // Parse parameters
        Map<String,String> parameters = new HashMap<String,String> ();
        String[] stringParams = args[1].split("[,]");
        for (String s : stringParams){
            String[] keyValuePair = s.split("[=]");
            parameters.put(keyValuePair[0],keyValuePair[1]);
        }

        // Run Query
        UnivIsQuery q = conn.buildQuery(entityClass,parameters);
        List<? extends UnivIsEntity> list = q.execute();

        // Print results
        System.out.println("Found " + list.size() + " mathing items");
        for (UnivIsEntity entity : list){
            System.out.println("\n\n========================================");
            System.out.println(entity);
            System.out.println("========================================");
            Method[] methodList = entity.getClass().getMethods();

            for (Method m : methodList){
                String name = "";
                String value = "";
                if (m.getName().startsWith("get")) {
                    name = m.getName().substring(3, m.getName().length());
                    Object returnValue = null;
                    try {
                        returnValue = m.invoke(entity, new Object[0]);
                    } catch (Exception e){
                        // NOP
                    }

                    value = returnValue != null ? returnValue.toString() : "NULL";
                    System.out.println(name + ": " + value);
                }
            }
        }
    }
}
