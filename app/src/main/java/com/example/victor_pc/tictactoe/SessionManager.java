package com.example.victor_pc.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.victor_pc.tictactoe.login.LoginActivity;
import com.example.victor_pc.tictactoe.model.Session;

import java.util.HashMap;

public class SessionManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private Context context;

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "AppPref";
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String KEY_EMAIL = "email";

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String email) {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public String getUserEmail() {
        return pref.getString(KEY_EMAIL, null);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public boolean checkLogin() {
        if(this.isLoggedIn()) {
            return true;
        } else return false;
    }

    public boolean logoutUser() {
         if(this.isLoggedIn()) {
             editor.clear();
             editor.commit();
             return true;
         } else return false;
    }
}
