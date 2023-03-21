package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class Quiz extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener{
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
    private Button enviar;
    private ArrayList<Questions> perguntas = new ArrayList<>();
    private int indice;
    private String mod1;
    private String mod2;

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
        enviar = (Button) findViewById(R.id.button2);

        enviar.setOnClickListener(this);
        carregaPerguntas();
        mod1 = "Pergunta: ";
        mod2 = "/10";
        carregaPerguntaNoDesign();
    }

    public void carregaPerguntas(){
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
    public void carregaPerguntaNoDesign(){
        barra.setProgress(0);
        indice = 1;
        Collections.shuffle(perguntas);
        acertos.setText(Integer.toString(0));
        errados.setText(Integer.toString(0));
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
        if(enviar == view){
            indice++;
            pergunta.setText(String.format("%s%d%s", mod1, indice, mod2));
            questao.setText(perguntas.get(indice).getPergunta());
            radio1.setText(perguntas.get(indice).getResp1());
            radio2.setText(perguntas.get(indice).getResp2());
            radio3.setText(perguntas.get(indice).getResp3());
            radio4.setText(perguntas.get(indice).getResp4());

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        
    }
}