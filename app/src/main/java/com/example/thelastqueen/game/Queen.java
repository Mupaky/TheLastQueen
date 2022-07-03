package com.example.thelastqueen.game;

import java.util.List;

public class Queen extends Figure{

    public void blockFields(int x, int y,  Player playerOne, Player playerTwo, int playerTurn){
        if(playerTurn == 1){
            //blocking player 2 table and the place for the queen
            playerOne.getPlayerTable().getTable().get(x).set(y, false);
            blockUpToDown(y, playerTwo.getPlayerTable().getTable());
            blockLeftToRight(x, playerTwo.getPlayerTable().getTable());
            blockDiagonals(x, y, playerTwo.getPlayerTable().getTable());
        }else if(playerTurn == 2){
            //blocking player 1 table and the place for the queen
            playerTwo.getPlayerTable().getTable().get(x).set(y, false);
            blockUpToDown(y, playerOne.getPlayerTable().getTable());
            blockLeftToRight(x, playerOne.getPlayerTable().getTable());
            blockDiagonals(x, y, playerOne.getPlayerTable().getTable());
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

}
