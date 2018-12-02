package com.ust.project.siklab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ust.project.siklab.Quizzes.ActivityQuizOne;

public class ActivityTakeQuiz extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz);

        final int lessonNum = (Integer) getIntent().getIntExtra("LESSON_NUMBER", 0);
        int index = lessonNum - 1;
        String lessonTitle = getResources().getStringArray(R.array.lesson_list)[index];

        TextView title = (TextView) findViewById(R.id.title);
        TextView subtitleGuide = (TextView) findViewById(R.id.subtitle_guide);
        TextView subtitleMain = (TextView) findViewById(R.id.subtitle_main);

        getSupportActionBar().setTitle("Quiz " + lessonNum + ": " + lessonTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title.setText("You are about to take Quiz " + lessonNum);
        subtitleGuide.setText("This quiz will focus on Lesson " + lessonNum + ":");
        subtitleMain.setText(lessonTitle);

        Button quizBtn = findViewById(R.id.quiz_btn);
        quizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                switch (lessonNum) {
                    case 1 :
                        intent = new Intent(getApplicationContext(), ActivityQuizOne.class);
                        intent.putExtra("LESSON_NUMBER", lessonNum);
                        intent.putExtra("QUIZ_NUMBER", 1);
                        intent.putExtra("QUIZ_QUESTION_RESOURCE", R.array.lesson_one_questions);
                        break;
                    case 2 :
                        intent = new Intent(getApplicationContext(), ActivityQuizOne.class);
                        intent.putExtra("LESSON_NUMBER", lessonNum);
                        intent.putExtra("QUIZ_NUMBER", 2);
                        intent.putExtra("QUIZ_QUESTION_RESOURCE", R.array.lesson_two_questions);
                        break;
                    case 3 :
                        intent = new Intent(getApplicationContext(), ActivityQuizOne.class);
                        intent.putExtra("LESSON_NUMBER", lessonNum);
                        intent.putExtra("QUIZ_NUMBER", 3);
                        intent.putExtra("QUIZ_QUESTION_RESOURCE", R.array.lesson_three_questions);
                        break;
                    case 4 :
                        intent = new Intent(getApplicationContext(), ActivityQuizOne.class);
                        intent.putExtra("LESSON_NUMBER", lessonNum);
                        intent.putExtra("QUIZ_NUMBER", 4);
                        intent.putExtra("QUIZ_QUESTION_RESOURCE", R.array.lesson_four_questions);
                        break;
                    case 5 :
                        intent = new Intent(getApplicationContext(), ActivityQuizOne.class);
                        intent.putExtra("LESSON_NUMBER", lessonNum);
                        intent.putExtra("QUIZ_NUMBER", 5);
                        intent.putExtra("QUIZ_QUESTION_RESOURCE", R.array.lesson_five_questions);
                        break;
                }
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        else
        {
            return super.onOptionsItemSelected(item);
        }
    }
}
