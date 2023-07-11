package com.example.bikespriceprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Extras
    public static final String EXTRA_COMPANY = "EXTRA_COMPANY";
    public static final String EXTRA_COUNTRY = "EXTRA_COUNTRY";
    public static final String EXTRA_HORSEPOWER = "EXTRA_HORSEPOWER";
    public static final String EXTRA_TORQUE = "EXTRA_TORQUE";
    public static final String EXTRA_SEATING_CAPACITY = "EXTRA_SEATING_CAPACITY";
    public static final String EXTRA_YEAR = "EXTRA_YEAR";
    public static final String EXTRA_LOOKS = "EXTRA_LOOKS";
    public static final String EXTRA_BODY_TYPE = "EXTRA_BODY_TYPE";
    public static final String EXTRA_ELECTRIC_VEHICLE = "EXTRA_ELECTRIC_VEHICLE";

    private Spinner companySpinner,countrySpinner,seatingCapacitySpinner,looksSpinner,bodyTypeSpinner;
    private EditText horsepowerEditText,torqueEditText,yearEditText;
    private Switch electricVehicleSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting View from activity
        companySpinner = (Spinner) findViewById(R.id.companySpinner);
        countrySpinner = (Spinner) findViewById(R.id.countrySpinner);
        horsepowerEditText = (EditText) findViewById(R.id.horsepowerEditText);
        torqueEditText = (EditText) findViewById(R.id.torqueEditText);
        seatingCapacitySpinner = (Spinner) findViewById(R.id.seatingCapacitySpinner);
        yearEditText = (EditText) findViewById(R.id.yearEditText);
        looksSpinner = (Spinner) findViewById(R.id.looksSpinner);
        bodyTypeSpinner = (Spinner) findViewById(R.id.bodyTypeSpinner);
        electricVehicleSwitch = (Switch) findViewById(R.id.electricVehicleSwitch) ;

        //Filling Spinner
        ArrayAdapter<CharSequence> companySpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.company_name, android.R.layout.simple_spinner_item);
        companySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        companySpinner.setAdapter(companySpinnerAdapter);

        ArrayAdapter<CharSequence> countrySpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.country_list, android.R.layout.simple_spinner_item);
        countrySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countrySpinnerAdapter);

        ArrayAdapter<CharSequence> seatingCapacitySpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.seating_capacity, android.R.layout.simple_spinner_item);
        seatingCapacitySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seatingCapacitySpinner.setAdapter(seatingCapacitySpinnerAdapter);

        ArrayAdapter<CharSequence> looksSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.looks_list, android.R.layout.simple_spinner_item);
        looksSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        looksSpinner.setAdapter(looksSpinnerAdapter);

        ArrayAdapter<CharSequence> bodyTypeSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.body_type_list, android.R.layout.simple_spinner_item);
        bodyTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bodyTypeSpinner.setAdapter(bodyTypeSpinnerAdapter);
    }

    public void proceedButtonOnClick(View view){
        String company = (String)companySpinner.getSelectedItem();
        if( company.equals("") ){
            Toast.makeText(this,"Select Company",Toast.LENGTH_SHORT).show();
            return;
        }

        String country = (String)countrySpinner.getSelectedItem();
        if( country.equals("") ){
            Toast.makeText(this,"Select Country",Toast.LENGTH_SHORT).show();
            return;
        }

        String horsepowerString = horsepowerEditText.getText().toString();
        if( horsepowerString.equals("") ){
            Toast.makeText(this,"Enter Horsepower",Toast.LENGTH_SHORT).show();
            return;
        }
        float horsepower = Float.parseFloat(horsepowerString);

        String torqueString = torqueEditText.getText().toString();
        if( torqueString.equals("") ){
            Toast.makeText(this,"Enter Torque",Toast.LENGTH_SHORT).show();
            return;
        }
        float torque = Float.parseFloat(torqueString);

        String seatingCapacityString = (String)seatingCapacitySpinner.getSelectedItem();
        if( seatingCapacityString.equals("") ){
            Toast.makeText(this,"Select Seating Capacity",Toast.LENGTH_SHORT).show();
            return;
        }
        int seatingCapacity = Integer.parseInt(seatingCapacityString);

        String yearString = yearEditText.getText().toString();
        if( yearString.equals("") ){
            Toast.makeText(this,"Enter Year",Toast.LENGTH_SHORT).show();
            return;
        }
        int year = Integer.parseInt(yearString);

        String looks = (String) looksSpinner.getSelectedItem();
        if( looks.equals("") ){
            Toast.makeText(this,"Select Looks",Toast.LENGTH_SHORT).show();
            return;
        }

        String bodyType = (String) bodyTypeSpinner.getSelectedItem();
        if( bodyType.equals("") ){
            Toast.makeText(this,"Select Body Type",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = null;
        if( electricVehicleSwitch.isChecked() ){
            intent = new Intent(this, ElectricBikeAcitivity.class);
            intent.putExtra(EXTRA_ELECTRIC_VEHICLE,1);
        }else{
            intent = new Intent(this,IceVehicleActivity.class);
            intent.putExtra(EXTRA_ELECTRIC_VEHICLE,0);
        }
        intent.putExtra(EXTRA_COMPANY,company);
        intent.putExtra(EXTRA_COUNTRY,country);
        intent.putExtra(EXTRA_HORSEPOWER,horsepower);
        intent.putExtra(EXTRA_TORQUE,torque);
        intent.putExtra(EXTRA_SEATING_CAPACITY,seatingCapacity);
        intent.putExtra(EXTRA_YEAR,year);
        intent.putExtra(EXTRA_LOOKS,looks);
        intent.putExtra(EXTRA_BODY_TYPE,bodyType);
        startActivity(intent);
    }
}