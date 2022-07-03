package com.example.thelastqueen.game;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private Table playerTable;

    public Player(){
        playerTable = new Table();
    }

    public boolean isBlocked(){
        for(int i = 0; i <= playerTable.getTable().size() - 1; i++){
            if(playerTable.getTable().get(i).contains(true)){
                return false;
            }
        }
        return true;
    }

    public void fillPlayerTables(int maxHeight, int maxWidth){
        for (int i = 0; i <= maxHeight - 1; i++){
            playerTable.getTable().add(new ArrayList<>());
            for (int j = 0; j <= maxWidth - 1; j++){
                playerTable.getTable().get(i).add(Boolean.TRUE);
            }
        }
    }



    public Table getPlayerTable() {
        return playerTable;
    }
}
