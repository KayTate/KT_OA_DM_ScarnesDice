package com.example.scarnesdice;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.temporal.TemporalQueries;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rand = new Random();
    String APP_TAG = "ScarnesDice";

    int UserOverall;
    int UserTurn;
    int CompOverall;
    int CompTurn;
    int roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button roll = findViewById(R.id.roll_btn);
        roll.setOnClickListener(new RollListener());

//        Button reset = findViewById(R.id.reset_btn);
//        reset.setOnClickListener(new ResetListener());
//
//        Button hold = findViewById(R.id.hold_btn);
//        hold.setOnClickListener(new HoldListener());
    }

    private int gameTurn(int roll){
        Log.w(APP_TAG, String.format("Top of gameturn method"));
        int turn_score;

        if(roll != 1){
            turn_score = roll;
        }
        else{
            turn_score = 0;
        }
        return turn_score;
    }


    private class RollListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            roll = rand.nextInt(6) + 1;
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

            UserTurn = gameTurn(roll);
            TextView turn_score_text = findViewById(R.id.turn_score_textView);
            turn_score_text.setText(String.format("Turn Score: %d", UserTurn));

        }
    }

    private class HoldListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            UserTurn = gameTurn(roll);
            UserOverall += UserTurn;

            TextView user_score = findViewById(R.id.user_score_textview);
            user_score.setText(String.format("Your Score: %d", UserOverall));

        }
    }

    private class ResetListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            UserOverall = 0;
            UserTurn = 0;
            CompOverall = 0;
            CompTurn = 0;

        }
    }
}
