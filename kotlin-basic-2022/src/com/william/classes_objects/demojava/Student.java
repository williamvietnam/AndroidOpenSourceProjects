package com.william.classes_objects.demojava;

public class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        System.out.println("This is my constructor in Java: ");
        this.id = id;
        this.name = name;
        System.out.println("ID: " + this.id + "\nName: " + name);
    }
}
