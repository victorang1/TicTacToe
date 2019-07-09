package com.example.victor_pc.tictactoe.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.victor_pc.tictactoe.BR;

import java.nio.ByteOrder;

public class Board extends BaseObservable {

    public boolean isFilled = false;
    public boolean playerTile = false;
    public boolean botTile = false;

    @Bindable
    public boolean isFilled() {
        return isFilled;
    }

    public Board setFilled(boolean filled) {
        isFilled = filled;
        notifyPropertyChanged(BR.filled);
        return this;
    }

    @Bindable
    public boolean isPlayerTile() {
        return playerTile;
    }

    public Board setPlayerTile(boolean playerTile) {
        this.playerTile = playerTile;
        notifyPropertyChanged(BR.playerTile);
        return this;
    }

    @Bindable
    public boolean isBotTile() {
        return botTile;
    }

    public Board setBotTile(boolean botTile) {
        this.botTile = botTile;
        notifyPropertyChanged(BR.botTile);
        return this;
    }
}
