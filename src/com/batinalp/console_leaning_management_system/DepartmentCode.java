package com.batinalp.console_leaning_management_system;

public enum DepartmentCode {
    CENG(1),
    ME(2),
    EEE(3),
    MED(4);

    private final int value;

    DepartmentCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static DepartmentCode fromValue(int value) {
        for (DepartmentCode department : DepartmentCode.values()) {
            if (department.value == value) {
                return department;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
