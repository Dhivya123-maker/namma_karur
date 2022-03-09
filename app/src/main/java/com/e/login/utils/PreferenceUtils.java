package com.e.login.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
public class PreferenceUtils {
    private static String token = null;

    private static  String email = null;
    private static  String phone = null;
    private static int length;
    private static String id;
    private static Context context;
    private static String goo_token = null;
    private static String goo_id = null;


    public  PreferenceUtils(){

    }

    public static boolean saveToken(String token, Context context) {
        PreferenceUtils.token = token;
        PreferenceUtils.context = context;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.KEY_TOKEN, token);
        prefsEditor.apply();
        return true;
    }

    public static String getToken(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(Constants.KEY_TOKEN, null);
    }



    public static boolean saveid(String id, Context context) {
        PreferenceUtils.id = id;
        PreferenceUtils.context = context;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.KEY_ID, id);
        prefsEditor.apply();
        return true;
    }

    public static String getid(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(Constants.KEY_ID, null);
    }









    public static boolean saveToken1(String goo_token, Context context) {
        PreferenceUtils.goo_token = goo_token;
        PreferenceUtils.context = context;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.KEY_TOKEN1, goo_token);
        prefsEditor.apply();
        return true;
    }

    public static String getToken1(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(Constants.KEY_TOKEN1, null);
    }



    public static boolean saveid1(String goo_id, Context context) {
        PreferenceUtils.goo_id = goo_id;
        PreferenceUtils.context = context;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.KEY_ID1, goo_id);
        prefsEditor.apply();
        return true;
    }

    public static String getid1(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(Constants.KEY_ID1, null);
    }












//
    public static boolean saveEmail(String email, Context context) {
        PreferenceUtils.email = email;
        PreferenceUtils.context = context;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.putString(Constants.KEY_EMAIL, email);
        prefsEditor.apply();
        return true;
    }

    public static String getEmail(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getString(Constants.KEY_EMAIL, null);
    }



//
//    public static boolean savePhone(String phone, Context context) {
//        PreferenceUtils.phone = phone;
//        PreferenceUtils.context = context;
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        SharedPreferences.Editor prefsEditor = prefs.edit();
//        prefsEditor.putString(Constants.KEY_PHONE, phone);
//        prefsEditor.apply();
//        return true;
//    }
//
//    public static String getPhone(Context context) {
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        return prefs.getString(Constants.KEY_PHONE, null);
//    }


}
