package com.ust.project.siklab.Lessons;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ust.project.siklab.ActivityTakeQuiz;
import com.ust.project.siklab.Database.DatabaseHelper;
import com.ust.project.siklab.R;

public class ActivityLessonThree extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_three);

        String lessonTitle = getResources().getStringArray(R.array.lesson_list)[2];
        getSupportActionBar().setTitle("Lesson 3: " + lessonTitle);

        TextView lessonTitleView = (TextView) findViewById(R.id.lesson_title);
        lessonTitleView.setText("Lesson 3: " + lessonTitle);

        Button completeBtn = (Button) findViewById(R.id.complete_btn);
        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                databaseHelper.updateLessonState(3, 1);

                Intent intent = new Intent(getApplicationContext(), ActivityTakeQuiz.class);
                intent.putExtra("LESSON_NUMBER", 3);
                startActivity(intent);
            }
        });
    }
}
