package com.example.victor_pc.tictactoe.game;

import android.content.Context;
import android.content.Intent;

import com.example.victor_pc.tictactoe.database.DatabaseOpenHelper;
import com.example.victor_pc.tictactoe.home.HomeActivity;
import com.example.victor_pc.tictactoe.model.Board;
import com.example.victor_pc.tictactoe.model.GameBoard;
import com.example.victor_pc.tictactoe.model.User;

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

    public int checkPlayerBoard() {
        int flag = 0;

        //Check every row
        for(int i = 0; i < 9; i += 3) {
            if(gameBoard.getListBoard().get(i).isFilled) {
                if(gameBoard.getListBoard().get(i).isPlayerTile() &&
                        gameBoard.getListBoard().get(i+1).isPlayerTile() &&
                        gameBoard.getListBoard().get(i+2).isPlayerTile()) {
                    flag = 1;
                    break;
                } else if(gameBoard.getListBoard().get(i).isBotTile() &&
                        gameBoard.getListBoard().get(i+1).isBotTile() &&
                        gameBoard.getListBoard().get(i+2).isBotTile()) {
                    flag = 2;
                    break;
                }
            }
        }

        if(flag == 1 || flag == 2) {
            return flag;
        }

        flag = 0;

        //Check every column
        for(int i = 0; i < 3; i++) {
            if(gameBoard.getListBoard().get(i).isFilled) {
                if(gameBoard.getListBoard().get(i).isPlayerTile() &&
                        gameBoard.getListBoard().get(i+3).isPlayerTile() &&
                        gameBoard.getListBoard().get(i+6).isPlayerTile()) {
                    flag = 1;
                    break;
                }
                else if(gameBoard.getListBoard().get(i).isBotTile() &&
                        gameBoard.getListBoard().get(i+3).isBotTile() &&
                        gameBoard.getListBoard().get(i+6).isBotTile()) {
                    flag = 2;
                    break;
                }
            }
        }

        if(flag == 1 || flag == 2) {
            return flag;
        }

        flag = 0;

        //check diagonal
        for(int i = 0; i < 2; i++) {
            if(i == 0) {
                if(gameBoard.getListBoard().get(i).isFilled) {
                    if(gameBoard.getListBoard().get(i).isPlayerTile() &&
                            gameBoard.getListBoard().get(i+4).isPlayerTile() &&
                            gameBoard.getListBoard().get(i+8).isPlayerTile()) {
                        flag = 1;
                        break;
                    } else if(gameBoard.getListBoard().get(i).isBotTile() &&
                            gameBoard.getListBoard().get(i+4).isBotTile() &&
                            gameBoard.getListBoard().get(i+8).isBotTile()) {
                        flag = 2;
                        break;
                    }
                }
            } else if(i == 1) {
                if(gameBoard.getListBoard().get(i+1).isFilled) {
                    if(gameBoard.getListBoard().get(i+1).isPlayerTile() &&
                            gameBoard.getListBoard().get(i+3).isPlayerTile() &&
                            gameBoard.getListBoard().get(i+5).isPlayerTile()) {
                        flag = 1;
                        break;
                    } else if(gameBoard.getListBoard().get(i).isBotTile() &&
                            gameBoard.getListBoard().get(i+3).isBotTile() &&
                            gameBoard.getListBoard().get(i+5).isBotTile()) {
                        flag = 2;
                        break;
                    }
                }
            }
        }
        if(flag == 1 || flag == 2) {
            return flag;
        }

        return -1;
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
        int id = checkPlayerBoard();
        if(id != -1) {
            if(id == 1) {
                gameBoard.setEventId(GameBoard.WIN);
            } else if(id == 2) {
                gameBoard.setEventId(GameBoard.LOSE);
            }
        }
        if(id == -1 && isFieldFull()) {
            gameBoard.setEventId(GameBoard.DRAW);
        }
        return gameBoard.getEventId();
    }

    public int getBotChoice() {
        if(!isPlayerTurn() && !isFieldFull()) {
            int index = new Random().nextInt(9);
            while(gameBoard.getListBoard().get(index).isFilled) {
                index = new Random().nextInt(9) ;
            }
            return index;
        }
        return -1;
    }

    public void updateStatus(int status) {
        User user = databaseOpenHelper.getHomeData();
        if(status == 1) {
            databaseOpenHelper.insertStatus(user.getTotalMatch()+1, user.getWin()+1, user.getLose());
        } else if(status == 2) {
            databaseOpenHelper.insertStatus(user.getTotalMatch()+1, user.getWin(), user.getLose()+1);
        } else if (status == 3) {
            databaseOpenHelper.insertStatus(user.getTotalMatch()+1, user.getWin(), user.getLose());
        }
    }
}
