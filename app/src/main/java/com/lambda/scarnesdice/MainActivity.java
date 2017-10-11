package com.lambda.scarnesdice;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private int userOverallScore = 0 , computerOverallScore = 0 , userCurrentScore = 0 , computerCurrentScore = 0;
    private Random random = new Random(System.nanoTime());;
    private TextView userScore, computerScore, turnScore;
    private Button rollButton , holdButton , resetButton;
    private ImageView diceFace;
    private int num;
    private Handler timerHandler= new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            if (num != 1 && computerCurrentScore <= 20) {
                computerTurn();
                timerHandler.postDelayed(this, 1000);
            }
            if (computerCurrentScore > 20) {
                setComputerOverallScore();
                Toast.makeText(MainActivity.this , "Computer Holds!" , Toast.LENGTH_SHORT).show();
                timerHandler.removeCallbacks(this);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceFace = (ImageView) findViewById(R.id.image_dice);
        rollButton  = (Button) findViewById(R.id.bt_roll);
        holdButton = (Button) findViewById(R.id.bt_hold);
        resetButton = (Button) findViewById(R.id.bt_reset);
        turnScore = (TextView) findViewById(R.id.tv_turn_score);
        userScore = (TextView) findViewById(R.id.tv_user);
        computerScore = (TextView) findViewById(R.id.tv_computer);


    }







    public void roll( View view ) {
        num = random.nextInt(6) + 1;
        setScore(num , 'u');
    }

    public void hold( View view ) {
        userOverallScore += userCurrentScore;
        userCurrentScore = 0;
        userScore.setText("Your Score:" + " " + userOverallScore);
        turnScore.setText("Computer Turn Score: 0");
        declareWinner();
        if (userOverallScore < 100) {
            computerTurn();
            declareWinner();
        }
    }

    public void reset( View view ) {
        userCurrentScore = 0;
        userOverallScore = 0;
        computerCurrentScore = 0;
        computerOverallScore = 0;
        turnScore.setText("Turn Score: 0");
        userScore.setText("Your Score: 0");
        computerScore.setText("Computer Score: 0");
        rollButton.setEnabled(true);
        holdButton.setEnabled(true);
    }

    public void computerTurn() {
       // Log.e(TAG , "computerTurnCalled!!");
        holdButton.setEnabled(false);
        resetButton.setEnabled(false);
        rollButton.setEnabled(false);
        num = random.nextInt(6) + 1;
        Log.e(TAG , "num: " + num);
        if (num != 1 && computerCurrentScore <= 20) {
            setScore(num , 'c');
            timerHandler.postDelayed(timerRunnable , 1000);
        } else {
            timerHandler.removeCallbacks(timerRunnable);
            if(num == 1) {
                diceFace.setImageResource(R.drawable.dice1);
                Toast.makeText(this , "Computer Rolled a 1!" , Toast.LENGTH_SHORT).show();
                computerCurrentScore = 0;
            }
            setComputerOverallScore();
        }

    }

    public void setComputerOverallScore() {
        computerOverallScore += computerCurrentScore;
        computerCurrentScore = 0;
        computerScore.setText("Computer Score:" + " " + computerOverallScore);
        turnScore.setText("Your Turn Score: 0");
        holdButton.setEnabled(true);
        resetButton.setEnabled(true);
        rollButton.setEnabled(true);
        declareWinner();
    }

    public void setScore(int num , char ch) {
        switch (num) {
            case 1: diceFace.setImageResource(R.drawable.dice1);
                break;
            case 2: diceFace.setImageResource(R.drawable.dice2);
                break;
            case 3: diceFace.setImageResource(R.drawable.dice3);
                break;
            case 4: diceFace.setImageResource(R.drawable.dice4);
                break;
            case 5: diceFace.setImageResource(R.drawable.dice5);
                break;
            case 6: diceFace.setImageResource(R.drawable.dice6);
                break;
        }
        if (num == 1 ) {
            if (ch == 'u') {
                userCurrentScore = 0;
                turnScore.setText("User Turn Score: 0");
                computerTurn();
            }else {
                computerCurrentScore = 0;
                turnScore.setText("Computer Turn Score: 0");
            }
        } else {
            if (ch == 'u') {
                userCurrentScore += num;
                turnScore.setText("User Turn Score: " + userCurrentScore);
            } else {
                computerCurrentScore += num;
                turnScore.setText("Computer Turn Score: " + computerCurrentScore);
            }
        }
    }

    public void declareWinner() {
        if (computerOverallScore > 100 || userOverallScore > 100) {
            if (computerOverallScore > 100) {
                Toast.makeText(this, "Computer Wins!!!", Toast.LENGTH_SHORT).show();
            } else if (userOverallScore > 100) {
                Toast.makeText(this, "User Wins!!!", Toast.LENGTH_SHORT).show();
            }
            rollButton.setEnabled(false);
            holdButton.setEnabled(false);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);

    }
}