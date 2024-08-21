package com.example.fullstack_study.category.domain;

public class Category {
    private Integer id;
    private int count;
    private String name;

    public Category() {
    }

    public Category(Integer id, int count, String name) {
        this.id = id;
        this.count = count;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
