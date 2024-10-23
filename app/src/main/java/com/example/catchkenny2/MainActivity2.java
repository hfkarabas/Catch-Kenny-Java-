package com.example.catchkenny2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        Button buttonEasy = findViewById(R.id.easy);
        Button buttonNormal = findViewById(R.id.normal);
        Button buttonHard = findViewById(R.id.hard);

        // Set OnClickListener for Easy button
        buttonEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start GameActivity with "easy" difficulty
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                intent.putExtra("difficulty", "easy");
                startActivity(intent);
            }
        });

        // Set OnClickListener for Normal button
        buttonNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start GameActivity with "normal" difficulty
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                intent.putExtra("difficulty", "normal");
                startActivity(intent);
            }
        });

        // Set OnClickListener for Hard button
        buttonHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start GameActivity with "hard" difficulty
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                intent.putExtra("difficulty", "hard");
                startActivity(intent);
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}