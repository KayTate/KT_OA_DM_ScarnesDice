package com.example.scarnesdice;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

        Button roll_btn = findViewById(R.id.roll_btn);
        roll_btn.setOnClickListener(new RollListener());

        Button reset_btn = findViewById(R.id.reset_btn);
        reset_btn.setOnClickListener(new ResetListener());

        Button hold_btn = findViewById(R.id.hold_btn);
        hold_btn.setOnClickListener(new HoldListener());
    }


    private void userTurn(int roll) {
        if(roll != 1){
            UserTurn += roll;
        }
        else{
            UserTurn = 0;
            Hold();
        }
    }


    private class RollListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.w(APP_TAG, "User turn");

            int roll = Roll();
            userTurn(roll);

            TextView turn_score = findViewById(R.id.turn_score_textView);
            turn_score.setText(String.format("Turn Score: %d", UserTurn));

        }
    }

    private int Roll() {
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
        return roll;
    }


    private class HoldListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Hold();

        }
    }

    private void Hold() {
        UserOverall += UserTurn;
        UserTurn = 0;

        TextView user_score = findViewById(R.id.user_score_textview);
        user_score.setText(String.format("Your Score: %d", UserOverall));

        TextView turn_score = findViewById(R.id.turn_score_textView);
        turn_score.setText("Turn Score: 0");

        ComputerTurn();
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

    private void ComputerTurn() {
        Log.w(APP_TAG, "Computer Turn");
        Button roll_btn = findViewById(R.id.roll_btn);
        Button hold_btn = findViewById(R.id.hold_btn);

        roll_btn.setEnabled(false);
        hold_btn.setEnabled(false);

        while (CompTurn < 20) {
            int roll = Roll();
            if (roll != 1) {
                CompTurn += roll;
            } else {
                CompTurn = 0;
                break;
            }
            TextView turn_score = findViewById(R.id.turn_score_textView);
            turn_score.setText(String.format("Turn Score: %d", CompTurn));
        }
        CompOverall += CompTurn;
        CompTurn = 0;
        Log.w(APP_TAG, "Comp overall " + CompOverall);

        TextView computer_score = findViewById(R.id.comp_score_textView);
        computer_score.setText(String.format("Computer Score: %d", CompOverall));

        TextView turn_score = findViewById(R.id.turn_score_textView);
        turn_score.setText("Turn Score: 0");

        roll_btn.setEnabled(true);
        hold_btn.setEnabled(true);

    }
}
