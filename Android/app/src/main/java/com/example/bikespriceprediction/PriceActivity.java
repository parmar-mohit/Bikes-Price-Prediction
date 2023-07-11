package com.example.bikespriceprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PriceActivity extends AppCompatActivity {

    //EXTRAS
    public static final String EXTRA_PRICE = "EXTRA_PRICE";

    private TextView priceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);

        //Getting View
        priceTextView = (TextView) findViewById(R.id.priceTextView);

        //Setting Text
        int price = getIntent().getIntExtra(EXTRA_PRICE,0);

        if( price == 0){
            Intent intent = new Intent(this, ErrorActivity.class);
            startActivity(intent);
            finish();
        }
        priceTextView.setText("Estimated Price of Bike Rs. "+price);
    }
}