package com.batinalp.console_leaning_management_system;

import java.util.ArrayList;

public class Student {
    private String name;
    private DepartmentCode departmentCode;
    private int id;
    private double gpa;
    private final ArrayList<Exam> exams;

    public Student(String name, int id, DepartmentCode departmentCode) {
        this.name = name;
        this.id = id;
        this.departmentCode = departmentCode;
        exams = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public DepartmentCode getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(DepartmentCode departmentCode) {
        this.departmentCode = departmentCode;
    }

    public void examResults() {
        System.out.println(this.name + " student exam results: ");
        for (int i = 0; i < exams.size(); i++) {
            System.out.println((i + 1) + "- Exam " + exams.get(i).getName() + " made by " +
                    exams.get(i).getExamCreator().getName()
                    + "= " + exams.get(i).getGrade() +
                    " Letter note: " + exams.get(i).getLetterGrade() +
                    " added on " + exams.get(i).getDate());

        }
        System.out.println();
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }

    public void calculateGpa() {
        double sumGrade = 0;
        for (int i = 0; i < exams.size(); i++) {
            sumGrade += exams.get(i).getGrade();
            System.out.println();
        }

        double averageGrade = sumGrade / exams.size();

        gpa = averageGrade / 25d;
    }
}
