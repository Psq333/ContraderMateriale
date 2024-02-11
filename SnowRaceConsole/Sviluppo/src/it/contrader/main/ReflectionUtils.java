package it.contrader.main;

import java.lang.reflect.Constructor;

/**
 * Utility class for reflection operations.
 * This class includes methods to perform dynamic operations such as instantiating classes by their names.
 *
 * @param <T> the type of the class on which the operations are to be performed
 */
public class ReflectionUtils<T> {

    /**
     * This method instantiates a class dynamically based on the class name provided.
     * It utilizes reflection to create a new instance of the class.
     *
     * @param nomeClasse The fully qualified name of the class to be instantiated.
     * @return The new instance of the class, or null if instantiation fails due to any reason.
     */
    public static Object instantiateClass(String nomeClasse){
        try {
            Class<?> clazz = Class.forName(nomeClasse);
            Constructor<?> ctor = clazz.getDeclaredConstructor();
            return ctor.newInstance();
        } catch (Throwable e) {
            return null;
        }
    }
}
