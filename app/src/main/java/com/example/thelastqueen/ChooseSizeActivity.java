package com.example.thelastqueen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChooseSizeActivity extends AppCompatActivity {

    private int x;
    private int y;
    private Button btnStartGame;
    private EditText editX;
    private EditText editY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_size);

        editX = findViewById(R.id.edit_x);
        editY = findViewById(R.id.edit_y);

        btnStartGame = findViewById(R.id.btn_start_game);
        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x = Integer.parseInt(editX.getText().toString());
                y = Integer.parseInt(editY.getText().toString());
                if(!editX.getText().equals(null) && x > 3 && x < 15
                    && !editY.getText().equals(null) && y > 3 && y < 15){
                    startGameActivity();
                }else{
                    Toast.makeText(getApplicationContext(),
                                    "Table size should be in range",
                                    Toast.LENGTH_SHORT)
                            .show();
                }

            }
        });


    }

    private void startGameActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("x", x);
        intent.putExtra("y", y);
        startActivity(intent);
    }
}