package com.example.scarnesdice;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
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
        Log.w(APP_TAG, "Content view set");

        Button roll_btn = findViewById(R.id.roll_btn);
        roll_btn.setOnClickListener(new RollListener());
        Button hold_btn = findViewById(R.id.hold_btn);
        hold_btn.setOnClickListener(new HoldListener());
        Button reset_btn = findViewById(R.id.reset_btn);
        reset_btn.setOnClickListener(new ResetListener());

        String turn_score_string = getString(R.string.turn_score);
        String user_score_string = getString(R.string.user_score);
        String comp_score_stirng = getString(R.string.comp_score);

        TextView turn_score = findViewById(R.id.turn_score_textView);
        turn_score.setText(String.format(Locale.US, "%s %d", turn_score_string, 0));
        TextView user_score = findViewById(R.id.user_score_textview);
        user_score.setText(String.format(Locale.US, "%s %d", user_score_string, 0));
        TextView computer_score = findViewById(R.id.comp_score_textView);
        computer_score.setText(String.format(Locale.US, "%s %d", comp_score_stirng, 0));
    }


    //Turns
    private void userTurn(int roll) {
        if (roll != 1) {
            UserTurn += roll;
        } else {
            UserTurn = 0;
            Hold();
        }
    }

    private void ComputerTurn() {
        Log.w(APP_TAG, "Computer Turn");

        Button roll_btn = findViewById(R.id.roll_btn);
        roll_btn.setEnabled(false);
        Button hold_btn = findViewById(R.id.hold_btn);
        hold_btn.setEnabled(false);

        TextView turn_score = findViewById(R.id.turn_score_textView);
        TextView computer_score = findViewById(R.id.comp_score_textView);

        String turn_score_string = getString(R.string.turn_score);
        String comp_score_stirng = getString(R.string.comp_score);

        while (CompTurn < 20) {
            int roll = Roll();
            //Good place to insert wait?
            if (roll != 1) {
                CompTurn += roll;
            } else {
                CompTurn = 0;
                break;
            }
            turn_score.setText(String.format(Locale.US, "%s %d", turn_score_string, CompTurn));
        }

        CompOverall += CompTurn;
        CompTurn = 0;
        Log.w(APP_TAG, "Comp overall " + CompOverall);

        computer_score.setText(String.format(Locale.US, "%s %d", comp_score_stirng, CompOverall));
        turn_score.setText(String.format(Locale.US, "%s %d", turn_score_string, 0));

        roll_btn.setEnabled(true);
        hold_btn.setEnabled(true);

    }

    //Helpers
    private int Roll() {
        ImageView dice = findViewById(R.id.dice_view);
        int roll = rand.nextInt(6) + 1;
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


    private void Hold() {
        UserOverall += UserTurn;
        UserTurn = 0;

        String turn_score_string = getString(R.string.turn_score);
        String user_score_string = getString(R.string.user_score);

        TextView user_score = findViewById(R.id.user_score_textview);
        user_score.setText(String.format(Locale.US, "%s %d", user_score_string, UserOverall));
        TextView turn_score = findViewById(R.id.turn_score_textView);
        turn_score.setText(String.format(Locale.US, "%s %d", turn_score_string, 0));

        ComputerTurn();
    }


    //Listeners
    private class RollListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Log.w(APP_TAG, "User turn");

            int roll = Roll();
            userTurn(roll);

            String turn_score_string = getString(R.string.turn_score);

            TextView turn_score = findViewById(R.id.turn_score_textView);
            turn_score.setText(String.format(Locale.US, "%s %d", turn_score_string, UserTurn));

        }
    }

    private class HoldListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Hold();

        }
    }

    private class ResetListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            UserOverall = 0;
            UserTurn = 0;
            CompOverall = 0;
            CompTurn = 0;

        }
    }
}
