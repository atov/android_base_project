package com.aetorresv.baseproject.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by AntonioTorres on 13/01/16.
 */
public class AppSessionStore {
    private static AppSessionStore mInstance = null;
    public static final String KEY = Properties.APPSESSION_NAME;
    private Context context = null;

    private AppSessionStore(Context contextIn){
        this.context = contextIn;
    }

    public synchronized static AppSessionStore getInstance(Context contextIn){
        if(mInstance == null)
        {
            mInstance = new AppSessionStore(contextIn);
        }
        return mInstance;
    }

    public void resetPreferences(){
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE).edit();
        editor.clear().commit();
    }

    public boolean save(Map<String,String> data){
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE).edit();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            editor.putString(entry.getKey(), entry.getValue());
        }
        return editor.commit();
    }

    public  boolean save(String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public  boolean save( String key, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public  boolean save( String key, Boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public  boolean save( String key, float value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE).edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    public  boolean save( String key, long value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE).edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public  String restore( String key) {
        SharedPreferences savedSession = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE);
        return savedSession.getString(key, null);
    }

    public  String restore( String key , String defaultValue) {
        SharedPreferences savedSession = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE);
        return savedSession.getString(key, defaultValue);
    }

    public static String restore(Context context,  String key) {
        SharedPreferences savedSession = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE);
        return savedSession.getString(key, null);
    }

    public  boolean restoreBoolean( String key) {
        SharedPreferences savedSession = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE);
        return savedSession.getBoolean(key, false);
    }

    public  int restoreInteger( String key) {
        SharedPreferences savedSession = context.getSharedPreferences(
                KEY, Context.MODE_PRIVATE);
        return savedSession.getInt(key, 0);
    }

    public  int restoreInteger( String key, Integer defaultValue) {
        SharedPreferences savedSession = context.getSharedPreferences(
                KEY, Context.MODE_PRIVATE);
        return savedSession.getInt(key, defaultValue);
    }

    public  float restoreFloat( String key) {
        SharedPreferences savedSession = context.getSharedPreferences(
                KEY, Context.MODE_PRIVATE);
        return savedSession.getFloat(key, 0f);
    }

    public  long restoreLong( String key) {
        SharedPreferences savedSession = context.getSharedPreferences(KEY,
                Context.MODE_PRIVATE);
        return savedSession.getLong(key, 0L);
    }

    public void setSigned(boolean signed){
        save(Properties.IS_LOGGED_KEY, signed);
    }

    public boolean isSigned(){
        return restoreBoolean(Properties.IS_LOGGED_KEY);
    }
}
