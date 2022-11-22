package com.example.main_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;


public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button BtnLogin; DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        username = (EditText) findViewById(R.id.loginusername);
        password = (EditText) findViewById(R.id.loginpassword);
        BtnLogin = (Button) findViewById((R.id.btnLogin));

        myDB = new DBHelper(this);

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter the credentials. ", Toast.LENGTH_SHORT).show();
                }else
                {
                    Boolean loginValid = myDB.checkusernamePassword(user,pass);
                    if (loginValid == true) {
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
                
            }
        });
    }
}