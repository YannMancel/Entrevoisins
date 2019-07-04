package com.openclassrooms.entrevoisins.model.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.util.List;

import static com.openclassrooms.entrevoisins.model.utils.JsonTools.convertJsonToJavaList;

/**
 * Created by Yann MANCEL on 02/07/2019.
 * Name of the project: Entrevoisins
 * Name of the package: com.openclassrooms.entrevoisins.model.utils
 */
public class SaveTools {

    // FIELDS --------------------------------------------------------------------------------------

    private static final String SAVE_FILE_NAME = "com.openclassrooms.entrevoisins.model.utils.SaveTools";

    // METHODS -------------------------------------------------------------------------------------

    // SAVE ****************************************************************************************
    /**
     * Saves a {@link String} thanks to {@link SharedPreferences}
     * @param context a {@link Context}
     * @param key a {@link String} that contains the key
     * @param value a {@link String} that contains the value
     */
    public static void saveSharedPreferences(final Context context, final String key, final String value) {
        // Creates an SharedPreferences thanks to the Context in argument
        SharedPreferences preferences = context.getSharedPreferences(SAVE_FILE_NAME, Context.MODE_PRIVATE);

        // Creates an Editor to modify the stored data
        SharedPreferences.Editor editor = preferences.edit();

        // Stores a String into the Editor (key/value system)
        editor.putString(key, value);

        // Throws the backup -> you can also use editor.commit(); (to write the data to disk synchronously)
        editor.apply();
    }

    /**
     * Saves a {@link Object} (Gson library) thanks to {@link SharedPreferences}
     * @param context a {@link Context}
     * @param object a {@link Object} to save in Json format
     * @param key a {@link String} that contains the key
     */
    public static void saveSharedPreferencesWithJson(final Context context, final Object object, final String key) {
        // Converts Object in Json format
        final String json = JsonTools.convertJavaToJson(object);

        // Save Json format with SharedPreferences
        SaveTools.saveSharedPreferences(context, key, json);
    }

    // LOAD ****************************************************************************************

    /**
     * Retrieves a {@link String} from {@link SharedPreferences}
     * @param context a {@link Context}
     * @param key a {@link String} that contains the key
     * @return a {@link String} that contains the value
     */
    @Nullable
    public static String loadStringFromSharedPreferences(final Context context, final String key) {
        // Creates an SharedPreferences thanks to the Context in argument
        SharedPreferences preferences = context.getSharedPreferences(SAVE_FILE_NAME, Context.MODE_PRIVATE);

        return preferences.getString(key, null);
    }

    /**
     * Retrieves {@link List} of T from {@link SharedPreferences}
     * @param context a {@link Context}
     * @param key a {@link String} that contains the key
     * @param <T> the class for conversion
     * @return a {@link List} of T
     */
    public static <T> List<T> loadListFromSharedPreferences(final Context context, final String key, Class<T> classOfT) {
        // Retrieves the String from the SharedPreferences
        String json = SaveTools.loadStringFromSharedPreferences(context, key);

        return (json == null) ? null : convertJsonToJavaList(json, classOfT);
    }

    // REMOVE **************************************************************************************

    /**
     * Removes data from {@link SharedPreferences}
     * @param context a {@link Context}
     * @param key a {@link String} that contains the key
     */
    public static void removeDataFromSharedPreferences(final Context context, final String key) {
        // Creates an SharedPreferences thanks to the Context in argument
        SharedPreferences preferences = context.getSharedPreferences(SAVE_FILE_NAME, Context.MODE_PRIVATE);

        // Creates an Editor object to modify the stored data
        SharedPreferences.Editor editor = preferences.edit();

        // Removes data
        editor.remove(key);

        // Throws the backup -> you can also use editor.commit(); (to write the data to disk synchronously)
        editor.apply();
    }
}
