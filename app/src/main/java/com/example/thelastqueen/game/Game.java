package com.example.thelastqueen.game;


import com.example.thelastqueen.game.Figure;
import com.example.thelastqueen.game.Player;
import com.example.thelastqueen.game.Queen;

public class Game {

    private final Player playerOne;
    private final Player playerTwo;
    private final Figure gameFigure;
    private int playerTurn = 1;
    private int winner;
    private final int maxWidth;
    private final int maxHeight;
    private boolean isStopped;
    private boolean firstGame = true;

    public Game(int maxHeight, int maxWidth){

        playerOne = new Player();
        playerTwo = new Player();
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;

        //With what figure you will play the game
        gameFigure = new Queen();
    }

    public void startGame(){
        playerOne.fillPlayerTables(maxHeight, maxWidth);
        playerTwo.fillPlayerTables(maxHeight, maxWidth);
        if(!firstGame){
            if(playerTurn == 1){
                this.playerTurn = 2;
            }else{
                this.playerTurn = 1;
            }
        }
        isStopped = false;
    }

    public void stopGame(int winner){
        this.winner = winner;
        isStopped = true;
        firstGame = false;
        playerOne.getPlayerTable().cleanTable();
        playerTwo.getPlayerTable().cleanTable();
    }


    public boolean placeFigure(int id){
        int x = makeIdToX(id);
        int y = makeIdToY(id);
        if(validateCoordinates(x,y)){
            gameFigure.blockFields(x,y, playerOne, playerTwo, playerTurn);
            checkForWinner();
            return  true;
        }
        return false;
    }

    public void checkForWinner(){
        if(playerOne.isBlocked() || playerTwo.isBlocked()){
            stopGame(getPlayerTurn());
        }
    }

    private boolean validateCoordinates(int x, int y){
        if(x >= 0 && x < playerOne.getPlayerTable().getTable().size()
                && y >= 0 && y < playerOne.getPlayerTable().getTable().get(0).size()){
            if(playerTurn == 1){
                return playerOne.getPlayerTable().getTable().get(x).get(y);
            }else if(playerTurn == 2){
                return playerTwo.getPlayerTable().getTable().get(x).get(y);
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
        boolean isStopped = this.isStopped;
        return isStopped;
    }
}
