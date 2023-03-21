package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Begin extends AppCompatActivity implements View.OnClickListener{

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);

        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(btn == view){
            Intent i = new Intent(this, Quiz.class);
            startActivity(i);
        }
    }
}