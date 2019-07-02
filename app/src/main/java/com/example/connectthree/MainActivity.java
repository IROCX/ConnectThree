package com.example.connectthree;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

public class MainActivity extends AppCompatActivity {


    public void resetButton(View view) {

        active = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        GridLayout board = (GridLayout) findViewById(R.id.grid);
        for (int i = 0; i < board.getChildCount(); i++) {
            ((ImageView) board.getChildAt(i)).setImageResource(0);
        }
    }

    public void reset() {
        GridLayout board = (GridLayout) findViewById(R.id.grid);
        for (int i = 0; i < board.getChildCount(); i++) {
            ((ImageView) board.getChildAt(i)).setImageResource(0);
        }
    }


    //    0 for black
//    1 for red
    int active = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {1, 4, 7}, {2, 5, 8}, {3, 6, 9}, {1, 5, 9}, {3, 5, 7}};

    public void start(View view) {
        GridLayout board = findViewById(R.id.grid);
        Button starter = findViewById(R.id.button);
        Button resetter = findViewById(R.id.button2);

        board.setVisibility(View.VISIBLE);
        board.animate().translationX(8f).alpha(1f).setDuration(200);
        starter.animate().alpha(0f).setDuration(500);
        starter.setVisibility(View.GONE);
        resetter.setVisibility(View.VISIBLE);
        resetter.animate().alpha(1f).setDuration(500);


    }

    public void winner(int x) {
        String winnerName;
        if (x == 1)
            winnerName = "Red";
        else if (x == 0)
            winnerName = "Black";
        else
            winnerName = "Match resulted in a draw.";

        GridLayout board = (GridLayout) findViewById(R.id.grid);
        board.setVisibility(View.INVISIBLE);

        reset();

        TextView textView = (TextView) findViewById(R.id.winningMessage);
        if (x != 1 && x != 0) {
            textView.setText(winnerName+"\n");
        } else
            textView.setText("Congratulations!!!\n" + winnerName + " has won!\n");

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.WinnerL);
        relativeLayout.setVisibility(View.VISIBLE);

        Button button = (Button) findViewById(R.id.button2);
        button.setVisibility(View.GONE);

    }

    public void playAgain(View view) {

        active = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        Button button = (Button) findViewById(R.id.button);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.WinnerL);
        GridLayout board = (GridLayout) findViewById(R.id.grid);

        board.animate().alpha(0f).setDuration(1);
        relativeLayout.setVisibility(View.GONE);
        button.setVisibility(View.VISIBLE);
        button.animate().alpha(1f).setDuration(500);
    }

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());
        int x = Integer.parseInt(counter.getTag().toString());

        if (gameState[x - 1] == 2) {
            if (active == 0) {
                counter.setTranslationY(-10000f);
                counter.setImageResource(R.drawable.blackbutton);
                gameState[x - 1] = active;
                active = 1;
                counter.animate().translationYBy(10000f).setDuration(100);
            } else {
                counter.setTranslationY(-10000f);
                counter.setImageResource(R.drawable.redb);
                gameState[x - 1] = active;
                active = 0;
                counter.animate().translationYBy(10000f).setDuration(100);
            }
        }
        int flag = 0;
        for (int i = 0; i < 8; i++) {
            if (gameState[winningPositions[i][0] - 1] != 2 && gameState[winningPositions[i][0] - 1] == gameState[winningPositions[i][1] - 1]
                    && gameState[winningPositions[i][2] - 1] == gameState[winningPositions[i][1] - 1]) {
                winner(gameState[winningPositions[i][0] - 1]);
                flag = 1;
            }
        }
        int count = 0;
        for (int i = 0; i < 9; i++) {

            if (gameState[i] != 2)
                count += 1;
        }
        if (count == 9 && flag == 0) {
            winner(9);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridLayout board = (GridLayout) findViewById(R.id.grid);
        board.setTranslationX(-2000);

    }
}
