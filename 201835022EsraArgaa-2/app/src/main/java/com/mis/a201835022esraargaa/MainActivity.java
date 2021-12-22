package com.mis.a201835022esraargaa;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {




        Button btn1;
        Button btn2;

        @SuppressLint("CutPasteId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            btn1 = findViewById(R.id.btn1);
            btn2 = findViewById(R.id.btn2);
            final Button Artists = findViewById(R.id.btn1);
            final Button Musics = findViewById(R.id.btn2);




            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this,QuizActivity.class);
                    startActivity(intent);

                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent(MainActivity.this,QuizActivity2.class);
                    startActivity(intent2);

                }
            });

        }
}












