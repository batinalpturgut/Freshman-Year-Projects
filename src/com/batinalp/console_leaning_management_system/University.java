package com.batinalp.console_leaning_management_system;

import java.util.ArrayList;

public class University {
    private int totalStudent;
    private int totalFacultyMember;
    private String rectorName;
    private String universityName;
    private final int foundationYear;
    private final ArrayList<Department> departments;
    private String city;

    public University(String rectorName, String universityName, int foundationYear, String city) {
        this.rectorName = rectorName;
        this.universityName = universityName;
        this.foundationYear = foundationYear;
        this.city = city;
        departments = new ArrayList<>();
    }

    public String getRectorName() {
        return rectorName;
    }

    public void setRectorName(String rectorName) {
        this.rectorName = rectorName;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public int getFoundationYear() {
        return foundationYear;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void universityInfo() {
        System.out.println("University name: " + universityName);
        System.out.println("Rector name: " + rectorName);
        System.out.println("Foundation year: " + foundationYear);
        System.out.println("Departments: ");
        allDepartmentsInfo();
    }

    public void allDepartmentsInfo() {
        for (Department d : departments) {
            d.displayDepartmentInfo();
            System.out.println();
        }
    }

    public void addDepartmentUni(Department d) {
        departments.add(d);
    }

    public int getTotalStudent() {
        for (Department d : departments) {
            totalStudent += d.getStudents().size();
        }
        return totalStudent;
    }

    public int getTotalFacultyMember() {
        for (Department d : departments) {
            totalFacultyMember += d.getFacultyMembers().size();
        }
        return totalFacultyMember;
    }

    public void uniInfo() {
        System.out.println("University name: " + this.universityName + " \nFoundation Year: " + this.foundationYear
                + "\nRector's name: " + this.rectorName + "\nCity: " + this.city + "\n");
    }

    public void allUniInfo() {
        this.uniInfo();
        this.allDepartmentsInfo();

        System.out.println("Total students in university: " + this.getTotalStudent());
        System.out.println("Total faculty members in university: " + this.getTotalFacultyMember());
    }
}
