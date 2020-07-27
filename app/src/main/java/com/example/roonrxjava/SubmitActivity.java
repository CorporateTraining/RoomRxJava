package com.example.roonrxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roonrxjava.entity.Person;

public class SubmitActivity extends AppCompatActivity {
    private Button submit;
    private EditText userName, userGender, userAge;
    private LocalPersonDataSource localPersonDataSource;
    private Boolean isCheck = true;
    private final static String USER_NAME_MESSAGE = "请重新输入姓名";
    private final static String USER_AGE_MESSAGE = "请重新输入性别";
    private final static String USER_GENDER_MESSAGE = "请重新输入年龄";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        submit = findViewById(R.id.user_submit);
        userGender = findViewById(R.id.user_gender);
        userAge = findViewById(R.id.user_age);
        userName = findViewById(R.id.user_name);
        localPersonDataSource = new LocalPersonDataSource(this);
        submit.setOnClickListener(view -> {
            textCheck(userName, USER_NAME_MESSAGE, isNameVerify());
            textCheck(userGender, USER_GENDER_MESSAGE, isGenderVerify());
            textCheck(userAge, USER_AGE_MESSAGE, isAgeVerify());
            if (isCheck) {
                localPersonDataSource.createPerson(
                        new Person(userName.getText().toString(),
                                Integer.parseInt(userGender.getText().toString()),
                                Integer.parseInt(userAge.getText().toString())));
            }else {
                isCheck = true;
            }
        });
    }

    private void textCheck(EditText editText, String message, Boolean isVerify) {
        if (isVerify) {
            isCheck = false;
            editText.setText("");
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isGenderVerify() {
        return "".equals(userGender.getText().toString()) || Integer.parseInt(userGender.getText().toString()) > 1;
    }

    private boolean isNameVerify() {
        return "".equals(userName.getText().toString());
    }

    private boolean isAgeVerify() {
        return "".equals(userAge.getText().toString()) || Integer.parseInt(userAge.getText().toString()) <= 0;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        localPersonDataSource.getCompositeDisposable().clear();
    }
}