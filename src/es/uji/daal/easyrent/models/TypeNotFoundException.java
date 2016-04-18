package es.uji.daal.easyrent.models;

/**
 * Created by alberto on 21/03/16.
 */
public class TypeNotFoundException extends Exception {
    public TypeNotFoundException(String serializedType) {
        super("No type was found for: " + serializedType);
    }
}
