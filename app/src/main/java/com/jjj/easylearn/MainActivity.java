package com.jjj.easylearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    Button mTextViewRegister;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edit_username);
        mTextPassword = (EditText)findViewById(R.id.edit_pass);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mTextViewRegister = (Button) findViewById(R.id.text_register);

        mTextViewRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });


        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);
//                if(轉到登入後頁面
                if(res == true){
                    Intent HomePage = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(HomePage);
                }
                else {
                    Toast.makeText(MainActivity.this,"Login Error",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
