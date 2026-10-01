package com.example.mileagecalculatorandroidapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText etDistance, etFuel;
    Button btnCalculate, btnReset;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        etDistance = findViewById(R.id.etDistance);
        etFuel = findViewById(R.id.etFuel);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnReset = findViewById(R.id.btnReset);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateMileage();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etDistance.setText("");
                etFuel.setText("");
                tvResult.setText("Mileage: -- km/L");
            }
        });

    }

    private void calculateMileage() {
        String distanceText = etDistance.getText().toString().trim();
        String fuelText = etFuel.getText().toString().trim();

        if (TextUtils.isEmpty(distanceText)) {
            etDistance.setError("Enter distance");
            etDistance.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(fuelText)) {
            etFuel.setError("Enter fuel used");
            etFuel.requestFocus();
            return;
        }

        try {
            double distance = Double.parseDouble(distanceText);
            double fuel = Double.parseDouble(fuelText);

            if (distance <= 0) {
                etDistance.setError("Distance must be greater than 0");
                return;
            }

            if (fuel <= 0) {
                etFuel.setError("Fuel must be greater than 0");
                return;
            }

            double mileage = distance / fuel;

            tvResult.setText(String.format(
                    Locale.getDefault(),
                    "Mileage: %.2f km/L",
                    mileage
            ));

        } catch (NumberFormatException e) {
            Toast.makeText(
                    this,
                    "Please enter valid numbers",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

}