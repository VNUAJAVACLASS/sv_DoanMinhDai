package com.edu.vnua.fita.entity;

import java.util.HashMap;
import java.util.Map;

public class Schedule {
    private Map<Integer, Week> weekMap = new HashMap();

    public void addWeek(Integer tuanThu, Week week) {
        weekMap.put(tuanThu, week);
    }

    public Map<Integer, Week> getWeekMap() {
        return weekMap;
    }

    public Week getWeek(Integer tuanThu) {
        return weekMap.get(tuanThu);
    }
}
