package com.ust.project.siklab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ust.project.siklab.Database.DatabaseHelper;
import com.ust.project.siklab.Lessons.ActivityLessonFive;
import com.ust.project.siklab.Lessons.ActivityLessonFour;
import com.ust.project.siklab.Lessons.ActivityLessonThree;
import com.ust.project.siklab.Lessons.ActivityLessonTwo;

public class ActivityAnswers extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);

        int correctAnswers[] = getIntent().getIntArrayExtra("ANSWERS");
        int optionResourceIDs[] = getIntent().getIntArrayExtra("OPTION_RESOURCE");
        int questionResourceID = getIntent().getIntExtra("QUESTION_RESOURCE", 0);
        final int lessonNumber = getIntent().getIntExtra("LESSON_NUMBER", 0);

        String lessonTitle = getResources().getStringArray(R.array.lesson_list)[(lessonNumber - 1)];
        String appBarTitle = "Quiz 1: " + lessonTitle;
        getSupportActionBar().setTitle(appBarTitle);

        TextView titleView = findViewById(R.id.title);
        TextView questionsView[] = {
          findViewById(R.id.question_1),
          findViewById(R.id.question_2),
          findViewById(R.id.question_3),
          findViewById(R.id.question_4),
          findViewById(R.id.question_5),
        };

        TextView correctView[] = {
          findViewById(R.id.answer_1),
          findViewById(R.id.answer_2),
          findViewById(R.id.answer_3),
          findViewById(R.id.answer_4),
          findViewById(R.id.answer_5),
        };

        TextView answerView[] = {
          findViewById(R.id.your_answer_1),
          findViewById(R.id.your_answer_2),
          findViewById(R.id.your_answer_3),
          findViewById(R.id.your_answer_4),
          findViewById(R.id.your_answer_5),
        };

        Button nextBtn = findViewById(R.id.next_btn);

        String questions[] = getResources().getStringArray(questionResourceID);

        titleView.setText("Quiz " + lessonNumber);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        for(int i = 0; i < questions.length; i++) {
            questionsView[i].setText((i + 1) + ". " + questions[i]);

            String options[] = getResources().getStringArray(optionResourceIDs[i]);

            String correctAnswer = options[(correctAnswers[i] - 1)];
            correctView[i].setText(correctAnswer);

            int answerIndex = db.getAnswer(lessonNumber, (i+1)).getAnswer();
            String answer = options[(answerIndex - 1)];
            answerView[i].setText("Your answer: " + answer);
        }

        if(lessonNumber == 5) {
            nextBtn.setText("Go To Lessons");
        }

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (lessonNumber) {
                    case 1 :
                        startActivity(new Intent(getApplicationContext(), ActivityLessonTwo.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();
                        break;

                    case 2 :
                        startActivity(new Intent(getApplicationContext(), ActivityLessonThree.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();
                        break;

                    case 3 :
                        startActivity(new Intent(getApplicationContext(), ActivityLessonFour.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();
                        break;

                    case 4 :
                        startActivity(new Intent(getApplicationContext(), ActivityLessonFive.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();
                        break;

                    case 5 :
                        startActivity(new Intent(getApplicationContext(), ActivityLessons.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();
                        break;
                }
            }
        });
    }
}
