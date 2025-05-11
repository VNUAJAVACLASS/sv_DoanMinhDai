package com.edu.vnua.fita.entity;

import java.util.HashMap;
import java.util.Map;

public class Week {
    private Map<Integer, Day> dsNgay = new HashMap<>();

    public void addDay(Integer thu, Day day) {
        dsNgay.put(thu, day);
    }

    public Map<Integer, Day> getDayList() {
        return dsNgay;
    }

    public Day getDay(Integer thu) {
        return dsNgay.get(thu);
    }
}