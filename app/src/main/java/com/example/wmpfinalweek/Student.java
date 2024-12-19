package com.example.wmpfinalweek;

public class Student {
    private String name, email;
    private int totalCredits;

    public Student() {
        // Default constructor required for Firebase
    }

    public Student(String name, String email, int totalCredits) {
        this.name = name;
        this.email = email;
        this.totalCredits = totalCredits;
    }

    // Getters and setters...
}

