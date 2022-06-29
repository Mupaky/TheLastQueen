package com.example.thelastqueen;

import android.util.Log;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private final List<List<Boolean>> playerOne;
    private final List<List<Boolean>> playerTwo;
    private int playerTurn;
    private int winner;
    private final int maxWidth;
    private final int maxHeight;

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
    }

    public void stopGame(){
        this.winner = playerTurn;
        this.playerTurn = 0;
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
            blockPlaces(x,y);
            if(checkForWinner() != 0){
                changeTurn();
            }else{
                stopGame();
            }
            return  true;
        }
        return false;
    }
    private void blockPlaces(int x, int y){
        if(playerTurn == 1){
            //blocking player 2 table and the place for the queen
            playerOne.get(x).set(y, false);
            blockUpToDown(y, playerTwo);
            blockLeftToRight(x, playerTwo);
            blockDiagonals(x, y, playerTwo);
        }else if(playerTurn == 2){
            //blocking player 1 table and the place for the queen
            playerTwo.get(x).set(y, false);
            blockUpToDown(y, playerOne);
            blockLeftToRight(x, playerOne);
            blockDiagonals(x, y, playerOne);
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
        int count = 1;
        if(x + 1 >= player.size() || x - 1 >= 0){
            return;
        }
        for(int i = (x + 1); i <= player.size() - 1; i++){
            if(y + count <= player.get(x).size()){
                player.get(i).set(y + count, false);
            }
            if(y - count >= 0){
                player.get(i).set(y - count, false);
            }
            count++;
        }
        for(int i = (x - 1); i >= 0; i--){
            if(x - 1 <= 0){
                break;
            }
            if(y + count <= player.get(x).size()){
                player.get(i).set(y + count, false);
            }
            if(y - count >= 0){
                player.get(i).set(y - count, false);
            }
        }
    }


    public int checkForWinner(){
        if(!playerOne.contains(Boolean.TRUE)){
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
            }else return playerTurn == 2 && playerTwo.get(x).get(y);
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

    public int makeIdToX(int id){
        if(id == 0){
            return 0;
        }
        return (int)Math.floor(id/(maxHeight-1));
    }

    public int makeIdToY(int id){
        if(id == 0){
            return 0;
        }
        return id%maxWidth;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public int getWinner() {
        return winner;
    }


}
