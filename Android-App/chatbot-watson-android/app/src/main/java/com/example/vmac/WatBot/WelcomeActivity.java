package com.example.vmac.WatBot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    int type = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


    }

    public void onNextClicked(View view) {
        if (type == -1) {
            Toast.makeText(this, "Please make a selection", Toast.LENGTH_SHORT).show();
            return;
        }
        if (type == 0) {
            // Doctor
            Intent intent = new Intent(this, DiseasesActivity.class);
            startActivity(intent);
        } else {
            // Resident
            Intent intent = new Intent(this, DiseasesActivity.class);
            intent.putExtra("RESIDENT", true);
            startActivity(intent);
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.resident_radio:
                if (checked) {
                    ((RadioButton) findViewById(R.id.doctor_radio)).setChecked(false);
                    type = 1;
                } else {
                    ((RadioButton) findViewById(R.id.doctor_radio)).setChecked(true);
                    type = 0;
                }
                break;
            case R.id.doctor_radio:
                if (checked) {
                    ((RadioButton) findViewById(R.id.resident_radio)).setChecked(false);
                    type = 0;
                } else {
                    ((RadioButton) findViewById(R.id.resident_radio)).setChecked(true);
                    type = 1;
                }
                break;
        }
    }
}
