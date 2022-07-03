package com.example.thelastqueen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thelastqueen.game.Game;
import com.example.thelastqueen.templateHelper.ViewHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Game game;
    private List<LinearLayout> layoutsX;
    private List<List<ImageButton>> table;
    private int x = 5;
    private int y = 5;
    private boolean isNextColor = true;
    private TextView playerOne, playerTwo;
    private float dpHeight;
    private float dpWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOne = findViewById(R.id.txt_player_one);
        playerTwo = findViewById(R.id.txt_player_two);

        layoutsX = new ArrayList<>();

        Intent intent = getIntent();
            x = intent.getIntExtra("x", 4);
            y = intent.getIntExtra("y", 4);


        game = new Game(x,y);
        game.startGame();

        //Getting display metric for specific dp size on the imageButtons
        DisplayMetrics displayMetrics = getApplicationContext().getResources().getDisplayMetrics();
        this.dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        this.dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        createTable();

        LinearLayout tableView = findViewById(R.id.layout_table);

        for(int i = 0; i <= layoutsX.size() - 1; i++){
            tableView.addView(layoutsX.get(i));
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

        colorTurnChanger();



    }

    private View.OnClickListener onClickListener(){
        return view -> {
            if(game.isStopped()){
                Toast.makeText(getApplicationContext(),
                                "Player " + game.getWinner() + " win.",
                                Toast.LENGTH_SHORT)
                        .show();
                return;
            }

            if(game.placeFigure(view.getId())){
                Toast.makeText(getApplicationContext(),
                                "Player " + game.getPlayerTurn() + " turn. Id = " +view.getId(),
                                Toast.LENGTH_SHORT)
                        .show();


                placeQueen(view);


                if(game.isStopped()){
                    Toast.makeText(getApplicationContext(),
                                    "Player " + game.getWinner() + " win.",
                                    Toast.LENGTH_SHORT)
                            .show();
                }else{
                    game.changeTurn();
                    colorTurnChanger();
                }
            }
        };
    }

    private void createTable(){
        table = new ArrayList<>();
        int id = 0;
        for(int i = 0; i <= x - 1; i++){
            layoutsX.add(ViewHelper.makeLinearLayout(getApplicationContext(), (int)dpHeight, x));
            table.add(new ArrayList<>());


            for(int j = 0; j <= y - 1; j++){

                ImageButton imageButton = ViewHelper.makeImageButton(getApplicationContext(), (int)dpWidth, y);
                imageButton.setOnClickListener(onClickListener());
                imageButton.setId(id);
                table.get(i).add(imageButton);
                if(i % 2 == 0 && j == 0){
                    isNextColor = true;
                }else if(i % 2 != 0 && j == 0){
                    isNextColor = false;
                }
                    if(isNextColor){
                        isNextColor = false;
                        imageButton.setBackgroundColor(getResources().getColor(R.color.brown));
                    }
                    else{
                        isNextColor = true;
                        imageButton.setBackgroundColor(getResources().getColor(R.color.crema));
                    }
                layoutsX.get(i).addView(imageButton);
                id++;
            }

        }
    }

    private void placeQueen(View view){
        if(game.getPlayerTurn() == 2){
            ((ImageButton)view).setImageResource(R.drawable.blackqueen);
        }else if(game.getPlayerTurn() == 1){
            ((ImageButton)view).setImageResource(R.drawable.whitequeen);
        }
    }

    private void colorTurnChanger(){
        if(game.getPlayerTurn() == 1){
            playerOne.setBackgroundColor(getResources().getColor(R.color.green));
            playerTwo.setBackgroundColor(getResources().getColor(R.color.white));
        }else if(game.getPlayerTurn() == 2){
            playerOne.setBackgroundColor(getResources().getColor(R.color.white));
            playerTwo.setBackgroundColor(getResources().getColor(R.color.green));
        }
    }


}