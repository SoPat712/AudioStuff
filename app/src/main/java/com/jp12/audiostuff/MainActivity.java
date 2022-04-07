package com.jp12.audiostuff;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Button pauseButton;
    Button playButton;
    Button startButton;
    Button stopButton;
    Button ff;
    int length;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pauseButton = findViewById(R.id.button);
        playButton = findViewById(R.id.button2);
        startButton = findViewById(R.id.button3);
        stopButton = findViewById(R.id.button4);
        ff = findViewById(R.id.button5);
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.ra);
        playButton.setOnClickListener(view -> {
            mediaPlayer.start();
            mediaPlayer.seekTo(length);
        });
        pauseButton.setOnClickListener(view -> {
            mediaPlayer.pause();
            length = mediaPlayer.getCurrentPosition();
        });
        startButton.setOnClickListener(view -> mediaPlayer.start());
        stopButton.setOnClickListener(view -> mediaPlayer.stop());
        ff.setOnClickListener(view -> {
            Log.d("TAG", "onCreate: "+ mediaPlayer.getCurrentPosition());
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5);
            Log.d("TAG", "onCreate: siasdfnmasdfin");
        });
        final ProgressBar mSeelBar = findViewById(R.id.progressBar);
        final int duration = mediaPlayer.getDuration();
        final int amountToUpdate = duration / 100;
        Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        if (!(amountToUpdate * mSeelBar.getProgress() >= duration)) {
                            int p = mSeelBar.getProgress();
                            p += 1;
                            mSeelBar.setProgress(p);
                        }
                    }
                });
            };
        }, amountToUpdate);
    }
}