package com.example.fullstack_study_boostcourse.project_b;

public class TodoDto {
    private String title;
    private String name;
    private int sequence;

    public TodoDto(String title, String name, int sequence) {
        this.title = title;
        this.name = name;
        this.sequence = sequence;
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
}
