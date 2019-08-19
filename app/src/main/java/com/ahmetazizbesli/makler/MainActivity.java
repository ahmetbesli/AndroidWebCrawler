package com.ahmetazizbesli.makler;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static PendingIntent pendingIntent;
    static AlarmManager alarmManager;
    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.web);

        webView.getSettings().setJavaScriptEnabled(true);
        String newUA= "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.100 Safari/537.36";
        webView.getSettings().setUserAgentString(newUA);


        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                System.out.println("hello");
                view.evaluateJavascript("document.evaluate('//a[1]/text()', document, null, XPathResult.STRING_TYPE, null).stringValue", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        System.out.println(value);
                    }
                });
            }
        });

        webView.loadUrl("https://www.google.com");


        Intent alarmIntent = new Intent(this,AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,alarmIntent,0);
        startAlarm(getApplicationContext());

    }

    public void startAlarm(Context ctx){
        alarmManager = (AlarmManager)ctx.getSystemService(Context.ALARM_SERVICE);
        int interval = 60000;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),interval,pendingIntent);
        Toast.makeText(ctx,"Alarm Set",Toast.LENGTH_SHORT).show();

    }


}

