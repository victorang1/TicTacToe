package com.example.victor_pc.tictactoe.register;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.victor_pc.tictactoe.R;
import com.example.victor_pc.tictactoe.databinding.ActivityRegisterBinding;
import com.example.victor_pc.tictactoe.model.User;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegisterBinding mBinding;
    private RegisterViewModel mViewModel = new RegisterViewModel(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        mBinding.setViewModel(new User());
        initListener();
    }

    public void initListener() {
        mBinding.btnRegister.setOnClickListener(this);
        mBinding.txtGotoLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mBinding.btnRegister) {
            User user = mBinding.getViewModel();
            if(mViewModel.validationUser(user)) {
                mViewModel.insertUser(user);
                finish();
                startActivity(mViewModel.gotoLoginActivity());
            } else{
                Toast.makeText(this, "All field must be filled", Toast.LENGTH_SHORT).show();
            }
        } else if (v == mBinding.txtGotoLogin) {
            finish();
            startActivity(mViewModel.gotoLoginActivity());
        }
    }
}
