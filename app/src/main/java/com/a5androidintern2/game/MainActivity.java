package com.a5androidintern2.game;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static com.a5androidintern2.game.R.drawable.ic_android_black_24dp;
import static com.a5androidintern2.game.R.drawable.ic_attach_file_black_24dp;
import static com.a5androidintern2.game.R.drawable.ic_audiotrack_black_24dp;
import static com.a5androidintern2.game.R.drawable.ic_cloud_queue_black_24dp;
import static com.a5androidintern2.game.R.drawable.ic_directions_run_black_24dp;
import static com.a5androidintern2.game.R.drawable.ic_fitness_center_black_24dp;
import static com.a5androidintern2.game.R.drawable.ic_play_arrow_black_24dp;
import static com.a5androidintern2.game.R.drawable.ic_send_black_24dp;
import static com.a5androidintern2.game.R.drawable.ic_star_black_24dp;


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

    //кнопка выход.
    Button btnQuit;

    //переменная, указывающая, надо ли выйти из программы.
    boolean isQuitFlag = false;

    //картинка с заданием.
    ImageView imgTask;

    //игровые кнопки.
    ImageButton btn0;
    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;
    ImageButton btn4;
    ImageButton btn5;
    ImageButton btn6;
    ImageButton btn7;
    ImageButton btn8;

    //переменная содержит очки.
    public static int score;

    //HashMap-таблица рисунков от 0 до 8.
    HashMap<Integer, BitmapDrawable> tableOfPictures = new HashMap<>();

    //генератор случайных чисел, который случайным образом
    //выберет группу (3) картинок для раунда из общей базы картинок.
    Random randomGroup = new Random();

    //генератор случайных чисел, который случайным образом
    //выберет картинку из группы картинок данного раунда.
    Random randomPicture = new Random();


    //выбираем группу.
    //массив, содержащий id картинок в группе (j=0..sizeOfThePictureGroup-1).
    //sizeOfThePictureGroup - размер группы картинок. Сколько разновидностей картинок
    //будет в каждом раунде.
    int sizeOfThePictureGroup = 3;
    int[] pictureGroup = new int[sizeOfThePictureGroup];

    //переменная, содержащая id картинки на задании.
    int idOfTheTaskPicture;

    //массив, содержащий id картинки на i-й кнопке (i=0..8).
    int[] idOfTheButtonPicture = new int[9];

    //переменная содержит число "правильных" картинок.
    int numberOfCorrectPictures;

    //переменная счётчик "правильных" картинок.
    static int countOfCorrectPictures = 0;

    //список использованных картинок. Если мы уже нажади на картинку,
    //повторное нажатие не должно прибавлять очки.
    static List<Integer> usedPictures = new ArrayList<>();

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
        btnQuit = (Button) findViewById(R.id.btnQuit);

        imgTask = (ImageView) findViewById(R.id.imgTask);

        btn0 = (ImageButton) findViewById(R.id.btn0);
        btn1 = (ImageButton) findViewById(R.id.btn1);
        btn2 = (ImageButton) findViewById(R.id.btn2);
        btn3 = (ImageButton) findViewById(R.id.btn3);
        btn4 = (ImageButton) findViewById(R.id.btn4);
        btn5 = (ImageButton) findViewById(R.id.btn5);
        btn6 = (ImageButton) findViewById(R.id.btn6);
        btn7 = (ImageButton) findViewById(R.id.btn7);
        btn8 = (ImageButton) findViewById(R.id.btn8);


        //массив игровых кнопок.
        ImageButton[] playButtons = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8};


        //присваиваем картинкам переменные, по которым можно к ним обратиться.
        BitmapDrawable bd0 = (BitmapDrawable) getResources().getDrawable(ic_star_black_24dp);
        BitmapDrawable bd1 = (BitmapDrawable) getResources().getDrawable(ic_android_black_24dp);
        BitmapDrawable bd2 = (BitmapDrawable) getResources().getDrawable(ic_attach_file_black_24dp);
        BitmapDrawable bd3 = (BitmapDrawable) getResources().getDrawable(ic_audiotrack_black_24dp);
        BitmapDrawable bd4 = (BitmapDrawable) getResources().getDrawable(ic_cloud_queue_black_24dp);
        BitmapDrawable bd5 = (BitmapDrawable) getResources().getDrawable(ic_directions_run_black_24dp);
        BitmapDrawable bd6 = (BitmapDrawable) getResources().getDrawable(ic_fitness_center_black_24dp);
        BitmapDrawable bd7 = (BitmapDrawable) getResources().getDrawable(ic_play_arrow_black_24dp);
        BitmapDrawable bd8 = (BitmapDrawable) getResources().getDrawable(ic_send_black_24dp);


        //создаём таблицу картинок. Каждой картинке сопоставим номер 0-8.
        tableOfPictures.put(0, bd0);
        tableOfPictures.put(1, bd1);
        tableOfPictures.put(2, bd2);
        tableOfPictures.put(3, bd3);
        tableOfPictures.put(4, bd4);
        tableOfPictures.put(5, bd5);
        tableOfPictures.put(6, bd6);
        tableOfPictures.put(7, bd7);
        tableOfPictures.put(8, bd8);


        //заполняем массив группы картинок, которые будут участвовать в раунде.
        do {
            for (int j = 0; j < pictureGroup.length; j++) {
                pictureGroup[j] = randomGroup.nextInt(9);
            }
        } while (hasEqualElements(pictureGroup));
        //в результате, получили группу из 3-х картинок для раунда, взятых из большой базы картинок.
        //Мы проверили, чтобы все картинки в группе были разные.


        //заполняем ImageView.
        int taskPictureGroupElement = randomPicture.nextInt(sizeOfThePictureGroup);
        //получили индекс элемента массива.

        idOfTheTaskPicture = pictureGroup[taskPictureGroupElement];
        //получили ключ картинки.

        imgTask.setImageDrawable(tableOfPictures.get(idOfTheTaskPicture));
        //получили картинку по ключу. И добавили её в imgTask.

        //заполняем кнопки картинками из группы случайно.
        int[] buttonsPictureGroupArray = new int[9];
        for (int j = 0; j < buttonsPictureGroupArray.length; j++) {
            buttonsPictureGroupArray[j] = randomPicture.nextInt(sizeOfThePictureGroup);
            //получили массив из 9 чисел, каждое число случайное (0,1,2).
            //Это индексы массива группы картинок.

        }

        for (int i = 0; i < playButtons.length; i++) {

            idOfTheButtonPicture[i] = pictureGroup[buttonsPictureGroupArray[i]];
            //получаем ключи картинок,
            //они содержатся в соответствующих элементах массива группы картинок.


            playButtons[i].setImageDrawable(tableOfPictures.get(idOfTheButtonPicture[i]));
            //получаем картинки по ключам. И добавляем их на кнопки.

        }


        //назначаем обработчика события для кнопок.
        btnMenu.setOnClickListener(this);
        btnQuit.setOnClickListener(this);
        for (int i = 0; i < playButtons.length; i++) {
            playButtons[i].setOnClickListener(this);
        }

        numberOfCorrectPictures = numberOfCorrectPictures(idOfTheTaskPicture, idOfTheButtonPicture);


    }

    //метод определяет, сколько картинок таких же, что и на задании (imgTask).
    public int numberOfCorrectPictures(int idOfTheTaskPicture, int[] idOfTheButtonPicture) {
        int numberOfCorrectPictures = 0;
        for (int i = 0; i < idOfTheButtonPicture.length; i++) {
            if (idOfTheButtonPicture[i] == idOfTheTaskPicture) {
                numberOfCorrectPictures++;
            }
        }
        return numberOfCorrectPictures;
    }


    private boolean hasEqualElements(int[] pictureGroup) {
        boolean flag = false;
        for (int i = 0; i < pictureGroup.length; i++) {
            for (int j = 0; j < pictureGroup.length; j++) {
                if (pictureGroup[i] == pictureGroup[j] && i != j) {
                    flag = true;
                }
            }
        }
        return flag;
    }


    @Override
    public void onClick(View view) {
        int btnClickedId = view.getId();
        String scoreString;
        switch (btnClickedId) {
            case R.id.btnMenu:

                tvValueTest.setText("you pressed button MENU. NumberOfCorrectPictures= " + numberOfCorrectPictures + "\n" +
                        "countOfCorrectPictures= " + countOfCorrectPictures);
                break;
            case R.id.btnQuit:

                tvValueTest.setText("you pressed button Quit. NumberOfCorrectPictures= " + numberOfCorrectPictures + "\n" +
                        "countOfCorrectPictures= " + countOfCorrectPictures);
                isQuitFlag = true;
                break;
            case R.id.btn0:
                tvValueTest.setText("you pressed button 0");
                //увеличиваем очки, если нажатой картинки совпадает с картинки-задания.
                score += incrementScore(idOfTheButtonPicture[0] == idOfTheTaskPicture, R.id.btn0);
                scoreString = String.valueOf(score);

                //выводим значение очков в поле tvValueScore.
                tvValueScore.setText(scoreString);

                //добавляем id картинки в список использованных картинок.
                if (idOfTheButtonPicture[0] == idOfTheTaskPicture) {
                    usedPictures.add(R.id.btn0);
                }
                break;
            case R.id.btn1:
                tvValueTest.setText("you pressed button 1");
                //увеличиваем очки, если нажатой картинки совпадает с картинки-задания.
                score += incrementScore(idOfTheButtonPicture[1] == idOfTheTaskPicture, R.id.btn1);
                scoreString = String.valueOf(score);
                //выводим значение очков в поле tvValueScore.
                tvValueScore.setText(scoreString);

                //добавляем id картинки в список использованных картинок.
                if (idOfTheButtonPicture[1] == idOfTheTaskPicture) {
                    usedPictures.add(R.id.btn1);
                }
                break;
            case R.id.btn2:
                tvValueTest.setText("you pressed button 2");
                //увеличиваем очки, если нажатой картинки совпадает с картинки-задания.
                score += incrementScore(idOfTheButtonPicture[2] == idOfTheTaskPicture, R.id.btn2);
                scoreString = String.valueOf(score);
                //выводим значение очков в поле tvValueScore.
                tvValueScore.setText(scoreString);

                //добавляем id картинки в список использованных картинок.
                if (idOfTheButtonPicture[2] == idOfTheTaskPicture) {
                    usedPictures.add(R.id.btn2);
                }
                break;
            case R.id.btn3:
                tvValueTest.setText("you pressed button 3");
                //увеличиваем очки, если нажатой картинки совпадает с картинки-задания.
                score += incrementScore(idOfTheButtonPicture[3] == idOfTheTaskPicture, R.id.btn3);
                scoreString = String.valueOf(score);
                //выводим значение очков в поле tvValueScore.
                tvValueScore.setText(scoreString);

                //добавляем id картинки в список использованных картинок.
                if (idOfTheButtonPicture[3] == idOfTheTaskPicture) {
                    usedPictures.add(R.id.btn3);
                }
                break;
            case R.id.btn4:
                tvValueTest.setText("you pressed button 4");
                //увеличиваем очки, если нажатой картинки совпадает с картинки-задания.
                score += incrementScore(idOfTheButtonPicture[4] == idOfTheTaskPicture, R.id.btn4);
                scoreString = String.valueOf(score);
                //выводим значение очков в поле tvValueScore.
                tvValueScore.setText(scoreString);

                //добавляем id картинки в список использованных картинок.
                if (idOfTheButtonPicture[4] == idOfTheTaskPicture) {
                    usedPictures.add(R.id.btn4);
                }
                break;
            case R.id.btn5:
                tvValueTest.setText("you pressed button 5");
                //увеличиваем очки, если нажатой картинки совпадает с картинки-задания.
                score += incrementScore(idOfTheButtonPicture[5] == idOfTheTaskPicture, R.id.btn5);
                scoreString = String.valueOf(score);
                //выводим значение очков в поле tvValueScore.
                tvValueScore.setText(scoreString);

                //добавляем id картинки в список использованных картинок.
                if (idOfTheButtonPicture[5] == idOfTheTaskPicture) {
                    usedPictures.add(R.id.btn5);
                }
                break;
            case R.id.btn6:
                tvValueTest.setText("you pressed button 6");
                //увеличиваем очки, если нажатой картинки совпадает с картинки-задания.
                score += incrementScore(idOfTheButtonPicture[6] == idOfTheTaskPicture, R.id.btn6);
                scoreString = String.valueOf(score);
                //выводим значение очков в поле tvValueScore.
                tvValueScore.setText(scoreString);

                //добавляем id картинки в список использованных картинок.
                if (idOfTheButtonPicture[6] == idOfTheTaskPicture) {
                    usedPictures.add(R.id.btn6);
                }
                break;
            case R.id.btn7:
                tvValueTest.setText("you pressed button 7");
                //увеличиваем очки, если нажатой картинки совпадает с картинки-задания.
                score += incrementScore(idOfTheButtonPicture[7] == idOfTheTaskPicture, R.id.btn7);
                scoreString = String.valueOf(score);
                //выводим значение очков в поле tvValueScore.
                tvValueScore.setText(scoreString);

                //добавляем id картинки в список использованных картинок.
                if (idOfTheButtonPicture[7] == idOfTheTaskPicture) {
                    usedPictures.add(R.id.btn7);
                }
                break;
            case R.id.btn8:
                tvValueTest.setText("you pressed button 8");
                //увеличиваем очки, если нажатой картинки совпадает с картинки-задания.
                score += incrementScore(idOfTheButtonPicture[8] == idOfTheTaskPicture, R.id.btn8);
                scoreString = String.valueOf(score);
                //выводим значение очков в поле tvValueScore.
                tvValueScore.setText(scoreString);

                //добавляем id картинки в список использованных картинок.
                if (idOfTheButtonPicture[8] == idOfTheTaskPicture) {
                    usedPictures.add(R.id.btn8);
                }
                break;
        }

    }


    public static int incrementScore(boolean isCorrect, int pictureId) {
        int deltaScore = 0;
        //если картинка кнопки = картинке задания.
        if (isCorrect) {
            //если картинки нет в списке использованных картинок. (данную кнопку ещё не нажимали).
            if (usedPictures.contains(pictureId) == false) {
                deltaScore = 100;
                countOfCorrectPictures++;
            }
        } else {
            deltaScore = 0;
        }
        return deltaScore;
    }


}
