package com.ust.project.siklab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ust.project.siklab.Database.DatabaseHelper;

public class ActivityProgress extends AppCompatActivity {

    DatabaseHelper db;
    int scores[] = {0, 0, 0, 0, 0};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        db = new DatabaseHelper(getApplicationContext());

        int learnedLessons = countLearnedLessons();
        int totalScores = countTotalScores();

        TextView counterView = findViewById(R.id.counter);
        TextView totalScoreView = findViewById(R.id.total);
        TextView scoreViews[] = {
          findViewById(R.id.quiz_1_score),
          findViewById(R.id.quiz_2_score),
          findViewById(R.id.quiz_3_score),
          findViewById(R.id.quiz_4_score),
          findViewById(R.id.quiz_5_score),
        };

        counterView.setText("0" + learnedLessons);
        totalScoreView.setText((totalScores < 10 ? ("0" + totalScores) : Integer.toString(totalScores)) + "/25");

        for(int i = 0; i < scoreViews.length; i++) {
            scoreViews[i].setText("0" + scores[i] + "/05");
        }
    }

    private int countLearnedLessons() {
        int count = 0;
        int numberOfLessons = 5;

        for(int i = 0; i < numberOfLessons; i++) {
            count += db.getLesson((i + 1)).getIs_learned();
        }

        return count;
    }

    private int countTotalScores() {
        int total = 0;
        int numberOfLessons = 5;

        for(int i = 0; i < numberOfLessons; i++) {
            int score = db.getLesson((i + 1)).getScore();
            scores[i] = score;
            total += score;
        }

        return total;
    }
}
