package com.batinalp.console_leaning_management_system;

public class FacultyMember {
    private String name;
    private String email;
    private String field;
    private DepartmentCode departmentCode;

    public FacultyMember(String name, String email, String field, DepartmentCode departmentCode) {
        this.name = name;
        this.email = email;
        this.field = field;
        this.departmentCode = departmentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public DepartmentCode getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(DepartmentCode departmentCode) {
        this.departmentCode = departmentCode;
    }

}
