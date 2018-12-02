package com.ust.project.siklab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ust.project.siklab.Database.DatabaseHelper;
import com.ust.project.siklab.Lessons.ActivityLessonFive;
import com.ust.project.siklab.Lessons.ActivityLessonFour;
import com.ust.project.siklab.Lessons.ActivityLessonOne;
import com.ust.project.siklab.Lessons.ActivityLessonThree;
import com.ust.project.siklab.Lessons.ActivityLessonTwo;

import java.util.ArrayList;

public class ActivityLessons extends AppCompatActivity {

    ListView listView;
    ArrayList<ModelLesson> modelLessons;
    private static AdapterLesson adapterLesson;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        databaseHelper = new DatabaseHelper(getApplicationContext());

        String lessons[] = getResources().getStringArray(R.array.lesson_list);
        modelLessons = new ArrayList<>();

        for(int i = 0; i < lessons.length; i++) {
            modelLessons.add(new ModelLesson(lessons[i], (i + 1)));
        }

        adapterLesson = new AdapterLesson(modelLessons, getApplicationContext());

        listView = (ListView) findViewById(R.id.lesson_list);
        listView.setAdapter(adapterLesson);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int lessonNumber = position + 1;
                Intent intent = null;

                if (position != 0) {
                    boolean isLearned =  databaseHelper.getLesson(position).getIs_learned() == 1;

                    if(!isLearned) {
                        Toast.makeText(ActivityLessons.this, "You need to finish Lesson No. " + position + " first", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        switch (lessonNumber) {
                            case 2 :
                                intent = new Intent(getApplicationContext(), ActivityLessonTwo.class);
                                break;

                            case 3 :
                                intent = new Intent(getApplicationContext(), ActivityLessonThree.class);
                                break;

                            case 4 :
                                intent = new Intent(getApplicationContext(), ActivityLessonFour.class);
                                break;

                            case 5 :
                                intent = new Intent(getApplicationContext(), ActivityLessonFive.class);
                                break;
                        }

                        startActivity(intent);
                    }
                }
                else {
                    intent = new Intent(getApplicationContext(), ActivityLessonOne.class);
                    startActivity(intent);
                }
            }
        });
    }
}

