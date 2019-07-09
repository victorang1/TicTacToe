package com.example.victor_pc.tictactoe.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.example.victor_pc.tictactoe.BR;
import com.example.victor_pc.tictactoe.R;

import java.util.ArrayList;
import java.util.List;

public class GameBoard extends BaseObservable {

    public static String botName;

    public static List<Board> listBoard = new ArrayList<>();

    private static int eventId;

    public boolean playerTurnVisibility;
    public boolean botTurnVisibility;
    public boolean statusVisibility;
    public boolean yourTurn;
    public boolean isFinish;

    public static final int PLAYING = 0;
    public static final int WIN = 1;
    public static final int LOSE = 2;
    public static final int DRAW = 3;

    @Bindable
    public String getBotName() {
        return botName;
    }

    public GameBoard setBotName(String botName) {
        this.botName = botName;
        notifyPropertyChanged(BR.botName);
        return this;
    }

    @Bindable
    public static int getEventId() {
        return eventId;
    }

    public GameBoard setEventId(int eventId) {
        this.eventId = eventId;
        notifyPropertyChanged(BR.eventId);
        notifyPropertyChanged(BR.statusVisibility);
        return this;
    }

    @Bindable
    public boolean isYourTurn() {
        return yourTurn;
    }

    public GameBoard setYourTurn(boolean yourTurn) {
        this.yourTurn = yourTurn;
        notifyPropertyChanged(BR.yourTurn);
        notifyPropertyChanged(BR.botTurnVisibility);
        notifyPropertyChanged(BR.playerTurnVisibility);
        return this;
    }

    @Bindable
    public List<Board> getListBoard() {
        return listBoard;
    }

    public GameBoard setListBoard(List<Board> listBoard) {
        this.listBoard = listBoard;
        notifyPropertyChanged(BR.listBoard);
        return this;
    }

    @Bindable
    public int getPlayerTurnVisibility() {
        return yourTurn ? View.VISIBLE : View.GONE;
    }

    public GameBoard setTurnVisibility(boolean playerTurnVisibility) {
        this.playerTurnVisibility = playerTurnVisibility;
        notifyPropertyChanged(BR.playerTurnVisibility);
        return this;
    }

    @Bindable
    public int getBotTurnVisibility() {
        return yourTurn ? View.GONE : View.VISIBLE;
    }

    public GameBoard setBotTurnVisibility(boolean botTurnVisibility) {
        this.botTurnVisibility = botTurnVisibility;
        notifyPropertyChanged(BR.botTurnVisibility);
        return this;
    }

    @Bindable
    public int getStatusVisibility() {
        return this.eventId == 0 ? View.GONE : View.VISIBLE;
    }

    public GameBoard setStatusVisibility(boolean statusVisibility) {
        this.statusVisibility = statusVisibility;
        notifyPropertyChanged(BR.statusVisibility);
        return this;
    }

    @Bindable
    public boolean isFinish() {
        return isFinish;
    }

    public GameBoard setFinish(boolean finish) {
        isFinish = finish;
        notifyPropertyChanged(BR.finish);
        return this;
    }
}
