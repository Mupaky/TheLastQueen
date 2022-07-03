package com.example.thelastqueen.game;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private List<List<Boolean>> playerTable;

    public Table(){
        playerTable = new ArrayList<>();
    }

    public void cleanTable(){
        playerTable = null;
        playerTable = new ArrayList<>();
    }





    public List<List<Boolean>> getTable() {
        return playerTable;
    }
}
