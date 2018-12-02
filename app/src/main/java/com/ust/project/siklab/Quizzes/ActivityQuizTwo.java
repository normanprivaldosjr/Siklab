package com.ust.project.siklab.Quizzes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ust.project.siklab.Database.DatabaseHelper;
import com.ust.project.siklab.R;
import com.ust.project.siklab.Results.ActivityResultOne;

public class ActivityQuizTwo extends AppCompatActivity {

    // global variables
    int lessonNum;
    int quizNum;
    int selectedAnswer;

    int optionIDs[] = {
            R.id.quiz_option_1,
            R.id.quiz_option_2,
            R.id.quiz_option_3,
    };

    int buttonIDs[] = {
            R.id.option_btn_1,
            R.id.option_btn_2,
            R.id.option_btn_3,
    };

    int optionResources[] = {
            R.array.lesson_two_quiz_one_options,
            R.array.lesson_two_quiz_two_options,
            R.array.lesson_two_quiz_three_options,
            R.array.lesson_two_quiz_four_options,
            R.array.lesson_two_quiz_five_options,
    };

    Button nextBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_layout);

        lessonNum = getIntent().getIntExtra("LESSON_NUMBER" , 0);
        quizNum = getIntent().getIntExtra("QUIZ_NUMBER", 0);
        int questionsResourceID = getIntent().getIntExtra("QUIZ_QUESTION_RESOURCE", 0);
        int optionsResourceID = optionResources[(quizNum - 1)];

        String appBarTitle = "Quiz " + lessonNum + ": " + getResources().getStringArray(R.array.lesson_list)[(lessonNum - 1)];
        String quizIndicator = "Quiz " + lessonNum;
        String quizQuestion = quizNum + ". " + getResources().getStringArray(questionsResourceID)[(quizNum - 1)];
        String options[] = getResources().getStringArray(optionsResourceID);

        // Change appbar title and apply back button state
        getSupportActionBar().setTitle(appBarTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get all variables needed from the layout
        TextView quizIndicatorView = findViewById(R.id.quiz_indicator);
        TextView quizQuestionView = findViewById(R.id.quiz_question);
        final Button option1Btn = findViewById(R.id.option_btn_1);
        final Button option2Btn = findViewById(R.id.option_btn_2);
        final Button option3Btn = findViewById(R.id.option_btn_3);
        Button backBtn = findViewById(R.id.back_btn);
        nextBtn = findViewById(R.id.next_btn);

        // change quiz indicator text
        quizIndicatorView.setText(quizIndicator);

        // change quiz question text
        quizQuestionView.setText(quizQuestion);

        // change option text
        option1Btn.setText(options[0]);
        option2Btn.setText(options[1]);
        option3Btn.setText(options[2]);

        // hide next button when there's no answer yet
        nextBtn.setVisibility(View.INVISIBLE);

        // hide back button for first question
        if(quizNum == 1) {
            backBtn.setVisibility(View.INVISIBLE);
        }

        // change next button text for last question
        if(quizNum == 5) {
            nextBtn.setText("Finish");
        }

        // add click listeners
        option1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption(0, option1Btn);
            }
        });
        option2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption(1, option2Btn);
            }
        });
        option3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption(2, option3Btn);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());

                if(quizNum < 5) {
                    db.addAnswer(lessonNum, quizNum, selectedAnswer);
                    //copy of activity quiz one layout
                    Intent intent = new Intent(getApplicationContext(), ActivityQuizOne.class);
                    intent.putExtra("LESSON_NUMBER", lessonNum);
                    intent.putExtra("QUIZ_NUMBER", (quizNum + 1));
                    intent.putExtra("QUIZ_QUESTION_RESOURCE", R.array.lesson_one_questions);

                    startActivity(intent);
                }
                else {
                    db.addAnswer(lessonNum, quizNum, selectedAnswer);
                    db.updateLessonState(lessonNum, 1);

                    startActivity(new Intent(getApplicationContext(), ActivityResultOne.class));
                }
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

    private void selectOption(int index, Button activeBtn) {
        RadioButton radioButton = findViewById(optionIDs[index]);
        radioButton.setChecked(true);

        for (int i = 0; i < buttonIDs.length; i++) {
            Button button = findViewById(buttonIDs[i]);
            button.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_purpleroundedbutton));
        }

        activeBtn.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_redroundedbutton));
        nextBtn.setVisibility(View.VISIBLE);
        selectedAnswer = (index + 1);
    }
}
