package com.openclassrooms.entrevoisins.model.utils;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

/**
 * Created by Yann MANCEL on 02/07/2019.
 * Name of the project: Entrevoisins
 * Name of the package: com.openclassrooms.entrevoisins.model.utils
 */
public class SaveTools {

    /**
     * Save {@link String} thanks to {@link SharedPreferences}
     * @param preferences a {@link SharedPreferences}
     * @param key {@link String} that contains the key
     * @param value {@link String} that contains the value
     */
    public static void saveSharedPreferences(@NonNull final SharedPreferences preferences, final String key, final String value) {
        // Creates an Editor to modify the stored data
        SharedPreferences.Editor editor = preferences.edit();

        // Stores a String into the Editor (key/value system)
        editor.putString(key, value);

        // Throws the backup -> you can also use editor.commit(); (to write the data to disk synchronously)
        editor.apply();
    }
}
