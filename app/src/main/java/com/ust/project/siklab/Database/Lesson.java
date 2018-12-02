package com.ust.project.siklab.Database;

public class Lesson {
    public static final String TABLE_NAME = "LESSON";
    public static final String COL_ID = "id";
    public static final String COL_LESSON_NUM = "lesson_num";
    public static final String COL_IS_LEARNED = "is_learned";
    public static final String COL_IS_QUIZ_TAKEN = "is_quiz_taken";
    public static final String COL_SCORE = "score";

    private int id;
    private int lesson_num;
    private int is_learned;
    private int is_quiz_taken;
    private int score;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COL_LESSON_NUM + " INTEGER,"
                    + COL_IS_LEARNED + " INTEGER,"
                    + COL_IS_QUIZ_TAKEN + " INTEGER,"
                    + COL_SCORE + " INTEGER)";

    public Lesson(int id, int lesson_num, int is_learned, int is_quiz_taken, int score) {
        this.id = id;
        this.lesson_num = lesson_num;
        this.is_learned = is_learned;
        this.is_quiz_taken = is_quiz_taken;
        this.score = score;
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

    public int getIs_learned() {
        return is_learned;
    }

    public void setIs_learned(int is_learned) {
        this.is_learned = is_learned;
    }

    public int getIs_quiz_taken() {
        return is_quiz_taken;
    }

    public void setIs_quiz_taken(int is_quiz_taken) {
        this.is_quiz_taken = is_quiz_taken;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
