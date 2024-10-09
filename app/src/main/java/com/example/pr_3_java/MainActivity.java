package com.example.pr_3_java;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int score = 0;
    private TextView scoreText;
    private ImageButton hamsterButton;
    private SoundPool soundPool;
    private int soundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText = findViewById(R.id.scoreText);
        hamsterButton = findViewById(R.id.Img);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(1)
                .setAudioAttributes(audioAttributes)
                .build();

        soundID = soundPool.load(this, R.raw.sound, 1);

        final Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.hunster_click);

        hamsterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hamsterButton.startAnimation(scaleAnimation);

                soundPool.play(soundID, 1, 1, 0, 0, 1);

                score++;
                updateScore();
            }
        });
    }

    private void updateScore() {
        scoreText.setText("Очки: " + score);
    }
}
