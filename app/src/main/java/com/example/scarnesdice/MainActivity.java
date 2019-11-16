package com.example.scarnesdice;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rand = new Random();
    String APP_TAG = "ScarnesDice";

    int UserOverall;
    int UserTurn;
    int CompOverall;
    int CompTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button roll = findViewById(R.id.roll_btn);
        roll.setOnClickListener(new RollListener());
    }

    private class RollListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            int roll = rand.nextInt(6) + 1;
            ImageView dice = findViewById(R.id.dice_view);
            Log.w(APP_TAG, String.format("Rolled a %d", roll));

            switch (roll) {
                case 1:
                    dice.setImageResource(R.drawable.dice1);
                    break;
                case 2:
                    dice.setImageResource(R.drawable.dice2);
                    break;
                case 3:
                    dice.setImageResource(R.drawable.dice3);
                    break;
                case 4:
                    dice.setImageResource(R.drawable.dice4);
                    break;
                case 5:
                    dice.setImageResource(R.drawable.dice5);
                    break;
                case 6:
                    dice.setImageResource(R.drawable.dice6);
                    break;
            }

        }
    }
}
