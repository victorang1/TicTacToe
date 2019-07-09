package com.example.victor_pc.tictactoe.home;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.victor_pc.tictactoe.R;
import com.example.victor_pc.tictactoe.SessionManager;
import com.example.victor_pc.tictactoe.databinding.ActivityHomeBinding;
import com.example.victor_pc.tictactoe.model.Session;
import com.example.victor_pc.tictactoe.model.User;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityHomeBinding mBinding;
    private HomeViewModel mViewModel = new HomeViewModel(this);
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        session = new SessionManager(getApplicationContext());
        checkSession();
        mBinding.setViewModel(new User());
        initListener();
    }

    private void checkSession() {
        if(session.checkLogin()) {
            Session.email = session.getUserEmail();
        } else {
            finish();
            startActivity(mViewModel.gotoLoginActivity());
        }
    }

    public void initListener() {
        mBinding.fab.setOnClickListener(this);
        mBinding.btnPlay.setOnClickListener(this);
        mBinding.btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mBinding.fab) {
            finish();
            startActivity(mViewModel.gotoEditActivity());
        } else if (v == mBinding.btnPlay) {
            finish();
            startActivity(mViewModel.gotoGameActivity());
        } else if(v == mBinding.btnLogout) {
            finish();
            userLogout();
        }
    }

    private void userLogout() {
        Session.email = null;
        if(session.logoutUser()) {
            startActivity(mViewModel.gotoLoginActivity());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.setViewModel(mViewModel.getData());
    }

    @Override
    public void onBackPressed() {}
}
