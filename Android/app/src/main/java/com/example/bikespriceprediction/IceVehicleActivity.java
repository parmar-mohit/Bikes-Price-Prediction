package com.example.bikespriceprediction;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;;

public class IceVehicleActivity extends AppCompatActivity {

    private static final String TAG = "IceVehicleActivity";

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
        JSONObject jsonObject = new JSONObject();

        String engineDisplacementString = engineDisplacementEditText.getText().toString();
        if( engineDisplacementString.equals("") ){
            Toast.makeText(this,"Enter Engine Displacement",Toast.LENGTH_SHORT).show();
            return;
        }

        String noOfCylindersString = (String)noOfCylindersSpinner.getSelectedItem();
        if( noOfCylindersString.equals("") ){
            Toast.makeText(this,"Select No Of Cylinders",Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            jsonObject.put("EngineDisplacement", Float.parseFloat(engineDisplacementString));
            jsonObject.put("Cylinders",Integer.parseInt(noOfCylindersString));
            jsonObject.put("Company",getIntent().getStringExtra(MainActivity.EXTRA_COMPANY));
            jsonObject.put("Country",getIntent().getStringExtra(MainActivity.EXTRA_COUNTRY));
            jsonObject.put("Horsepower",getIntent().getFloatExtra(MainActivity.EXTRA_HORSEPOWER,0));
            jsonObject.put("Torque",getIntent().getFloatExtra(MainActivity.EXTRA_TORQUE,0));
            jsonObject.put("SeatingCapacity",getIntent().getIntExtra(MainActivity.EXTRA_SEATING_CAPACITY,0));
            jsonObject.put("Year",getIntent().getIntExtra(MainActivity.EXTRA_YEAR,0));
            jsonObject.put("Looks",getIntent().getStringExtra(MainActivity.EXTRA_LOOKS));
            jsonObject.put("BodyType",getIntent().getStringExtra(MainActivity.EXTRA_BODY_TYPE));
            jsonObject.put("EV",getIntent().getIntExtra(MainActivity.EXTRA_ELECTRIC_VEHICLE,0));
            jsonObject.put("MotorPower",0);

            new GetPrice().execute(jsonObject);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private class GetPrice extends AsyncTask<JSONObject,Void,Integer> {
        @Override
        protected Integer doInBackground(JSONObject... jsonObjects){
            try {
                JSONObject headerObject = jsonObjects[0];
                URL url = new URL("http://192.168.1.26/getPrice");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                //Adding Header Parameters
                connection.setRequestProperty("Company",headerObject.getString("Company"));
                connection.setRequestProperty("Country",headerObject.getString("Country"));
                connection.setRequestProperty("Horsepower",headerObject.getDouble("Horsepower")+"");
                connection.setRequestProperty("Torque",headerObject.getDouble("Torque")+"");
                connection.setRequestProperty("SeatingCapacity",headerObject.getInt("SeatingCapacity")+"");
                connection.setRequestProperty("Year",headerObject.getInt("Year")+"");
                connection.setRequestProperty("Looks",headerObject.getString("Looks"));
                connection.setRequestProperty("BodyType",headerObject.getString("BodyType"));
                connection.setRequestProperty("Cylinders",headerObject.getInt("Cylinders")+"");
                connection.setRequestProperty("EV",headerObject.getInt("EV")+"");
                connection.setRequestProperty("EngineDisplacement",headerObject.getDouble("EngineDisplacement")+"");
                connection.setRequestProperty("MotorPower",headerObject.getInt("MotorPower")+"");

                int responseCode = connection.getResponseCode();
                if( responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    JSONObject responseJsonObject = new JSONObject(response.toString());
                    return responseJsonObject.getInt("Price");
                }

                Log.e(TAG,"Cannot Connect to Server");
            }catch ( Exception e){
                e.printStackTrace();
            }
            return 0;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            Intent intent = new Intent(IceVehicleActivity.this,PriceActivity.class);
            intent.putExtra(PriceActivity.EXTRA_PRICE,integer);
            startActivity(intent);
        }
    }
}

