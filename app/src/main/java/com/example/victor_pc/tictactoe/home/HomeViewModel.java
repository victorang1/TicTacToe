package com.example.victor_pc.tictactoe.home;

import android.content.Context;
import android.content.Intent;
import com.example.victor_pc.tictactoe.database.DatabaseOpenHelper;
import com.example.victor_pc.tictactoe.edit.EditActivity;
import com.example.victor_pc.tictactoe.game.GameActivity;
import com.example.victor_pc.tictactoe.login.LoginActivity;
import com.example.victor_pc.tictactoe.model.Session;
import com.example.victor_pc.tictactoe.model.User;

public class HomeViewModel {

    private Context context;
    private DatabaseOpenHelper databaseOpenHelper;

    public HomeViewModel(Context context) {
        this.context = context;
        databaseOpenHelper = new DatabaseOpenHelper(context);
    }

    public User getData() {
        User user = databaseOpenHelper.getHomeData();
        return user;
    }

    public Intent gotoEditActivity() {
        Intent intent = new Intent(context, EditActivity.class);
        return intent;
    }

    public Intent gotoGameActivity() {
        Intent intent = new Intent(context, GameActivity.class);
        return intent;
    }

    public Intent gotoLoginActivity() {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }
}
