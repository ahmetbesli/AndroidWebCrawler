package com.ahmetazizbesli.makler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AlarmReceiver extends BroadcastReceiver {
    static int x = 0;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"I'm running!",Toast.LENGTH_SHORT).show();
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 6; i++) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(x);
                    x++;
                    //document.evaluate('//*[@id="categories-5"]/ul/li[4]/ul/li[1]/a', document, null, 9, null).singleNodeValue.click();
                }
            });
        }
        executorService.shutdown();

    }
}
