package com.example.android.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button,button1;
    SeekBar seekBar;
    MediaPlayer mediaPlayer;

    public void go(View view)
    {

        seekBar.setEnabled(false);
        new CountDownTimer(seekBar.getProgress()*1000,1000){
            @Override
            public void onTick(long l) {
               updateTimer((int)l/1000);
            }

            @Override
            public void onFinish() {
                mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.alarm);
                mediaPlayer.start();

            }
        }.start();

    }

    public void stop(View view){
        seekBar.setEnabled(true);
        textView.setText("00:00");
        seekBar.setProgress(0);
        mediaPlayer.stop();

    }

    public void updateTimer(int passedVariable){

        int minute = passedVariable/60;
        int second = passedVariable%60;

        String secondString;

        if(second<=9)
        {
            secondString = "0"+second;
            textView.setText(Integer.toString(minute)+":"+secondString);
        }
        else
        {
            textView.setText(Integer.toString(minute)+":"+Integer.toString(second));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);

        seekBar = findViewById(R.id.seekBar);

        seekBar.setMax(600);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               // Log.i("This",Integer.toString(i));
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
