package com.a5androidintern2.game;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //текстовые поля.
    TextView tvTitleScore;
    TextView tvValueScore;
    TextView tvTitleTime;
    TextView tvValueTime;
    TextView tvTitleTest;
    TextView tvValueTest;

    //кнопка меню.
    Button btnMenu;

    //картинка с заданием.
    ImageView imgTask;

    //кнопки.
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;

    //переменная содержит очки.
    public static int score;
    //public static S intScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //находим элементы экрана по id.

        tvTitleScore = (TextView) findViewById(R.id.tvTitleScore);
        tvValueScore = (TextView) findViewById(R.id.tvValueScore);
        tvTitleTime = (TextView) findViewById(R.id.tvTitleTime);
        tvValueTime = (TextView) findViewById(R.id.tvValueTime);
        tvTitleTest = (TextView) findViewById(R.id.tvTitleTest);
        tvValueTest = (TextView) findViewById(R.id.tvValueTest);

        btnMenu = (Button) findViewById(R.id.btnMenu);

        imgTask = (ImageView) findViewById(R.id.imgTask);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);

        //назначаем обработчика события для кнопок.
        btnMenu.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

        //tvValueScore.setText((String)score);

    }

    @Override
    public void onClick(View view) {
        int btnClickedId = view.getId();
        switch (btnClickedId) {
            case R.id.btnMenu:
                tvValueTest.setText("you pressed button MENU");
                break;
            case R.id.btn1:
                tvValueTest.setText("you pressed button 1");
                break;
            case R.id.btn2:
                tvValueTest.setText("you pressed button 2");
                break;
            case R.id.btn3:
                tvValueTest.setText("you pressed button 3");
                break;
            case R.id.btn4:
                tvValueTest.setText("you pressed button 4");
                break;
            case R.id.btn5:
                tvValueTest.setText("you pressed button 5");
                break;
            case R.id.btn6:
                tvValueTest.setText("you pressed button 6");
                break;
            case R.id.btn7:
                tvValueTest.setText("you pressed button 7");
                break;
            case R.id.btn8:
                tvValueTest.setText("you pressed button 8");
                break;
            case R.id.btn9:
                tvValueTest.setText("you pressed button 9");
                break;
        }

    }


    public static int incrementScore(boolean isCorrect) {
        int newScore;
        if (isCorrect) {
            newScore = score + 100;
        } else {
            newScore = score;
        }
        return newScore;
    }
}
