package com.batinalp.console_leaning_management_system;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Management {
    private University university;
    private final Scanner scan;

    public Management() {
        scan = new Scanner(System.in);
        addUniversity();
    }

    public void addUniversity() {
        System.out.println("Enter the University's name: ");
        String universityName = scan.nextLine();

        System.out.println("Enter the Rector's name: ");
        String rectorName = scan.nextLine();

        int foundationYear = takeIntValue("Enter the foundation year", Integer.MAX_VALUE);

        System.out.println("Enter the city name: ");
        String cityName = scan.nextLine();

        university = new University(rectorName, universityName, foundationYear, cityName);
        System.out.println();

        systemStart();
    }

    public void systemStart() {
        while (true) {
            System.out.println();
            System.out.println("""
                    1 - Add Department.
                    2 - Add Student.
                    3 - Add Faculty Member.
                    4 - All Departments Info
                    5 - Remove Student.
                    6 - Remove Faculty Member
                    7 - Make Exam.
                    8 - Exam Results.
                    9 - All University Info.
                    10- Update Student.
                    11- Student Info.
                    12- Faculty Member Info.
                    13- Exit.
                    """);
            int selectedNum = takeIntValue("Choose what do you want ?", 13);

            switch (selectedNum) {
                case 1 -> addDepartment();
                case 2 -> addStudentManagement();
                case 3 -> addFacultyMemberManagement();
                case 4 -> university.allDepartmentsInfo();
                case 5 -> removeStudentManagement();
                case 6 -> removeFacultyMemberManagement();
                case 7 -> conductExam();
                case 8 -> examResultsManagement();
                case 9 -> university.allUniInfo();
                case 10 -> updateStudent();
                case 11 -> studentInfoManagement();
                case 12 -> facultyMemberInfoManagement();
                case 13 -> System.exit(0);
            }
        }
    }

    public int takeIntValue(String str, int range) {
        int number = 0;
        while (true) {
            try {
                System.out.println(str);
                number = scan.nextInt();
                if (number >= 1 && number <= range) {
                    scan.nextLine();
                    break;
                } else {
                    System.out.println("Invalid input. Input not in range.");
                    scan.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value.");
                scan.nextLine();
            }
        }
        return number;
    }

    public void addDepartment() {
        departmentNameList();

        int selectedNum = takeIntValue("Which department do you want to add ?", 4);

        System.out.println("Enter the dean name: ");
        String deanName = scan.nextLine();

        int totalStudent = takeIntValue("Enter the department max student: ", Integer.MAX_VALUE);

        switch (selectedNum) {
            case 1 -> university.addDepartmentUni(new CengDepartment(deanName, DepartmentCode.CENG, totalStudent));
            case 2 -> university.addDepartmentUni(new MeDepartment(deanName, DepartmentCode.ME, totalStudent));
            case 3 -> university.addDepartmentUni(new EeeDepartment(deanName, DepartmentCode.EEE, totalStudent));
            case 4 -> university.addDepartmentUni(new MedDepartment(deanName, DepartmentCode.MED, totalStudent));
        }
    }

    public void addStudentManagement() {
        departmentNameList();

        int selectedNum = takeIntValue("Which department do you want to add the student to?", 4);

        Department currentDepartment = findDepartment(selectedNum);

        if (currentDepartment != null) {

            if (currentDepartment.getDepartmentMaxStudent() > currentDepartment.getStudents().size()) {
                System.out.println("Enter the student name: ");
                String name = scan.nextLine();

                int id = takeIntValue("Enter the student id: ", Integer.MAX_VALUE);

                currentDepartment.addStudent(new Student(name, id, DepartmentCode.fromValue(selectedNum)));
            } else
                System.out.println("Maximum student capacity reached.");

        } else
            System.out.println("There is no such department.");
    }

    public void addFacultyMemberManagement() {
        departmentNameList();
        int selectedNum = takeIntValue("Which department do you want to add the faculty member to?", 4);

        Department currentDepartment = findDepartment(selectedNum);

        if (currentDepartment != null) {
            System.out.println("Enter the faculty member name: ");
            String name = scan.nextLine();

            System.out.println("Enter the faculty member email: ");
            String email = scan.nextLine();

            System.out.println("Enter the faculty member field: ");
            String field = scan.nextLine();

            currentDepartment.addFacultyMember(new FacultyMember(name, email, field,
                    DepartmentCode.fromValue(selectedNum)));
        } else
            System.out.println("There is no such department.");
    }

    public void removeFacultyMemberManagement() {
        departmentNameList();
        int selectedNum = takeIntValue("Which department do you want to remove the faculty member to?", 4);

        Department currentDepartment = findDepartment(selectedNum);

        if (currentDepartment != null && currentDepartment.getFacultyMembers().size() != 0) {

            currentDepartment.displayFacultyMemberInfo();

            int num = takeIntValue("Which faculty member do you want to remove?",
                    currentDepartment.getFacultyMembers().size()) - 1;

            System.out.println(currentDepartment.getFacultyMembers().get(num).getName() + " successfully removed.");
            currentDepartment.getFacultyMembers().remove(num);
        }
        System.out.println("There is no such department or there is no faculty member to remove.");
    }

    public void removeStudentManagement() {
        departmentNameList();
        int selectedNum = takeIntValue("Which department do you want to remove the student to?", 4);

        Department currentDepartment = findDepartment(selectedNum);

        if (currentDepartment != null && currentDepartment.getStudents().size() != 0) {
            currentDepartment.displayStudentsInfo();

            int num = takeIntValue("Which student do you want to remove? ",
                    currentDepartment.getStudents().size()) - 1;

            System.out.println(currentDepartment.getStudents().get(num).getName() + " successfully removed.");
            currentDepartment.getStudents().remove(num);
        } else
            System.out.println("There is no such department or there is no student to remove in department.");
    }

    public void conductExam() {
        departmentNameList();
        int selectedNum = takeIntValue("Which department do you want to exam?", 4);
        Department currentDepartment = findDepartment(selectedNum);

        if (currentDepartment != null && currentDepartment.getStudents().size() != 0
                && currentDepartment.getFacultyMembers().size() != 0) {

            ArrayList<Student> currentStudents = currentDepartment.getStudents();

            currentDepartment.displayFacultyMemberInfo();
            int num = takeIntValue("Which faculty member is conducting the exam?",
                    currentDepartment.getFacultyMembers().size()) - 1;

            System.out.println("Enter the exam name: ");
            String examName = scan.nextLine();

            System.out.println("Enter the date: ");
            String date = scan.nextLine();

            System.out.println("Enter the subject: ");
            String subject = scan.nextLine();

            int numQuestion = takeIntValue("Enter the number of questions for the exam: ", Integer.MAX_VALUE);

            System.out.println("The exam papers are being distributed to the students.");

            for (int i = 0; i < currentStudents.size(); i++) {
                currentStudents.get(i).getExams().add(
                        new Exam(currentDepartment.getFacultyMembers().get(num), examName, date, subject, numQuestion));
            }

            System.out.println("Results: ");
            for (int i = 0; i < currentStudents.size(); i++) {
                int correctCount =
                        takeIntValue(currentStudents.get(i).getName() +
                                " please enter the number of correctly answered questions in the exam: ", numQuestion);

                currentStudents.get(i).getExams().get(currentStudents.get(i).getExams().size() - 1)
                        .setCorrectAns(correctCount);

                currentStudents.get(i).getExams().get(currentStudents.get(i).getExams().size() - 1).calculateGrade();
                currentStudents.get(i).getExams().get(currentStudents.get(i).getExams().size() - 1).calculateLetterGrade();

                currentStudents.get(i).calculateGpa();

            }
        } else
            System.out.println("It's not possible.");
    }

    public void examResultsManagement() {
        departmentNameList();
        int selectedNum = takeIntValue("Which department do you want to show exam results?", 4);
        Department currentDepartment = findDepartment(selectedNum);

        if (currentDepartment != null && currentDepartment.getStudents().size() != 0 && currentDepartment.getStudents().
                get(0).getExams().size() != 0) {

            currentDepartment.examResults();

        } else
            System.out.println("It's not possible.");

    }

    public void updateStudent() {
        departmentNameList();
        int selectedNum = takeIntValue("Which department do you want to update students ?", 4);
        Department currentDepartment = findDepartment(selectedNum);

        if (currentDepartment != null) {

            if (currentDepartment.getStudents().size() != 0) {

                currentDepartment.displayStudentsInfo();
                int selectedStudent = takeIntValue("Which student do you want to update ?",
                        currentDepartment.getStudents().size()) - 1;

                System.out.println("1- Name \n2- Id");

                int selectedUpdate = takeIntValue("Choose what do you want to update?", 2);

                if (selectedUpdate == 1) {
                    System.out.println("Please enter the name: ");
                    currentDepartment.getStudents().get(selectedStudent).setName(scan.nextLine());
                } else {
                    currentDepartment.getStudents().get(selectedStudent).setId(takeIntValue("Please enter the id: ",
                            Integer.MAX_VALUE));
                }


            } else
                System.out.println("There is no student in this department.");

        } else
            System.out.println("There is no such department or there is no student to remove in department.");


    }


    public void studentInfoManagement() {
        departmentNameList();
        int selectedNum = takeIntValue("Which department do you want to show students info?", 4);
        Department currentDepartment = findDepartment(selectedNum);

        if (currentDepartment != null) {

            if (currentDepartment.getStudents().size() != 0) {

                currentDepartment.displayStudentsInfo();

            } else
                System.out.println("There is no student.");

        } else
            System.out.println("There is no such department.");
    }

    public void facultyMemberInfoManagement() {
        departmentNameList();
        int selectedNum = takeIntValue("Which department do you want to show faculty members info ?", 4);
        Department currentDepartment = findDepartment(selectedNum);

        if (currentDepartment != null) {

            if (currentDepartment.getFacultyMembers().size() != 0) {

                currentDepartment.displayFacultyMemberInfo();

            } else
                System.out.println("There is no faculty member.");

        } else
            System.out.println("There is no such department.");
    }


    public Department findDepartment(int selectedNum) {
        for (int i = 0; i < university.getDepartments().size(); i++) {
            if (DepartmentCode.fromValue(selectedNum) == university.getDepartments().get(i).getDepartmentCode()) {
                return university.getDepartments().get(i);
            }
        }
        return null;
    }

    public void departmentNameList() {
        System.out.println("""
                1- Computer Engineering
                2- Mechanical Engineering
                3- Electrical Engineering
                4- Medicine
                """);
    }
}
