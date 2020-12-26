package com.example.bemillionaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
        TextView startGame;
        TextView msg;
        TextView score;//final score

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startGame=findViewById(R.id.startGame);
        msg=findViewById(R.id.msg);
        score=findViewById(R.id.scr);
        //starts game
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,gameActivity.class);
                startActivity(intent);

            }
        });
        //getting result from game activity
        Intent intent=getIntent();
        msg.setText(intent.getStringExtra("msg"));
        msg.setVisibility(View.VISIBLE);
        score.setText(intent.getStringExtra("score"));

    }
}
