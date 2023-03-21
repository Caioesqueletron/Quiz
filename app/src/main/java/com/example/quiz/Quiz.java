package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, Runnable {
    private TextView acertos;
    private TextView errados;
    private TextView pergunta;
    private TextView questao;
    private ProgressBar barra;
    private RadioButton radio1;
    private RadioButton radio2;
    private RadioButton radio3;
    private RadioButton radio4;
    private RadioGroup radioGroup;
    private RadioButton radioTocado;
    private Button enviar;
    private ArrayList<Questions> perguntas = new ArrayList<>();
    private int indice;
    private String mod1;
    private String mod2;
    private String textoRadioSelecionado;
    private Integer acertosCount;
    private Integer errosCount;
    private int limitePerguntas;
    private Handler handler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        acertos = (TextView) findViewById(R.id.textView2);
        errados = (TextView) findViewById(R.id.textView3);
        pergunta = (TextView) findViewById(R.id.textView4);
        questao = (TextView) findViewById(R.id.textView5);
        barra = (ProgressBar) findViewById(R.id.progressBar);
        radio1 = (RadioButton) findViewById(R.id.radioButton);
        radio2 = (RadioButton) findViewById(R.id.radioButton2);
        radio3 = (RadioButton) findViewById(R.id.radioButton3);
        radio4 = (RadioButton) findViewById(R.id.radioButton4);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);
        enviar = (Button) findViewById(R.id.button2);
        enviar.setEnabled(false);
        acertosCount = 0;
        errosCount = 0;
        limitePerguntas = 0;
        handler = new Handler();

        enviar.setOnClickListener(this);
        carregaPerguntas();
        mod1 = "Pergunta: ";
        mod2 = "/10";
        carregaPerguntaNoDesign();
    }

    public void carregaPerguntas() {
        Questions pergunta = new Questions();
        pergunta.setPergunta("Quantas vezes o Brasil foi campeão em uma Copa do Mundo ?");
        pergunta.setResp1("2");
        pergunta.setResp2("3");
        pergunta.setResp3("4");
        pergunta.setResp4("5");
        pergunta.setRespCerto("5");
        perguntas.add(pergunta);

        Questions pergunta1 = new Questions();
        pergunta1.setPergunta("Qual dessas cidades tem a maior população?");
        pergunta1.setResp1("Délhi");
        pergunta1.setResp2("Tóquio");
        pergunta1.setResp3("New York");
        pergunta1.setResp4("São Paulo");
        pergunta1.setRespCerto("Tóquio");
        perguntas.add(pergunta1);

        Questions pergunta2 = new Questions();
        pergunta2.setPergunta("Qual o maior classico no futebol ?");
        pergunta2.setResp1("Real Madrid x Barcelona");
        pergunta2.setResp2("River Plate x Boca Juniors");
        pergunta2.setResp3("Palmeiras x Corinthians");
        pergunta2.setResp4("Manchester United x Liverpool");
        pergunta2.setRespCerto("Real Madrid x Barcelona");
        perguntas.add(pergunta2);

        Questions pergunta3 = new Questions();
        pergunta3.setPergunta("Qual clube brasileiro tem mais mundias ?");
        pergunta3.setResp1("Corinthians");
        pergunta3.setResp2("Palmeiras");
        pergunta3.setResp3("São Paulo");
        pergunta3.setResp4("Internacional");
        pergunta3.setRespCerto("São Paulo");
        perguntas.add(pergunta3);

        Questions pergunta4 = new Questions();
        pergunta4.setPergunta("Qual a batalha mais sangrenta na historias das guerras ?");
        pergunta4.setResp1("Batalha dos 100 Anos");
        pergunta4.setResp2("Batalha de Stalingrado");
        pergunta4.setResp3("Operação Overloard");
        pergunta4.setResp4("Batalha de Salsu");
        pergunta4.setRespCerto("Batalha de Stalingrado");
        perguntas.add(pergunta4);

        Questions pergunta5 = new Questions();
        pergunta5.setPergunta("Quanto que é 10+10*2 ?");
        pergunta5.setResp1("0");
        pergunta5.setResp2("22");
        pergunta5.setResp3("30");
        pergunta5.setResp4("40");
        pergunta5.setRespCerto("22");
        perguntas.add(pergunta5);

        Questions pergunta6 = new Questions();
        pergunta6.setPergunta("Quais das escolas literarias não são coloniais ?");
        pergunta6.setResp1("Arcadismo");
        pergunta6.setResp2("Classicismo");
        pergunta6.setResp3("Barroco");
        pergunta6.setResp4("Realismo");
        pergunta6.setRespCerto("Realismo");
        perguntas.add(pergunta5);


    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    public void carregaPerguntaNoDesign() {
        barra.setProgress(0);
        indice = 1;
        Collections.shuffle(perguntas);
        acertos.setText("Acertos: " + acertosCount.toString());
        errados.setText("Erros: " + errosCount.toString());
        pergunta.setText(String.format("%s%d%s", mod1, indice, mod2));
        questao.setText(perguntas.get(indice).getPergunta());
        radio1.setText(perguntas.get(indice).getResp1());
        radio2.setText(perguntas.get(indice).getResp2());
        radio3.setText(perguntas.get(indice).getResp3());
        radio4.setText(perguntas.get(indice).getResp4());


    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onClick(View view) {
        if (enviar == view) {
            try {
                checkResults();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (radioGroup == this.radioGroup) {
            enviar.setEnabled(true);
            radioTocado = new RadioButton(this);

            if (i == radio1.getId()) {
                textoRadioSelecionado = radio1.getText().toString();
                radioTocado = radio1;
            }
            if (i == radio2.getId()) {
                textoRadioSelecionado = radio2.getText().toString();
                radioTocado = radio2;
            }
            if (i == radio3.getId()) {
                textoRadioSelecionado = radio3.getText().toString();
                radioTocado = radio3;
            }
            if (i == radio4.getId()) {
                textoRadioSelecionado = radio4.getText().toString();
                radioTocado = radio4;
            }
        }
    }

    @SuppressLint("DefaultLocale")
    public void checkResults() throws InterruptedException {
        radio1.setEnabled(false);
        radio2.setEnabled(false);
        radio3.setEnabled(false);
        radio4.setEnabled(false);
        enviar.setEnabled(false);

        if (textoRadioSelecionado.compareToIgnoreCase(perguntas.get(indice).getRespCerto()) == 0) {
            radioTocado.setBackgroundColor(Color.GREEN);
            acertosCount += 1;
        } else {
            radioTocado.setBackgroundColor(Color.RED);
            errosCount += 1;

        }
        barra.setProgress(barra.getProgress() + 20);
        limitePerguntas++;
        if (limitePerguntas >= 5) {
            Intent i = new Intent(this, Begin.class);
            startActivity(i);
            //acababou o quiz
        } else {
            handler.postDelayed(this, 1000);
        }
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void run() {
        indice++;
        pergunta.setText(String.format("%s%d%s", mod1, indice, mod2));
        questao.setText(perguntas.get(indice).getPergunta());
        radio1.setText(perguntas.get(indice).getResp1());
        radio2.setText(perguntas.get(indice).getResp2());
        radio3.setText(perguntas.get(indice).getResp3());
        radio4.setText(perguntas.get(indice).getResp4());
        acertos.setText(String.format("Acertos: %d", acertosCount));
        errados.setText(String.format("Erros: %d", errosCount));
        radioTocado.setBackgroundColor(Color.WHITE);
        radioTocado.setChecked(false);
        radio1.setEnabled(true);
        radio2.setEnabled(true);
        radio3.setEnabled(true);
        radio4.setEnabled(true);
        enviar.setEnabled(true);

    }
}