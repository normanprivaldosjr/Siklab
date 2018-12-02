package com.ust.project.siklab;

public class ModelLesson {

    private String lessonTitle;
    private int lessonNumber;

    public ModelLesson(String lessonTitle, int lessonNumber) {
        this.lessonTitle = lessonTitle;
        this.lessonNumber = lessonNumber;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
        this.lessonNumber = lessonNumber;
    }
}