package com.example.catchkenny2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText;
    TextView timeText;
    int score;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    int imageVisibilityDuration = 500; // Default visibility duration for Normal

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText = findViewById(R.id.timeText);
        scoreText = findViewById(R.id.scoreText);

        imageArray = new ImageView[]{
                findViewById(R.id.imageView),
                findViewById(R.id.imageView2),
                findViewById(R.id.imageView3),
                findViewById(R.id.imageView4),
                findViewById(R.id.imageView5),
                findViewById(R.id.imageView6),
                findViewById(R.id.imageView7),
                findViewById(R.id.imageView8),
                findViewById(R.id.imageView9)
        };

        // Get difficulty from Intent
        String difficulty = getIntent().getStringExtra("difficulty");
        setGameDifficulty(difficulty); // Set the game speed based on difficulty

        hideImages(); // Start the game by hiding images

        score = 0;

        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                timeText.setText("Time Off");
                handler.removeCallbacks(runnable);
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                showRestartDialog();
            }
        }.start();
    }

    private void setGameDifficulty(String difficulty) {
        if (difficulty != null) {
            switch (difficulty) {
                case "easy":
                    imageVisibilityDuration = 1000; // Slower speed for easy
                    break;
                case "normal":
                    imageVisibilityDuration = 500;  // Default speed for normal
                    break;
                case "hard":
                    imageVisibilityDuration = 300;  // Faster speed for hard
                    break;
            }
        }
    }

    public void hideImages() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random();
                int i = random.nextInt(imageArray.length);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this, imageVisibilityDuration); // Adjust based on difficulty
            }
        };
        handler.post(runnable);
    }

    private void showRestartDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setTitle("Restart ?");
        alert.setMessage("Your Score: " + score + "\nTry Again?");
        alert.setPositiveButton("Yes!", (dialog, which) -> {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        });
        alert.setNegativeButton("No", (dialog, which) ->
                Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_SHORT).show()
        );
        alert.show();
    }

    public void increaseScore(View view) {
        score++;
        scoreText.setText("Score: " + score);
    }
}
