package com.openclassrooms.entrevoisins.model.utils;

import com.google.gson.Gson;

/**
 * Created by Yann MANCEL on 02/07/2019.
 * Name of the project: Entrevoisins
 * Name of the package: com.openclassrooms.entrevoisins.model.utils
 */
public class JsonTools {

    /**
     * Converts a Java class to a Json format
     * @param object a {@link Object} for convertion
     * @return a {@link String} that contains the Json format
     */
    public static String convertJavaToJson(final Object object) {
        return new Gson().toJson(object);
    }

    /**
     * Converts a Json format to a Java class
     * @param json a {@link String} that contains the Json format
     * @param classType a {@link Class} type
     * @param <T> the class for conversion
     * @return the Java class
     */
    public static <T> T convertJsonToJava(final String json, Class<T> classType) {
        return new Gson().fromJson(json, classType);
    }
}
