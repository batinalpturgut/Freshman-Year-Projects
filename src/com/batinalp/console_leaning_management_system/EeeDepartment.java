package com.batinalp.console_leaning_management_system;

//Electrical Electronics Engineering

public class EeeDepartment extends Department {

    public EeeDepartment(String deanName, DepartmentCode departmentCode, int departmentTotalStudent) {
        super(deanName, departmentCode, departmentTotalStudent);
    }

    @Override
    public void displayDepartmentInfo() {
        System.out.println("Electrical Engineering:\n");

        System.out.println("Students: \n");
        for (int i = 0; i < getStudents().size(); i++) {
            System.out.println("Student " + (i + 1) + "-\nName: " + getStudents().get(i).getName() +
                    "\nId: " + getStudents().get(i).getId() + "\nGPA: " + getStudents().get(i).getGpa());
            System.out.println();
        }

        System.out.println("Faculty members: ");
        for (int i = 0; i < getFacultyMembers().size(); i++) {
            System.out.println((i + 1) + "- Faculty Member: \n" + "name : " +
                    getFacultyMembers().get(i).getName() + "\nemail: " +
                    getFacultyMembers().get(i).getEmail() + "\nfield: " + getFacultyMembers().get(i).getField() + "\n\n");
        }

        System.out.println("Total student in department: " + getStudents().size());
        System.out.println("Total faculty member in department: " + getFacultyMembers().size());
    }

}
