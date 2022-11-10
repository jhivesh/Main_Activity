package com.example.main_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {

    EditText usernameID, newPasswordID, confirmPasswordID;
    Button btnRegister, btnLogin;DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        usernameID = (EditText) findViewById(R.id.loginusername);
        newPasswordID = (EditText) findViewById(R.id.newPasswordID);
        confirmPasswordID = (EditText) findViewById(R.id.ConfirmPasswordID);

        btnRegister = (Button) findViewById(R.id.registerBtn);
        btnLogin = (Button) findViewById(R.id.loginBtn);

        myDB = new DBHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = usernameID.getText().toString();
                String pass = newPasswordID.getText().toString();
                String repass = confirmPasswordID.getText().toString();

                if (user.equals("") || pass.equals("") || repass.equals("")) {
                    Toast.makeText(RegisterPage.this, "Fill all the fields.", Toast.LENGTH_SHORT).show();
                }else
                {
                    if(pass.equals(repass)){
                        Boolean validResult = myDB.checkusername(user);
                        if (validResult == false)
                        {
                            Boolean registrationResult = myDB.insertData(user,pass);
                            if (registrationResult == true)
                            {
                                Toast.makeText(RegisterPage.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(RegisterPage.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(RegisterPage.this, "User already exist in the system", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(RegisterPage.this, "Password not matching", Toast.LENGTH_SHORT).show();
                    }
                }
                
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}