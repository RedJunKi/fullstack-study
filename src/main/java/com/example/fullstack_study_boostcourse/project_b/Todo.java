package com.example.fullstack_study_boostcourse.project_b;

import java.sql.Timestamp;


public class Todo {
    private String title;
    private String name;
    private int sequence;
    private String type;
    private Timestamp dateTime;

    public Todo() {
    }

    public Todo(String title, String name, int sequence, String type, Timestamp dateTime) {
        this.title = title;
        this.name = name;
        this.sequence = sequence;
        this.type = type;
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public Todo(String title, String name, int sequence) {
        this.title = title;
        this.name = name;
        this.sequence = sequence;
    }
}
