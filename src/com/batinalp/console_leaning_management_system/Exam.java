package com.batinalp.console_leaning_management_system;

public class Exam {
    private FacultyMember examCreator;
    private String name;
    private String date;
    private String subject;
    private double grade;
    private String letterGrade;
    private int numQuestion;
    private int correctAns;

    public Exam(FacultyMember examCreator, String name, String date, String subject, int numQuestion) {
        this.examCreator = examCreator;
        this.name = name;
        this.date = date;
        this.subject = subject;
        this.numQuestion = numQuestion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getNumQuestion() {
        return numQuestion;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setNumQuestion(int numQuestion) {
        this.numQuestion = numQuestion;
    }

    public void calculateGrade() {
        grade = (100d / numQuestion) * correctAns;
    }

    public String calculateLetterGrade() {
        int num = (int) (getGrade() / 10);

        if (num == 9 || num == 10) {
            letterGrade = "AA";
        } else if (num >= 8) {
            letterGrade = "BA";
        } else if (num == 7) {
            letterGrade = "BB";
        } else if (num == 6) {
            letterGrade = "CB";
        } else if (num == 5) {
            letterGrade = "CC";
        } else
            letterGrade = "FF";

        return letterGrade;
    }

    public FacultyMember getExamCreator() {
        return examCreator;
    }

    public void setExamCreator(FacultyMember examCreator) {
        this.examCreator = examCreator;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }
}
