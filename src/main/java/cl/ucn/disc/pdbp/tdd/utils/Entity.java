package cl.ucn.disc.pdbp.tdd.utils;

import org.apache.commons.lang3.builder.MultilineRecursiveToStringStyle;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Entity of a
 */
public final class Entity {

    /**
     * This method print the atributes of any object, independent of the type of class.
     * @param object
     * @return
     */
    public static String toString(Object object){

        return ReflectionToStringBuilder.toString(object, new MultilineRecursiveToStringStyle());
    }
}
