package com.simplistic.anothervolumecontrol;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


public class MainActivity extends Activity implements OnItemClickListener {
    private ListView choose;
    RelativeLayout mainLayout;
    private AdView mAdView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mainLayout = findViewById(R.id.mainLayout);
        int[] androidColors = getResources().getIntArray(R.array.colours);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        mainLayout.setBackgroundColor(randomAndroidColor);

        choose = findViewById(R.id.chooseList);
        choose.setAdapter(new ArrayAdapter<>(this, R.layout.list_item, R.id.chooseText, new String[]{"Alarm", "Music", "Ringtone", "System", "Voice"}));
        choose.setOnItemClickListener(this);

        MobileAds.initialize(this, "ca-app-pub-7041676438054720~5326191699");

//        String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
//        String deviceId = md5(android_id).toUpperCase();

        mAdView = findViewById(R.id.adView);

        AdRequest adRequest =  new AdRequest.Builder()
        .build();
        mAdView.loadAd(adRequest);
        
    }

    public static final String md5(final String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0xFF & messageDigest[i]);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {

        }
        return "";
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                startActivity(new Intent(getApplicationContext(), AlarmVolume.class));
                return;
            case 1:
                startActivity(new Intent(getApplicationContext(), MusicVolume.class));
                return;
            case 2:
                startActivity(new Intent(getApplicationContext(), RingVolume.class));
                return;
            case 3:
                startActivity(new Intent(getApplicationContext(), SystemVolume.class));
                return;
            case 4:
                startActivity(new Intent(getApplicationContext(), VoiceVolume.class));
                return;
            default:
                return;
        }
    }

    protected void onStart() {
        super.onStart();

    }

    protected void onStop() {
        super.onStop();

    }

}
