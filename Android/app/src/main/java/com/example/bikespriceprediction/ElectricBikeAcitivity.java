package com.example.bikespriceprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ElectricBikeAcitivity extends AppCompatActivity {

    private EditText motorPowerEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electric_bike_acitivity);

        //Getting Views
        motorPowerEditText = (EditText) findViewById(R.id.motorPowerEditText);
    }

    public void getPriceButtonOnClick(View view){

    }
}