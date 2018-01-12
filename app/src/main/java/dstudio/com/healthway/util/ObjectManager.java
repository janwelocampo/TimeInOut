package dstudio.com.healthway.util;

import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by janwelcris on 10/19/2017.
 */

public class ObjectManager {
    private SharedPreferences sharedPreferences;
    private Gson gson;

    public ObjectManager(SharedPreferences sharedPreferences, Gson gson) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
    }

    public  void setString(String key, String value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    public Gson getGson (){
        return  gson;
    }

    public  boolean getBoolean(String key, boolean defValue) {
        return sharedPreferences.getBoolean(key, defValue);
    }

    public  void setBoolean(String key, boolean value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    public Integer getInt(String key, int defValue) {
        return sharedPreferences.getInt(key, defValue);
    }

    public  void setInt(String key, Integer value) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putInt(key, value).commit();
    }


}
