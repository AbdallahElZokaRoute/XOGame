package com.route.xogame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView player1Score, player2Score;
    int player1ScoreValue = 0, player2ScoreValue = 0;
    ConstraintLayout constraintLayout;
    /*
        onCreate -> When Creating Layout but not visible to user

        onStart -> When UI is Visible but can't interact with

        onResume-> When UI is Visible and Can interact with

        onPause -> Activity Partially Visible

        onStop -> Activity Completely Not Visible

        onDestroy -> Activity is Destroyed From Memory


     */
    final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1Score = findViewById(R.id.player_1_score);
        player2Score = findViewById(R.id.player_2_score);
        constraintLayout = findViewById(R.id.root_view_constrain);
        initializeBoard();
        Log.e(TAG, "onCreate: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart: ");
    }

    int counter = 0;
    ArrayList<String> board;

    public void initializeBoard() {
        board = new ArrayList<>(9);
        counter = 0;
        for (int i = 0; i < 9; i++) {
            board.add("");
        }
        for (int i = 0; i < constraintLayout.getChildCount(); i++) {
            View view = constraintLayout.getChildAt(i);
            if (view instanceof Button) {
                ((Button) view).setText("");
            }

        }


    }

    public void onPlayerClick(View view) {
        Button btn;
        if (view instanceof Button) {
            btn = ((Button) view);
            if (!btn.getText().toString().isEmpty()) {
                return;
            }
            String playerSymbol;
            if (counter % 2 == 0) {
                playerSymbol = "X";
                btn.setText("X");

            } else {
                playerSymbol = "O";
                btn.setText("O");
            }
            placePlayerSympol(btn, board, playerSymbol);
            ++counter;
            if (checkIfPlayerWin("X", board)) {
                player1ScoreValue += 1;
                player1Score.setText("" + player1ScoreValue);
                Toast.makeText(this, getString(R.string.player_1_x) + " Wins !", Toast.LENGTH_SHORT).show();
                initializeBoard();
            } else if (checkIfPlayerWin("O", board)) {
                player2ScoreValue += 1;
                player2Score.setText("" + player2ScoreValue);
                Toast.makeText(this, getString(R.string.player_2_o) + " Wins !", Toast.LENGTH_SHORT).show();
                initializeBoard();
            } else if (counter == 9) {
                Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
                initializeBoard();
            }
            btn.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.red, null));

        }
    }

    private void placePlayerSympol(Button btn, ArrayList<String> boardState, String playerSymbol) {
        if (btn.getId() == R.id.btn1) {
            boardState.set(0, playerSymbol);
        } else if (btn.getId() == R.id.btn2) {
            boardState.set(1, playerSymbol);
        } else if (btn.getId() == R.id.btn3) {
            boardState.set(2, playerSymbol);
        } else if (btn.getId() == R.id.btn4) {
            boardState.set(3, playerSymbol);
        } else if (btn.getId() == R.id.btn5) {
            boardState.set(4, playerSymbol);
        } else if (btn.getId() == R.id.btn6) {
            boardState.set(5, playerSymbol);
        } else if (btn.getId() == R.id.btn7) {
            boardState.set(6, playerSymbol);
        } else if (btn.getId() == R.id.btn8) {
            boardState.set(7, playerSymbol);
        } else if (btn.getId() == R.id.btn9) {
            boardState.set(8, playerSymbol);
        }
    }

    public boolean checkIfPlayerWin(String playerSympol, ArrayList<String> boardState) {
        //Check Rows
        for (int i = 0; i < 9; i += 3) {
            if (boardState.get(i).equals(playerSympol) && boardState.get(i + 1).equals(playerSympol) && boardState.get(i + 2).equals(playerSympol))
                return true;
        }
        // Check Columns
        for (int i = 0; i < 3; i++) {
            if (boardState.get(i).equals(playerSympol) && boardState.get(i + 3).equals(playerSympol) && boardState.get(i + 6).equals(playerSympol))
                return true;
        }
        //Check Diagonals
        if (boardState.get(0).equals(playerSympol) && boardState.get(4).equals(playerSympol) && boardState.get(8).equals(playerSympol))
            return true;
        if (boardState.get(2).equals(playerSympol) && boardState.get(4).equals(playerSympol) && boardState.get(6).equals(playerSympol))
            return true;
        return false;
    }

    /*
    ["","",""
     "","",""
     "","",""
    ]

     */

}