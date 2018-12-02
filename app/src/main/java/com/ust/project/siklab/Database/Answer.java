package com.ust.project.siklab.Database;

public class Answer {
    public static final String TABLE_NAME = "ANSWER";
    public static final String COL_ID = "id";
    public static final String COL_LESSON_NUM = "lesson_num";
    public static final String COL_ITEM_NUM = "item_num";
    public static final String COL_ANSWER = "answer";

    private int id;
    private int lesson_num;
    private int item_num;
    private int answer;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COL_LESSON_NUM + " INTEGER,"
                    + COL_ITEM_NUM + " INTEGER,"
                    + COL_ANSWER + " INTEGER)";

    public Answer(int id, int lesson_num, int item_num, int answer) {
        this.id = id;
        this.lesson_num = lesson_num;
        this.item_num = item_num;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLesson_num() {
        return lesson_num;
    }

    public void setLesson_num(int lesson_num) {
        this.lesson_num = lesson_num;
    }

    public int getItem_num() {
        return item_num;
    }

    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}