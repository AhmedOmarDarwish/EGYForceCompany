package com.example.a3mr.egyforcecompany;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.view.View;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ViewPager slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);

        slider = (ViewPager) findViewById(R.id.slider);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        slider.setAdapter(viewPagerAdapter);
        Timer timer=new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(),1000,2000);
    }

    public class MyTimerTask extends TimerTask {
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(slider.getCurrentItem()==0){
                        slider.setCurrentItem(1);
                    }else if(slider.getCurrentItem()==1) {
                        slider.setCurrentItem(2);
                    } else if(slider.getCurrentItem()==2){
                            slider.setCurrentItem(3);
                    } else if(slider.getCurrentItem()==3){
                        slider.setCurrentItem(4);
                    } else if(slider.getCurrentItem()==4){
                        slider.setCurrentItem(5);
                    }else {
                        slider.setCurrentItem(0);
                    }
                }
            });
        }
    }

    public void face(View view) {
        String ur = "https://www.facebook.com/profile.php?id=100006415325273";
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100006415325273"));
            startActivity(intent);
        } catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100006415325273")));
        }
    }

    public void call(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01229590598"));
        startActivity(intent);
    }

    public void whatsApp(View view) {

        String smsNumber = "01229590598";
        boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
        if (isWhatsappInstalled) {

            Intent sendIntent = new Intent("android.intent.action.MAIN");
            sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
            sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(smsNumber) + "@s.whatsapp.net");//phone number without "+" prefix

            startActivity(sendIntent);
        } else {
            Uri uri = Uri.parse("market://details?id=com.whatsapp");
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            Toast.makeText(this, "WhatsApp not Installed",
                    Toast.LENGTH_SHORT).show();
            startActivity(goToMarket);
        }
    }

    private boolean whatsappInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}

