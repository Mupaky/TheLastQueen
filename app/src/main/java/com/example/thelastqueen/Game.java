package com.example.thelastqueen;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<List<Boolean>> playerOne;
    private List<List<Boolean>> playerTwo;
    private int playerTurn = 0;

    public Game(int maxWidth, int maxHeight){
        playerOne = new ArrayList<>(maxWidth);
        playerTwo = new ArrayList<>(maxHeight);
        Log.i("evala", "Width: " + maxWidth + ", Height: " + maxHeight);
    }

    public void startGame(){
        this.playerTurn = 1;
    }
    public boolean placeQueen(int x, int y){
        if(validateCoordinates(x,y)){
            blockPlaces(x,y);
            changeTurn();
            return  true;
        }
        return false;
    }
    private void blockPlaces(int x, int y){
        if(playerTurn == 1){
            //blocking player 2 table and the place for the queen
            playerOne.get(x).set(y, false);
            blockUpToDown(x,y,playerTwo);
            blockLeftToRight(x,y,playerTwo);
            blockDiagonals(x,y,playerTwo);
        }else{
            //blocking player 1 table and the place for the queen
            playerTwo.get(x).set(y, false);
            blockUpToDown(x,y,playerOne);
            blockLeftToRight(x,y,playerOne);
            blockDiagonals(x,y,playerOne);
        }


    }

    private void blockUpToDown(int x, int y, List<List<Boolean>> player){
        for(int i = 0; i <= player.size(); i++){
            player.get(i).set(y, false);
        }
    }

    private void blockLeftToRight(int x, int y, List<List<Boolean>> player){
        for(int i = 0; i <= player.get(x).size() - 1; i++){
            player.get(x).set(i, false);
        }
    }

    private void blockDiagonals(int x, int y, List<List<Boolean>> player){
        int count = 1;
        if(x + 1 >= player.size() || x - 1 >= player.size()){
            return;
        }
        for(int i = (x + 1); i <= player.size(); i++){
            if(y + count <= player.get(x).size()){
                player.get(i).set(y + count, false);
            }
            if(y - count >= 0){
                player.get(i).set(y - count, false);
            }
            count++;
        }
        for(int i = (x - 1); i >= 0; i--){
            if(y + count <= player.get(x).size()){
                player.get(i).set(y + count, false);
            }
            if(y - count >= 0){
                player.get(i).set(y - count, false);
            }
        }
    }


    public int checkForWinner(){
        if(!playerOne.contains(true)){
            return 2;
        }else if(!playerTwo.contains(true)){
            return 1;
        }
        return 0;

    }

    private boolean validateCoordinates(int x, int y){
        if(x >= 0 && x <= playerOne.size() - 1
                && y >= 0 && y <= playerOne.get(0).size() - 1){
            if(playerTurn == 1 && playerOne.get(x).get(y)){
                return true;
            }else if(playerTurn == 2 && playerTwo.get(x).get(y)){
                return true;
            }
        }
        return false;
    }



    private void changeTurn(){
        if(playerTurn == 1){
            this.playerTurn = 2;
        }else if(playerTurn == 2){
            this.playerTurn = 1;
        }
    }

    public int getPlayerTurn() {
        return playerTurn;
    }
}
