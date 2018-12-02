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

import java.util.ArrayList;

public class ActivityQuizzes extends AppCompatActivity {

    ListView listView;
    ArrayList<ModelLesson> modelLessons;
    private static AdapterQuiz adapterQuiz;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);

        databaseHelper = new DatabaseHelper(getApplicationContext());

        String lessons[] = getResources().getStringArray(R.array.lesson_list);
        modelLessons = new ArrayList<>();

        for(int i = 0; i < lessons.length; i++) {
            modelLessons.add(new ModelLesson(lessons[i], (i + 1)));
        }

        adapterQuiz = new AdapterQuiz(modelLessons, getApplicationContext());

        listView = (ListView) findViewById(R.id.quiz_list);
        listView.setAdapter(adapterQuiz);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int lessonNumber = position + 1;
                Intent intent = null;

                if (position != 0) {
                    boolean isLearned =  databaseHelper.getLesson(position).getIs_learned() == 1;

                    if(!isLearned) {
                        Toast.makeText(ActivityQuizzes.this, "You need to finish Lesson No. " + position + " first", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        switch (lessonNumber) {
                            case 2 :
//                                intent = new Intent(getApplicationContext(), ActivityLessonTwo.class);
                                break;

                            case 3 :
//                                intent = new Intent(getApplicationContext(), ActivityLessonThree.class);
                                break;

                            case 4 :
//                                intent = new Intent(getApplicationContext(), ActivityLessonFour.class);
                                break;

                            case 5 :
//                                intent = new Intent(getApplicationContext(), ActivityLessonFive.class);
                                break;
                        }

                        startActivity(intent);
                    }
                }
                else {
                    int answers[] = {3, 2, 1, 2, 3};
                    int optionsResourceIDs[] = {
                            R.array.lesson_one_quiz_one_options,
                            R.array.lesson_one_quiz_two_options,
                            R.array.lesson_one_quiz_three_options,
                            R.array.lesson_one_quiz_four_options,
                            R.array.lesson_one_quiz_five_options,
                    };
                    startActivity(
                            new Intent(getApplicationContext(), ActivityAnswers.class)
                                    .putExtra("ANSWERS", answers)
                                    .putExtra("LESSON_NUMBER", 1)
                                    .putExtra("QUESTION_RESOURCE", R.array.lesson_one_questions)
                                    .putExtra("OPTION_RESOURCE", optionsResourceIDs)
                    );
                }
            }
        });
    }
}
