package com.aetorresv.baseproject.utils;


public class Properties {
    public static final Boolean production = Boolean.TRUE;

    public final static String BASE_PRE = "http://107.170.50.162";
    public final static String PRODUCTION  ="/";
    public final static String DEVELOPMENT ="/_dev";

    public final static String APPSESSION_NAME = "APP_Pref_File";

    public final static String PRODUCTION_GCM_ID = "";
    public final static String DEVELOPMENT_GCM_ID = "";

    public final static String URL_BASE = production ? (BASE_PRE + PRODUCTION) : (BASE_PRE + DEVELOPMENT) ;
    public final static String PUSH_SENDER_ID = production ? PRODUCTION_GCM_ID : DEVELOPMENT_GCM_ID;

    public final static String IS_LOGGED_KEY  = "LOGGK";
}
