package com.example.bikespriceprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class IceVehicleActivity extends AppCompatActivity {

    private EditText engineDisplacementEditText;
    private Spinner noOfCylindersSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ice_vehicle);

        //Getting Views
        engineDisplacementEditText = (EditText) findViewById(R.id.engineDisplacementEditText);
        noOfCylindersSpinner = (Spinner) findViewById(R.id.noOfCylindersSpinner);

        //Population Spinner
        ArrayAdapter<CharSequence> noOfCylindersSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.no_of_cylinders, android.R.layout.simple_spinner_item);
        noOfCylindersSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        noOfCylindersSpinner.setAdapter(noOfCylindersSpinnerAdapter);
    }

    public void getPriceOnClick(View view){

    }
}