package com.batinalp.console_leaning_management_system;
// Mechanical Engineering

public class MeDepartment extends Department {

    public MeDepartment(String deanName, DepartmentCode departmentCode, int departmentTotalStudent) {
        super(deanName, departmentCode, departmentTotalStudent);
    }

    @Override
    public void displayDepartmentInfo() {
        System.out.println("Mechanical Engineering department.\n");

        System.out.println("Students: \n");
        for (int i = 0; i < getStudents().size(); i++) {
            System.out.println("Student " + (i + 1) + "-\nName: " + getStudents().get(i).getName() +
                    "\nId: " + getStudents().get(i).getId() + "\nGPA: " + getStudents().get(i).getGpa());
            System.out.println();
        }

        System.out.println("Faculty members: ");
        for (int i = 0; i < getFacultyMembers().size(); i++) {
            System.out.println((i + 1) + "- Faculty Member: \n" + "Name : " +
                    getFacultyMembers().get(i).getName() + "\nEmail: " +
                    getFacultyMembers().get(i).getEmail() + "\nField: " + getFacultyMembers().get(i).getField() + "\n\n");
        }

        System.out.println("Total student in department: " + getStudents().size());
        System.out.println("Total faculty member in department: " + getFacultyMembers().size());
    }

}
