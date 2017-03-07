package com.cyrusv.onedayatatime;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Boolean ask = false;
        Bundle bund = getIntent().getExtras();
        if (bund != null) {
            ask = bund.getBoolean("askDate", false);
        }

        // Go to Countdown
        if (!ask && getSharedPreferences("userSettings", Context.MODE_PRIVATE).getLong(getString(R.string.date_key), -1) >= 0) {
            Intent intent = new Intent(this, CountdownActivity.class);
            startActivity(intent);
        }

        // Show Date Picker
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void saveDate(View view) {
        DatePicker pickedDate = (DatePicker) findViewById(R.id.startDate);
        Calendar calendar = Calendar.getInstance();
        calendar.set(pickedDate.getYear(), pickedDate.getMonth(), pickedDate.getDayOfMonth(),
                0, 0, 0);

        long ptime = calendar.getTimeInMillis();
        SharedPreferences sharedPref = getSharedPreferences("userSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        // Store 12am Epoch Time
        editor.putLong(getString(R.string.date_key), ptime);
        editor.apply();

        MainAppWidgetProvider.updateMyWidgets(getBaseContext());

        Intent intent = new Intent(this, CountdownActivity.class);
        startActivity(intent);
    }
}
