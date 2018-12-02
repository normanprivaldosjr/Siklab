package com.ust.project.siklab.Results;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ust.project.siklab.ActivityAnswers;
import com.ust.project.siklab.ActivityLessons;
import com.ust.project.siklab.Database.DatabaseHelper;
import com.ust.project.siklab.Lessons.ActivityLessonTwo;
import com.ust.project.siklab.R;

public class ActivityResultOne extends AppCompatActivity {

    // answers
    private int answers[] = {3, 2, 1, 2, 3};
    private int optionsResourceIDs[] = {
      R.array.lesson_one_quiz_one_options,
      R.array.lesson_one_quiz_two_options,
      R.array.lesson_one_quiz_three_options,
      R.array.lesson_one_quiz_four_options,
      R.array.lesson_one_quiz_five_options,
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_layout);

        // count total correct answers
        int correctAnswers = checkAnswers();

        // update database score
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        db.updateLessonScore(1, correctAnswers);

        // change appbar title
        String lessonTitle = getResources().getStringArray(R.array.lesson_list)[0];
        String appBarTitle = "Quiz 1: " + lessonTitle;
        getSupportActionBar().setTitle(appBarTitle);

        // get all the variables needed from the layout
        TextView counterView = findViewById(R.id.counter);
        Button answersBtn = findViewById(R.id.answer_btn);
        Button nextLessonBtn = findViewById(R.id.next_btn);

        // change counter text
        counterView.setText("0" + Integer.toString(correctAnswers));

        // set onclick listeners
        answersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to answers page
                startActivity(
                    new Intent(getApplicationContext(), ActivityAnswers.class)
                        .putExtra("ANSWERS", answers)
                        .putExtra("LESSON_NUMBER", 1)
                        .putExtra("QUESTION_RESOURCE", R.array.lesson_one_questions)
                        .putExtra("OPTION_RESOURCE", optionsResourceIDs)
                );
            }
        });
        nextLessonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityLessonTwo.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), ActivityLessons.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private int checkAnswers() {
        int lessonNumber = 1;
        int totalNumberOfQuestions = 5;
        int correct = 0;

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        for(int i = 0; i < totalNumberOfQuestions; i++) {
            int quizNum = i + 1;

            int answer = db.getAnswer(lessonNumber, quizNum).getAnswer();

            if(answer == answers[i]) {
                correct++;
            }
        }

        return correct;
    }
}
