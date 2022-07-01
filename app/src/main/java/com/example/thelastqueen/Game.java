package com.example.thelastqueen;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<List<Boolean>> playerOne;
    private List<List<Boolean>> playerTwo;
    private int playerTurn;
    private int winner;
    private int maxWidth;
    private int maxHeight;
    private boolean isStopped;

    public Game(int maxHeight, int maxWidth){
        playerOne = new ArrayList<>();
        playerTwo = new ArrayList<>();
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
        Log.i("proverkaWH", "Width: " + maxWidth + ", Height: " + maxHeight);
    }

    public void startGame(){
        fillPlayerTables();
        this.playerTurn = 1;
        isStopped = false;
    }

    public void stopGame(int winner){
        this.winner = winner;
        isStopped = true;
    }

    private void fillPlayerTables(){
        for (int i = 0; i <= maxHeight - 1; i++){
            playerOne.add(new ArrayList<>());
            playerTwo.add(new ArrayList<>());
            for (int j = 0; j <= maxWidth - 1; j++){
                playerOne.get(i).add(Boolean.TRUE);
                playerTwo.get(i).add(Boolean.TRUE);
            }
        }
    }

    public boolean placeQueen(int id){
        int x = makeIdToX(id);
        int y = makeIdToY(id);
        if(validateCoordinates(x,y)){
            blockFields(x,y);
            return  true;
        }
        return false;
    }
    private void blockFields(int x, int y){
        if(playerTurn == 1){
            //blocking player 2 table and the place for the queen
            playerOne.get(x).set(y, false);
            blockUpToDown(y, playerTwo);
            blockLeftToRight(x, playerTwo);
            blockDiagonals(x, y, playerTwo);
            checkForWinner(playerTwo);
        }else if(playerTurn == 2){
            //blocking player 1 table and the place for the queen
            playerTwo.get(x).set(y, false);
            blockUpToDown(y, playerOne);
            blockLeftToRight(x, playerOne);
            blockDiagonals(x, y, playerOne);
            checkForWinner(playerOne);
        }
    }

    private void blockUpToDown(int y, List<List<Boolean>> player){
        for(int i = 0; i <= player.size() - 1; i++){
            player.get(i).set(y, false);
        }
    }

    private void blockLeftToRight(int x, List<List<Boolean>> player){
        for(int i = 0; i <= player.get(x).size() - 1; i++){
            player.get(x).set(i, false);
        }
    }

    private void blockDiagonals(int x, int y, List<List<Boolean>> player){
        blockDiagonalBotLeft(x, y, player);
        blockDiagonalBotRight(x, y, player);
        blockDiagonalTopLeft(x, y, player);
        blockDiagonalTopRight(x, y, player);
    }

    private void blockDiagonalTopLeft(int x, int y, List<List<Boolean>> player){
        int count = 1;
        if(x - 1 < 0){
            return;
        }
        for(int i = (x - 1); i >= 0; i--){
            if(y - count >= 0){
                player.get(i).set(y - count, false);
            }
            else{
                break;
            }
            count++;
        }

    }
    private void blockDiagonalBotLeft(int x, int y, List<List<Boolean>> player){
        int count = 1;
        if(x + 1 >= player.size()){
            return;
        }
        for(int i = (x + 1); i <= player.size() - 1; i++){
            if(y - count >= 0){
                player.get(i).set(y - count, false);
            }
            else{
                break;
            }
            count++;
        }
    }
    private void blockDiagonalTopRight(int x, int y, List<List<Boolean>> player){
        int count = 1;
        if(x - 1 < 0){
            return;
        }
        for(int i = (x - 1); i >= 0; i--){
            if(y + count <= player.get(0).size() - 1){
                player.get(i).set(y + count, false);
            }
            else{
                break;
            }
            count++;
        }
    }
    private void blockDiagonalBotRight(int x, int y, List<List<Boolean>> player){
        int count = 1;
        if(x + 1 >= player.size()){
            return;
        }
        for(int i = (x + 1); i <= player.size() - 1; i++){
            if(y + count <= player.get(0).size() - 1){
                player.get(i).set(y + count, false);
            }
            else{
                break;
            }
            count++;
        }
    }

    private boolean isBlocked(List<List<Boolean>> player){
        for(int i = 0; i <= player.size() - 1; i++){
            if(player.get(i).contains(true)){
                return false;
            }
        }
        return true;
    }

    public void checkForWinner(List<List<Boolean>> player){
        if(isBlocked(player)){
            stopGame(getPlayerTurn());
        }
    }

    private boolean validateCoordinates(int x, int y){
        if(x >= 0 && x < playerOne.size()
                && y >= 0 && y < playerOne.get(0).size()){
            if(playerTurn == 1){

                return playerOne.get(x).get(y);
            }else if(playerTurn == 2){
                return playerTwo.get(x).get(y);
            }
        }
        return false;
    }



    public void changeTurn(){
        if(playerTurn == 1){
            this.playerTurn = 2;
        }else if(playerTurn == 2){
            this.playerTurn = 1;
        }
    }

    public int makeIdToX(int id){
        int var = (int)Math.ceil(id/maxWidth);
        if(id == 0 || var == 0){
            return 0;
        }
        return var;
    }

    public int makeIdToY(int id){
        if(id == 0){
            return 0;
        }
        return id % maxWidth;
    }

    public int getPlayerTurn() {
        int turn = playerTurn;
        return turn;
    }

    public int getWinner() {
        int win = winner;
        return win;
    }

    public boolean isStopped() {
        boolean isGameStopped = isStopped;
        return isGameStopped;
    }
}
