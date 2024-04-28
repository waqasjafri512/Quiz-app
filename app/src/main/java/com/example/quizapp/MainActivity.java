package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btn_one, btn_two, btn_three, btn_four;
    TextView tv_question;
    private Question question = new Question();
    private String answer;
    private int questionLength;
    Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        random = new Random();
        questionLength = question.questions.length;

        btn_one = findViewById(R.id.btn_one);
        btn_one.setOnClickListener(this::onClick);
        btn_two = findViewById(R.id.btn_two);
        btn_two.setOnClickListener(this::onClick);
        btn_three = findViewById(R.id.btn_three);
        btn_three.setOnClickListener(this::onClick);
        btn_four = findViewById(R.id.btn_four);
        btn_four.setOnClickListener(this::onClick);


        tv_question = findViewById(R.id.tv_question);

        NextQuestion(random.nextInt(questionLength));
    }

  //  @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_one ||
                v.getId() == R.id.btn_two ||
                v.getId() == R.id.btn_three ||
                v.getId() == R.id.btn_four) {
            Button clickedButton = (Button) v;
            if (clickedButton.getText().equals(answer)) {
                Toast.makeText(MainActivity.this, "You Are Correct", Toast.LENGTH_SHORT).show();
                NextQuestion(random.nextInt(questionLength));
            } else {
                GameOver();
            }
        }

    }

    private void GameOver() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder
                .setMessage("Game Over")
                .setCancelable(false)
                .setPositiveButton("New Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                });
        alertDialogBuilder.show();
    }

    private void NextQuestion(int num) {
        tv_question.setText(question.getQuestion(num));
        btn_one.setText(question.getchoice1(num));
        btn_two.setText(question.getchoice2(num));
        btn_three.setText(question.getchoice3(num));
        btn_four.setText(question.getchoice4(num));

        answer = question.getCorrectAnswer(num);
    }

}