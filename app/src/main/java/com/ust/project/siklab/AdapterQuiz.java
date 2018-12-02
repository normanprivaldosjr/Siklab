package com.ust.project.siklab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterQuiz extends ArrayAdapter<ModelLesson> {

    private ArrayList<ModelLesson> dataSet;
    Context context;

    private static class ViewHolder {
        TextView lessonTitle;
        TextView lessonText;
    }

    public AdapterQuiz(ArrayList<ModelLesson> dataSet, Context context) {
        super(context, R.layout.layout_card_lesson, dataSet);
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ModelLesson modelLesson = getItem(position);
        AdapterQuiz.ViewHolder viewHolder;

        final View result;

        if(convertView == null) {
            viewHolder = new AdapterQuiz.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_card_lesson, parent, false);

            viewHolder.lessonTitle = (TextView) convertView.findViewById(R.id.lesson_title);
            viewHolder.lessonText = (TextView) convertView.findViewById(R.id.lesson_text);

            result = convertView;
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (AdapterQuiz.ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.lessonTitle.setText(modelLesson.getLessonTitle());
        viewHolder.lessonText.setText("QUIZ " + modelLesson.getLessonNumber());

        return convertView;
    }
}
