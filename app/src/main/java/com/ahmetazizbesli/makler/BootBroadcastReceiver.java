package com.ahmetazizbesli.makler;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static com.ahmetazizbesli.makler.MainActivity.alarmManager;
import static com.ahmetazizbesli.makler.MainActivity.pendingIntent;

public class BootBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent alarmIntent = new Intent(context,AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(context,0,alarmIntent,0);
        startAlarm(context);
    }

    public void startAlarm(Context ctx){
        alarmManager = (AlarmManager)ctx.getSystemService(Context.ALARM_SERVICE);
        int interval = 60000;

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),interval,pendingIntent);
        Toast.makeText(ctx,"Alarm Set",Toast.LENGTH_SHORT).show();

    }
}
