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

    public void checkLogin() {
        if(!this.isLoggedIn()) {
            Intent intent = new Intent(context, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            Session.email = getUserEmail();
        }
    }

    public void logoutUser() {
         editor.clear();
         editor.commit();
         Intent intent = new Intent(context, LoginActivity.class);
         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         context.startActivity(intent);
    }
}
