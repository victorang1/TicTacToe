package com.example.victor_pc.tictactoe.register;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.victor_pc.tictactoe.database.DatabaseOpenHelper;
import com.example.victor_pc.tictactoe.login.LoginActivity;
import com.example.victor_pc.tictactoe.model.User;

public class RegisterViewModel {

    private Context context;
    private DatabaseOpenHelper databaseOpenHelper;

    public RegisterViewModel(Context context) {
        this.context = context;
        this.databaseOpenHelper = new DatabaseOpenHelper(context);
    }

    public void insertUser(User user) {
        if(databaseOpenHelper.insertUserToDb(user)) {
            Toast.makeText(context, "Registered Success", Toast.LENGTH_SHORT).show();
        }
    }

    public Intent gotoLoginActivity() {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    public boolean validationUser(User user) {
        if(user.getEmail() == null || user.getEmail().equals("")) return false;
        if(user.getPassword() == null || user.getPassword().equals("")) return false;
        if(user.getUsername() == null || user.getUsername().equals("")) return false;
        return true;
    }
}
