package com.example.victor_pc.tictactoe.edit;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.victor_pc.tictactoe.database.DatabaseOpenHelper;
import com.example.victor_pc.tictactoe.home.HomeActivity;
import com.example.victor_pc.tictactoe.model.User;

public class EditViewModel {

    private Context context;
    private DatabaseOpenHelper databaseOpenHelper;

    public EditViewModel(Context context) {
        this.context = context;
        databaseOpenHelper = new DatabaseOpenHelper(context);
    }

    public User getData() {
        User user = databaseOpenHelper.getHomeData();
        return user;
    }

    public void updateUser(User user) {
        if(databaseOpenHelper.updateUserDatabase(user)) {
            Toast.makeText(context, "Update Success", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean validationUser(User user) {
        if(user.getEmail() == null || user.getEmail().equals("")) return false;
        if(user.getPassword() == null || user.getPassword().equals("")) return false;
        return true;
    }

    public Intent gotoHomeActivity() {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }
}
