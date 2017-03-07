package com.cyrusv.onedayatatime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CountdownActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_countdown);

        long startDate = getSharedPreferences("userSettings", Context.MODE_PRIVATE).getLong(getString(R.string.date_key), 0);
        long today = new Date().getTime();

        String days = Long.toString((today-startDate)/(60*60*24*1000));

        TextView textView = (TextView) findViewById(R.id.num_days);
        textView.setText(days);

        TextView startDateTxtVw = (TextView) findViewById(R.id.startDate);
        SimpleDateFormat fmt = new SimpleDateFormat("d MMMM yyyy");
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(startDate);
        startDateTxtVw.setText(fmt.format(c.getTime()));

        Toolbar tb = (Toolbar) findViewById(R.id.my_toolbar);
        tb.setTitle("Days Sober");
        setSupportActionBar(tb);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.toolbar, menu);
            return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.set_date:

            Intent intent = new Intent(this, MainActivity.class);
            Bundle b = new Bundle();
            b.putBoolean("askDate", true);
            intent.putExtras(b);
            startActivity(intent);
            break;
        default:
            break;
        }

      return true;
    }
}
