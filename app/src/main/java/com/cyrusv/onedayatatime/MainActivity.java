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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void saveDate(View view) {
        Intent intent = new Intent(this, CountdownActivity.class);
        DatePicker pickedDate = (DatePicker) findViewById(R.id.startDate);
        Calendar calendar = Calendar.getInstance();
        calendar.set(pickedDate.getYear(), pickedDate.getMonth(), pickedDate.getDayOfMonth());
        SharedPreferences sharedPref = getSharedPreferences("userSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(getString(R.string.date_key), calendar.getTime().getTime());
        editor.commit();

        startActivity(intent);
    }
}
