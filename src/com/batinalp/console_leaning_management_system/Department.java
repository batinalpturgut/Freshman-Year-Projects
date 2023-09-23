package com.batinalp.console_leaning_management_system;

import java.util.ArrayList;

public abstract class Department implements StudentServices, FacultyMemberServices {

    private String deanName;

    private DepartmentCode departmentCode;

    private int departmentMaxStudent;

    private final ArrayList<Student> students;

    private final ArrayList<FacultyMember> facultyMembers;

    // Constructor
    public Department(String deanName, DepartmentCode departmentCode, int departmentMaxStudent) {
        this.deanName = deanName;
        this.departmentCode = departmentCode;
        this.departmentMaxStudent = departmentMaxStudent;
        students = new ArrayList<>();
        facultyMembers = new ArrayList<>();
    }

    public abstract void displayDepartmentInfo();

    public String getDeanName() {
        return deanName;
    }

    public void setDeanName(String deanName) {
        this.deanName = deanName;
    }

    public DepartmentCode getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(DepartmentCode departmentCode) {
        this.departmentCode = departmentCode;
    }

    public int getDepartmentMaxStudent() {
        return departmentMaxStudent;
    }

    public void setDepartmentMaxStudent(int departmentMaxStudent) {
        this.departmentMaxStudent = departmentMaxStudent;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<FacultyMember> getFacultyMembers() {
        return facultyMembers;
    }

    @Override
    public void addStudent(Student s) {
        students.add(s);
    }

    @Override
    public void addFacultyMember(FacultyMember f) {
        facultyMembers.add(f);
    }

    @Override
    public void displayFacultyMemberInfo() {
        for (int i = 0; i < facultyMembers.size(); i++) {
            System.out.println((i + 1) + "- Faculty Member: \n" + "name : " + facultyMembers.get(i).getName() + "\nemail: " +
                    getFacultyMembers().get(i).getEmail() + "\nfield: " + getFacultyMembers().get(i).getField() + "\n\n");
        }
    }

    @Override
    public void displayStudentsInfo() {
        for (int i = 0; i < students.size(); i++) {
            System.out.println("Student " + (i + 1) + "-\nName: " + getStudents().get(i).getName() +
                    "\nId: " + getStudents().get(i).getId() + "\nGPA: " + getStudents().get(i).getGpa());
            System.out.println();
        }
    }

    public void examResults() {
        for (Student student : students) {
            student.examResults();
        }
    }
}
