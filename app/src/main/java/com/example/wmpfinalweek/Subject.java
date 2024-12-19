package com.example.wmpfinalweek;

public class Subject {
    private String name; // Name of the subject
    private int credits; // Number of credits for the subject

    // Default constructor required for Firebase
    public Subject() {
    }

    // Parameterized constructor to create a new Subject object
    public Subject(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }

    // Getter method for the subject name
    public String getName() {
        return name;
    }

    // Setter method for the subject name
    public void setName(String name) {
        this.name = name;
    }

    // Getter method for the subject credits
    public int getCredits() {
        return credits;
    }

    // Setter method for the subject credits
    public void setCredits(int credits) {
        this.credits = credits;
    }
}

