package com.example.thelastqueen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Game game;
    private List<LinearLayout> layoutsX;
    private List<List<ImageButton>> table;
    private int x = 5;
    private int y = 5;
    private boolean isNextColor = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutsX = new ArrayList<>();

        Intent intent = getIntent();
            x = intent.getIntExtra("x", 4);
            y = intent.getIntExtra("y", 4);


        Log.i("printera","x = " + x + "   y = " + y);
        game = new Game(x,y);
        createTable();
        LinearLayout tableView = findViewById(R.id.layout_table);
        for(int i = 0; i <= layoutsX.size() - 1; i++){
            tableView.addView(layoutsX.get(i));
        }


    }

    private void createTable(){
        table = new ArrayList<>();
        int var;
        for(int i = 0; i <= x - 1; i++){
            layoutsX.add(makeLinearLayout());
            table.add(new ArrayList<>());


            for(int j = 0; j <= y - 1; j++){

                ImageButton imageButton = makeImageButton();
                imageButton.setOnClickListener(onClickListener());
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
            }

        }
    }



    private ImageButton makeImageButton(){

        ImageButton imageButton = new ImageButton(getApplicationContext());
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams
                        (LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                1.0f);
        imageButton.setLayoutParams(lp);

        return imageButton;
    }

    private LinearLayout makeLinearLayout(){
        LinearLayout linearLayout = new LinearLayout(getApplicationContext());
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams
                        (LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                1.0f);
                linearLayout.setLayoutParams(lp);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        return  linearLayout;
    }

    private View.OnClickListener onClickListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(game.placeQueen(x,y)){
                    Toast.makeText(getApplicationContext(),
                                    "Player " + game.getPlayerTurn() + " turn.",
                                    Toast.LENGTH_SHORT)
                            .show();
                    if(game.checkForWinner() == 0){
                        Toast.makeText(getApplicationContext(),
                                        "Player " + game.getPlayerTurn() + " win.",
                                        Toast.LENGTH_SHORT)
                                .show();
                    }
                }

            }
        };

    }


}