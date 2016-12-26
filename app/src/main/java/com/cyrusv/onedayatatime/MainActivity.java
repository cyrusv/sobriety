package com.cyrusv.onedayatatime;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.Days;
import org.joda.time.DateTime;


public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.onedayatatime.START_DATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
//        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
//        ActionBar actionBar = getActionBar();
//        actionBar.hide();
    }

    public void saveDate(View view) {
        Intent intent = new Intent(this, CountdownActivity.class);
        DatePicker pickedDate = (DatePicker) findViewById(R.id.startDate);
        Calendar calendar = Calendar.getInstance();
        calendar.set(pickedDate.getYear(), pickedDate.getMonth(), pickedDate.getDayOfMonth());
        Date today = new Date();
        int elapsedDays = Days.daysBetween(new DateTime(calendar), new DateTime(today)).getDays();
        String message = Integer.toString(elapsedDays);

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
