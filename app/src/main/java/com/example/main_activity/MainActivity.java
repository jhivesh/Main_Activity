package com.example.main_activity;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("myTag", "Lifecycle onCreate onStart() is on ");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("myTag", "Function onStart() is on ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_register_page);
        //setContentView(R.layout.activity_main);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("myTag", "Function onRestart() is on ");
    }

    public void calculateBMI(View v){

        EditText Weight = findViewById(R.id.weight_input);
        String strWeight = Weight.getText().toString();
        Log.i("Get Weight", strWeight);

        EditText Height = findViewById(R.id.height_input);
        String strHeight = Height.getText().toString();
        Log.i("Get Weight", strHeight);

        Double dHeight = Double.parseDouble(strHeight);
        Double dWeight = Double.parseDouble(strWeight);
        Double dBMI = (dWeight/(dHeight * dHeight));

        BigDecimal bd = BigDecimal.valueOf(dBMI);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        String StrBMI = bd.toString();

        String strBMI = Double.toString(dBMI);
        Log.i("Set BMI", strBMI);

        //TextView t = findViewById(R.id.bmiField);
        ((TextView)findViewById(R.id.bmiField)).setText(StrBMI);

    }
}