package com.retro09.www.alarmmanager;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button button;
    TimePicker timePicker;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.set_alarm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                timePicker =(TimePicker)findViewById(R.id.time_picker);
                calendar.set(calendar.get(Calendar.HOUR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH), timePicker.getHour(),
                        timePicker.getMinute(),0);
                setAlarm(calendar.getTimeInMillis());
            }
        });
     }
    private void setAlarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent alrm = new Intent(this,Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,alrm,0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,timeInMillis, AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(this, "Alarm is set!", Toast.LENGTH_SHORT).show();
    }
}
