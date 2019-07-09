package com.example.victor_pc.tictactoe.game;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.victor_pc.tictactoe.R;
import com.example.victor_pc.tictactoe.databinding.ActivityGameBinding;
import com.example.victor_pc.tictactoe.model.GameBoard;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityGameBinding mBinding;
    private GameViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_game);
    }

    private void initListener() {
        mBinding.square1.setOnClickListener(this);
        mBinding.square2.setOnClickListener(this);
        mBinding.square3.setOnClickListener(this);
        mBinding.square4.setOnClickListener(this);
        mBinding.square5.setOnClickListener(this);
        mBinding.square6.setOnClickListener(this);
        mBinding.square7.setOnClickListener(this);
        mBinding.square8.setOnClickListener(this);
        mBinding.square9.setOnClickListener(this);
        mBinding.btnPlay.setOnClickListener(this);
        mBinding.btnQuit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int index = 0;
        if(v == mBinding.square1) {
            index = 0;
        }
        else if(v == mBinding.square2) {
            index = 1;
        }
        else if(v == mBinding.square3) {
            index = 2;
        }
        else if(v == mBinding.square4) {
            index = 3;
        }
        else if(v == mBinding.square5) {
            index = 4;
        }
        else if(v == mBinding.square6) {
            index = 5;
        }
        else if(v == mBinding.square7) {
            index = 6;
        }
        else if(v == mBinding.square8) {
            index = 7;
        }
        else if(v == mBinding.square9) {
            index = 8;
        }
        int eventId = mViewModel.getEvent();
        if(eventId == GameBoard.PLAYING) {
            inputBoard(index);
        }
        if(eventId == GameBoard.PLAYING) {
            inputBoard(mViewModel.getBotChoice());
        }
        else if(v == mBinding.btnPlay) {
            restartGame();
        }
        else if(v == mBinding.btnQuit) {
            finish();
            startActivity(mViewModel.gotoHomeActivity());
        }
    }

    private void inputBoard(int position) {
        if(position == -1) {

        }
        else if(mViewModel.checkBoard(position)) {
            mViewModel.setTileOwner(position);
            if(mViewModel.isPlayerTurn()) {
                if(position == 0) mBinding.square1.setBackgroundResource(R.drawable.playertile);
                else if(position == 1) mBinding.square2.setBackgroundResource(R.drawable.playertile);
                else if(position == 2) mBinding.square3.setBackgroundResource(R.drawable.playertile);
                else if(position == 3) mBinding.square4.setBackgroundResource(R.drawable.playertile);
                else if(position == 4) mBinding.square5.setBackgroundResource(R.drawable.playertile);
                else if(position == 5) mBinding.square6.setBackgroundResource(R.drawable.playertile);
                else if(position == 6) mBinding.square7.setBackgroundResource(R.drawable.playertile);
                else if(position == 7) mBinding.square8.setBackgroundResource(R.drawable.playertile);
                else if(position == 8) mBinding.square9.setBackgroundResource(R.drawable.playertile);
            } else {
                if(position == 0) mBinding.square1.setBackgroundResource(R.drawable.bottile);
                else if(position == 1) mBinding.square2.setBackgroundResource(R.drawable.bottile);
                else if(position == 2) mBinding.square3.setBackgroundResource(R.drawable.bottile);
                else if(position == 3) mBinding.square4.setBackgroundResource(R.drawable.bottile);
                else if(position == 4) mBinding.square5.setBackgroundResource(R.drawable.bottile);
                else if(position == 5) mBinding.square6.setBackgroundResource(R.drawable.bottile);
                else if(position == 6) mBinding.square7.setBackgroundResource(R.drawable.bottile);
                else if(position == 7) mBinding.square8.setBackgroundResource(R.drawable.bottile);
                else if(position == 8) mBinding.square9.setBackgroundResource(R.drawable.bottile);
            }
            checkEvent();
            if(mViewModel.getEvent() == GameBoard.PLAYING) {
                mViewModel.changeTurn();
            }
        }
    }

    private void checkEvent() {
        int eventId = mViewModel.getEvent();
        if(eventId != GameBoard.PLAYING) {
            if(eventId == GameBoard.DRAW) {
                mBinding.status.setText(R.string.youDraw);
            } else if(eventId == GameBoard.LOSE) {
                mBinding.status.setText(R.string.youLose);
            } else if(eventId == GameBoard.WIN) {
                mBinding.status.setText(R.string.youWin);
            }
            disableLayoutTouch();
            mViewModel.updateStatus(mViewModel.getEvent());
        }
    }

    private void restartGame() {
        initListener();
        mViewModel = new GameViewModel(this);
        mViewModel.onGameStart();
        mBinding.setBoard(mViewModel.generateBotName());
        resetAllColor();
        if(!mViewModel.isPlayerTurn()) inputBoard(mViewModel.getBotChoice());
        enableLayoutTouch();
    }

    private void resetAllColor() {
        mBinding.square1.setBackgroundResource(R.drawable.game_border);
        mBinding.square2.setBackgroundResource(R.drawable.game_border);
        mBinding.square3.setBackgroundResource(R.drawable.game_border);
        mBinding.square4.setBackgroundResource(R.drawable.game_border);
        mBinding.square5.setBackgroundResource(R.drawable.game_border);
        mBinding.square6.setBackgroundResource(R.drawable.game_border);
        mBinding.square7.setBackgroundResource(R.drawable.game_border);
        mBinding.square8.setBackgroundResource(R.drawable.game_border);
        mBinding.square9.setBackgroundResource(R.drawable.game_border);
    }

    private void disableLayoutTouch() {
        mBinding.parentButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    private void enableLayoutTouch() {
        mBinding.parentButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        restartGame();
    }

    @Override
    public void onBackPressed() {}
}
