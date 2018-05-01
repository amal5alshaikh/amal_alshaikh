package com.example.android.quizapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import static com.example.android.quizapp.R.id.checkBox_First;


public class MainActivity extends AppCompatActivity {

    int firstQ;
    int secondQ;
    int thirdQ;
    int fourthQ;
    int fifthQ;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public int getFirstQ(View v) {
        boolean selected = ((RadioButton) v).isChecked();
        switch (v.getId()) {
            case R.id.firstQ_RadioButton_A:
                if (selected) {
                    firstQ = 10;
                }
                break;
            case R.id.secondQ_RadioButton_A:
                if (selected) {
                    firstQ = 0;
                }
                break;
            case R.id.thirdQ_RadioButton_A:
                if (selected) {
                    firstQ = 0;
                }
                break;
        }
        return firstQ;
    }

    public int getSecondQ(View v) {

        boolean selected = ((RadioButton) v).isChecked();
        switch (v.getId()) {

            case R.id.firstQ_RadioButton_B:
                if (selected) {
                    secondQ = 10;
                }
                break;
            case R.id.secondQ_RadioButton_B:
                if (selected) {
                    secondQ = 0;
                }
                break;
            case R.id.thirdQ_RadioButton_B:
                if (selected) {
                    secondQ = 0;
                }
                break;
        }
        return secondQ;
    }

    public int getThirdQ() {
        CheckBox checkBoxFirst = (CheckBox) findViewById(checkBox_First);
        CheckBox checkBoxSecond = (CheckBox) findViewById(R.id.checkBox_Second);
        CheckBox checkBoxThird = (CheckBox) findViewById(R.id.checkBox_Third);
        if (checkBoxThird.isChecked()) {
            thirdQ = 0;
        } else if (checkBoxFirst.isChecked() & checkBoxSecond.isChecked() & checkBoxThird.isChecked()) {
            thirdQ = 0;
        } else if (checkBoxFirst.isChecked() & checkBoxSecond.isChecked()) {
            thirdQ = 10;
        } else if (checkBoxFirst.isChecked()) {
            thirdQ = 5;
        } else if (checkBoxSecond.isChecked()) {
            thirdQ = 5;
        } else if (!checkBoxFirst.isChecked()) {
            thirdQ = 0;
        } else if (!checkBoxSecond.isChecked()) {
            thirdQ = 0;
        }
        return thirdQ;
    }


    public int getFourthQ() {

        EditText editAnswer = (EditText) findViewById(R.id.editTextAnswer);
        if (editAnswer.getText().toString().trim().equalsIgnoreCase("Mercury")) {
            return fourthQ = 10;
        } else return fourthQ = 0;
    }

    public int getFifthQ(View v) {
        boolean selected = ((RadioButton) v).isChecked();
        switch (v.getId()) {
            case R.id.firstQ_RadioButton_C:
                if (selected) {
                    fifthQ = 10;
                }
                break;
            case R.id.secondQ_RadioButton_C:
                if (selected) {
                    fifthQ = 0;
                }
                break;
            case R.id.thirdQ_RadioButton_C:
                if (selected) {
                    fifthQ = 0;
                }
                break;
        }
        return fifthQ;
    }

    public int getScore(int score) {
        this.getFourthQ();
        this.getThirdQ();
        score = firstQ + secondQ + thirdQ + fourthQ + fifthQ;
        return score;
    }


    public void makeToast(View v) {

        Context context = getApplicationContext();
        CharSequence text = "This is your Score   " + getScore(score);
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }


}




