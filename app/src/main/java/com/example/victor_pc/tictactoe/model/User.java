package com.example.victor_pc.tictactoe.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.victor_pc.tictactoe.BR;

public class User extends BaseObservable {

    private String username;
    private String email;
    private String password;
    private Integer totalMatch;
    private Integer win;
    private Integer lose;

    @Bindable
    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        notifyPropertyChanged(BR.username);
        return this;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
        return this;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
        return this;
    }

    @Bindable
    public Integer getTotalMatch() {
        return totalMatch;
    }

    public User setTotalMatch(Integer totalMatch) {
        this.totalMatch = totalMatch;
        notifyPropertyChanged(BR.totalMatch);
        return this;
    }

    @Bindable
    public Integer getWin() {
        return win;
    }

    public User setWin(Integer win) {
        this.win = win;
        notifyPropertyChanged(BR.win);
        return this;
    }

    @Bindable
    public Integer getLose() {
        return lose;
    }

    public User setLose(Integer lose) {
        this.lose = lose;
        notifyPropertyChanged(BR.lose);
        return this;
    }
}
