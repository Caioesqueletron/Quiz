package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Begin extends AppCompatActivity implements View.OnClickListener{

    private Button btn;
    private SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);

        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(this);
        shared = getSharedPreferences("acesso1", 0);
        SharedPreferences.Editor edit = shared.edit();
        edit.putString("Valor", "Quiz Elaborado na Aula");
        edit.putString("valor1", "Qualquer outro dado para salvar");
        edit.commit();

    }

    @Override
    public void onClick(View view) {
        if(btn == view){
            Intent i = new Intent(this, Quiz.class);
            startActivity(i);
        }
    }
}
