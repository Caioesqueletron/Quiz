package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class TelaFim extends AppCompatActivity implements Runnable {

    private TextView tx1, tx2;
    private Handler handler;
    private SharedPreferences shared;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_fim);

        shared = getSharedPreferences("acesso1",0);

        tx1 = (TextView) findViewById(R.id.textView7);
        tx2 = (TextView) findViewById(R.id.textView6);

        Intent ii = getIntent();
        if(ii != null){
            Bundle b = new Bundle();
            b = ii.getExtras();
            if(b != null){
                tx1.setText(b.getString("chave1"));
                tx2.setText(b.getString("chave2"));

            }
        }

        handler = new Handler();
        handler.postDelayed(this, 5000);

    }

    @Override
    public void run() {
        Intent i = new Intent(this, Begin.class);
        startActivity(i);
    }
}