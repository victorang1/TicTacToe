package com.example.victor_pc.tictactoe.game;

import android.content.Context;
import android.content.Intent;

import com.example.victor_pc.tictactoe.database.DatabaseOpenHelper;
import com.example.victor_pc.tictactoe.home.HomeActivity;
import com.example.victor_pc.tictactoe.model.Board;
import com.example.victor_pc.tictactoe.model.GameBoard;

import java.util.List;
import java.util.Random;

public class GameViewModel {

    private Context context;
    private DatabaseOpenHelper databaseOpenHelper;
    private GameBoard gameBoard;

    public GameViewModel(Context context) {
        this.context = context;
        databaseOpenHelper = new DatabaseOpenHelper(context);
        gameBoard = new GameBoard();
    }

    public void onGameStart() {
        GameBoard.listBoard.clear();
        for(int i = 0; i < 9; i++) {
            GameBoard.listBoard.add(new Board());
        }
        generateTurn();
        gameBoard.setEventId(GameBoard.PLAYING);
    }

    public GameBoard generateBotName() {
        String[] listBotName = {"Hawari", "Kevin", "Victor", "Jonathan", "Arvin", "Teguh", "Dimas", "Stephan", "Albert",
                "Martin", "Fred", "Alfred", "Calvin", "Dean", "Chris"};
        return gameBoard.setBotName(listBotName[new Random().nextInt(listBotName.length)]);
    }

    private void generateTurn() {
        int index = new Random().nextInt(2);
        if(index == 0) {
            gameBoard.setYourTurn(true);
        } else if(index == 1) {
            gameBoard.setYourTurn(false);
        }
    }

    public boolean checkBoard(int position) {
        if(gameBoard.getListBoard().get(position).isFilled) {
            return false;
        }
        gameBoard.getListBoard().get(position).setFilled(true);
        return true;
    }

    public boolean isPlayerTurn() {
        if(gameBoard.isYourTurn()) {
            return true;
        }
        else return false;
    }

    public void setTileOwner(int position) {
        if(gameBoard.isYourTurn()) {
            gameBoard.getListBoard().get(position).setPlayerTile(true);
            gameBoard.getListBoard().get(position).setBotTile(false);
        } else {
            gameBoard.getListBoard().get(position).setBotTile(true);
            gameBoard.getListBoard().get(position).setPlayerTile(false);
        }
    }


    public Intent gotoHomeActivity() {
        Intent intent = new Intent(context, HomeActivity.class);
        return intent;
    }

    public boolean checkBotBoard() {
        int flag = 0;
        int temp = 0;
        int max = 3;
        int i;

        //Check left to right each row
        while (max <= 9) {
            for(i = temp; i < max; i++) {
                if(gameBoard.getListBoard().get(i).isFilled && gameBoard.getListBoard().get(i).isBotTile()) {
                    flag = 1;
                } else {
                    flag = 0;
                    break;
                }
            }
            if(flag == 1) break;

            temp = max;
            max = max + 3;
        }

        if(flag == 1) return true;

        flag = 0;
        temp = 0;

        //Check top to bottom each column
        while(temp <= 2) {
            for(i = temp; i < 9; i += 3) {
                if(gameBoard.getListBoard().get(i).isFilled && gameBoard.getListBoard().get(i).isBotTile()) {
                    flag = 1;
                } else {
                    flag = 0;
                    break;
                }
            }
            if(flag == 1) break;
            temp++;
        }

        if(flag == 1) return true;

        flag = 0;

        //Check diagonal from top left to bottom right
        for(i = 0; i < 9; i += 4) {
            if(gameBoard.getListBoard().get(i).isFilled && gameBoard.getListBoard().get(i).isBotTile()) {
                flag = 1;
            } else {
                flag = 0;
                break;
            }
        }

        if(flag == 1) return true;

        flag = 0;

        //Check diagonal from top right to bottom left
        for(i = 2; i <= 6; i += 2) {
            if(gameBoard.getListBoard().get(i).isFilled && gameBoard.getListBoard().get(i).isBotTile()) {
                flag = 1;
            } else {
                flag = 0;
                break;
            }
        }

        if(flag == 1) return true;

        return false;
    }

    public boolean checkPlayerBoard() {

        int flag = 0;
        int temp = 0;
        int max = 3;
        int i;

        //Check left to right each row
        while (max <= 9) {
            for(i = temp; i < max; i++) {
                if(gameBoard.getListBoard().get(i).isFilled && gameBoard.getListBoard().get(i).isPlayerTile()) {
                    flag = 1;
                } else {
                    flag = 0;
                    break;
                }
            }
            if(flag == 1) break;

            temp = max;
            max = max + 3;
        }

        if(flag == 1) return true;

        flag = 0;
        temp = 0;

        //Check top to bottom each column
        while(temp <= 2) {
            for(i = temp; i < 9; i += 3) {
                if(gameBoard.getListBoard().get(i).isFilled && gameBoard.getListBoard().get(i).isPlayerTile()) {
                    flag = 1;
                } else {
                    flag = 0;
                    break;
                }
            }
            if(flag == 1) break;
            temp++;
        }

        if(flag == 1) return true;

        flag = 0;

        //Check diagonal from top left to bottom right
        for(i = 0; i < 9; i += 4) {
            if(gameBoard.getListBoard().get(i).isFilled && gameBoard.getListBoard().get(i).isPlayerTile()) {
                flag = 1;
            } else {
                flag = 0;
                break;
            }
        }

        if(flag == 1) return true;

        flag = 0;

        //Check diagonal from top right to bottom left
        for(i = 2; i <= 6; i += 2) {
            if(gameBoard.getListBoard().get(i).isFilled && gameBoard.getListBoard().get(i).isPlayerTile()) {
                flag = 1;
            } else {
                flag = 0;
                break;
            }
        }

        if(flag == 1) return true;

        return false;
    }

    public void changeTurn() {
        if(gameBoard.isYourTurn()) {
            gameBoard.setYourTurn(false);
        } else {
            gameBoard.setYourTurn(true);
        }
    }

    public boolean isFieldFull() {
        int flag = 0;
        for(int i = 0; i < 9; i++) {
            if(gameBoard.getListBoard().get(i).isFilled) {
                flag = 1;
            } else {
                flag = 0;
                break;
            }
        }
        if(flag == 1) return true;
        return false;
    }

    public int getEvent() {
        if(checkPlayerBoard() == true) {
            gameBoard.setEventId(GameBoard.WIN);
            gameBoard.setFinish(true);
        } else if(checkBotBoard() == true) {
            gameBoard.setEventId(GameBoard.LOSE);
            gameBoard.setFinish(true);
        } else if(isFieldFull()) {
            gameBoard.setEventId(GameBoard.DRAW);
            gameBoard.setFinish(true);
        }
        return gameBoard.getEventId();
    }

    public int getBotChoice() {
        if(!isPlayerTurn() && !isFieldFull()) {
            int index = 0;
            while(gameBoard.getListBoard().get(index).isFilled) {
                index = new Random().nextInt(9) ;
            }
            return index;
        }
        return -1;
    }

    public void updateStatus(int status) {
        databaseOpenHelper.insertStatus(status);
    }

    public boolean checkFinish() {
        if(gameBoard.isFinish()) {
            return true;
        } else return false;
    }
}
