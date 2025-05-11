package com.edu.vnua.fita.entity;

import java.util.ArrayList;
import java.util.List;

public class Day {
    private List<Subject> subjectList = new ArrayList<>();

    public void addSubject(Subject subject) {
        subjectList.add(subject);
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }
}