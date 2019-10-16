package com.jjj.easylearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    Button mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        mTextUsername = (EditText)findViewById(R.id.edit_username);
        mTextPassword = (EditText)findViewById(R.id.edit_pass);
        mTextCnfPassword = (EditText)findViewById(R.id.edit_cnf_pass);
        mButtonRegister = (Button) findViewById(R.id.button_register);
        mTextViewLogin = (Button)findViewById(R.id.textView_login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnf_pwd = mTextCnfPassword.getText().toString().trim();

                if (pwd.equals(cnf_pwd)) {
                    long val = db.addUser(user, pwd);
                    if (val > 0) {
                        Toast.makeText(RegisterActivity.this, "You have registered", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(moveToLogin);
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Registeration Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
