package com.example.victor_pc.tictactoe.edit;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.victor_pc.tictactoe.R;
import com.example.victor_pc.tictactoe.SessionManager;
import com.example.victor_pc.tictactoe.databinding.ActivityEditBinding;
import com.example.victor_pc.tictactoe.model.Session;
import com.example.victor_pc.tictactoe.model.User;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityEditBinding mBinding;
    private EditViewModel mViewModel = new EditViewModel(this);
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_edit);
        session = new SessionManager(getApplicationContext());
        initListener();
        mBinding.setViewModel(new User());
        mBinding.setViewModel(mViewModel.getData());
    }

    private void initListener() {
        mBinding.btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mBinding.btnSave) {
            User user = mBinding.getViewModel();
            if(mViewModel.validationUser(user)) {
                mViewModel.updateUser(user);
                Session.email = user.getEmail();
                session.createLoginSession(user.getEmail());
                finish();
                startActivity(mViewModel.gotoHomeActivity());
            } else {
                Toast.makeText(this, "New email and password must be filled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
