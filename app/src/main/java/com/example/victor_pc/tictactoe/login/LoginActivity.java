package com.example.victor_pc.tictactoe.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.victor_pc.tictactoe.MainActivity;
import com.example.victor_pc.tictactoe.R;
import com.example.victor_pc.tictactoe.SessionManager;
import com.example.victor_pc.tictactoe.databinding.ActivityLoginBinding;
import com.example.victor_pc.tictactoe.home.HomeActivity;
import com.example.victor_pc.tictactoe.model.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityLoginBinding mBinding;
    private LoginViewModel mViewModel = new LoginViewModel(this);
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        session = new SessionManager(getApplicationContext());
        mBinding.setViewModel(new User());
        initListener();
    }

    private void initListener() {
        mBinding.btnLogin.setOnClickListener(this);
        mBinding.txtGotoRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mBinding.btnLogin) {
            User user = mBinding.getViewModel();
            if(mViewModel.validationUser(user)) {
                if(mViewModel.loginUser(user)) {
                    session.createLoginSession(user.getEmail());
                    finish();
                    startActivity(mViewModel.gotoHomeActivity());
                }
            } else{
                Toast.makeText(this, "All field must be filled", Toast.LENGTH_SHORT).show();
            }
        } else if(v == mBinding.txtGotoRegister) {
            finish();
            startActivity(mViewModel.gotoRegisterActivity());
        }
    }
}
