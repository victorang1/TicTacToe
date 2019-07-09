package com.example.victor_pc.tictactoe.login;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.victor_pc.tictactoe.database.DatabaseOpenHelper;
import com.example.victor_pc.tictactoe.home.HomeActivity;
import com.example.victor_pc.tictactoe.model.Session;
import com.example.victor_pc.tictactoe.model.User;
import com.example.victor_pc.tictactoe.register.RegisterActivity;

public class LoginViewModel {

    private Context context;
    private DatabaseOpenHelper databaseOpenHelper;

    public LoginViewModel(Context context) {
        this.context = context;
        this.databaseOpenHelper = new DatabaseOpenHelper(context);
    }

    public boolean loginUser(User user) {
        if(databaseOpenHelper.loginUserWithDb(user)) {
            Session.email = user.getEmail();
            Log.d("<login>", Session.email);
            Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(context, "Email or password is incorrect", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean validationUser(User user) {
        if(user.getEmail() == null || user.getEmail().equals("")) return false;
        if(user.getPassword() == null || user.getPassword().equals("")) return false;
        return true;
    }

    public Intent gotoRegisterActivity() {
        Intent intent = new Intent(context, RegisterActivity.class);
        return intent;
    }

    public Intent gotoHomeActivity() {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }
}
