package com.simplistic.anothervolumecontrol;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


import java.util.Random;


public class SystemVolume extends Activity {
    private AudioManager mgr;
    private TextView currentVolText;
    private SeekBar voice;
    private int currentVol;
    RelativeLayout mainLayout;

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system);

        mainLayout = findViewById(R.id.mainLayout);
        int[] androidColors = getResources().getIntArray(R.array.colours);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        mainLayout.setBackgroundColor(randomAndroidColor);

        setVolumeControlStream(AudioManager.STREAM_SYSTEM);
        voice = findViewById(R.id.voice);
        currentVolText = findViewById(R.id.volText);

        initBar();
    }

    protected void onStart() {
        super.onStart();

    }

    protected void onStop() {
        super.onStop();

    }

    private void initBar() {
        try
        {
            voice = findViewById(R.id.voice);
            mgr = (AudioManager) getSystemService(AUDIO_SERVICE);
            voice.setMax(mgr
                    .getStreamMaxVolume(AudioManager.STREAM_SYSTEM));
            voice.setProgress(mgr
                    .getStreamVolume(AudioManager.STREAM_SYSTEM));

            currentVolText.setText("Volume: " + mgr
                    .getStreamVolume(AudioManager.STREAM_SYSTEM));

            voice.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
            {
                @Override
                public void onStopTrackingTouch(SeekBar arg0)
                {
                    currentVol = mgr.getStreamVolume(AudioManager.STREAM_SYSTEM);
                    if(currentVol >= mgr.getStreamMaxVolume(AudioManager.STREAM_SYSTEM)){
                        currentVolText.setTextSize(70);
                        currentVolText.setText("Volume: Max");
                    }else {
                        currentVolText.setText("Volume: " + currentVol);
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0)
                {
                }

                @SuppressLint("SetTextI18n")
                @Override
                public void onProgressChanged(SeekBar arg0, int progress, boolean arg2)
                {
                    mgr.setStreamVolume(AudioManager.STREAM_SYSTEM,
                            progress, 0);
                    currentVolText.setText("Volume: " + progress);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
