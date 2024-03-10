package org.example.entity;

import java.io.Serializable;

public class Student implements Serializable {
    private long sid;
    private String name;

    public Student(long sid, String name) {
        this.sid = sid;
        this.name = name;
    }

    public Student() {
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                '}';
    }
}
