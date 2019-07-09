package com.example.victor_pc.tictactoe.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.victor_pc.tictactoe.model.Session;
import com.example.victor_pc.tictactoe.model.User;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TicTacToe.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    private Cursor cursor;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "CREATE TABLE User(" +
                "Username TEXT," +
                "Email TEXT," +
                "Password TEXT," +
                "Matches INTEGER," +
                "Win INTEGER," +
                "Lose INTEGER)";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insertUserToDb(User user) {
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Username", user.getUsername());
            values.put("Email", user.getEmail());
            values.put("Password", user.getPassword());
            values.put("Matches", 0);
            values.put("Win", 0);
            values.put("Lose", 0);
            db.insert("User", null, values);
        } catch (Exception ex) {

        }
        return true;
    }

    public boolean loginUserWithDb(User user) {
        try{
            db = this.getReadableDatabase();
            String query = String.format("SELECT * FROM User WHERE Email=\"%s\" AND Password=\"%s\"", user.getEmail(), user.getPassword());
            cursor = db.rawQuery(query, new String[]{});
            if(cursor.moveToFirst()) {
                return true;
            }
            else return false;
        } catch (Exception ex) {

        }
        return false;
    }

    public User getHomeData() {
        User user = new User();
        db = this.getReadableDatabase();
        String query = String.format("SELECT * FROM User WHERE Email=\"%s\"", Session.email);
        cursor = db.rawQuery(query, new String[]{});
        if(cursor.moveToFirst()) {
            user.setUsername(cursor.getString(0));
            user.setEmail(cursor.getString(1));
            user.setTotalMatch(cursor.getInt(3));
            user.setWin(cursor.getInt(4));
            user.setLose(cursor.getInt(5));
        }
        return user;
    }

    public boolean updateUserDatabase(User user) {
        db = this.getWritableDatabase();
        String selection = "Email LIKE ?";
        String arg[] = {Session.email};
        Log.d("<Update>", Session.email + user.getEmail() + user.getPassword());
        ContentValues values = new ContentValues();
        values.put("Email", user.getEmail());
        values.put("Password", user.getPassword());
        db.update("User", values, selection, arg);
        return true;
    }

    public boolean insertStatus(int id) {
        User user = getHomeData();
        db = this.getWritableDatabase();
        int matches = user.getTotalMatch();
        String selection = "Email LIKE ?";
        String arg[] = {Session.email};
        ContentValues values = new ContentValues();
        values.put("Matches", matches+1);
        if(id == 1) {
            int win = user.getWin();
            values.put("Win", win+1);
        }
        else if(id == 2) {
            int lose = user.getLose();
            values.put("Lose", lose+1);
        }
        db.update("User", values, selection, arg);
        return true;
    }
}
