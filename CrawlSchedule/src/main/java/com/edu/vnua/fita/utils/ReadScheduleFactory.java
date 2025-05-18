package com.edu.vnua.fita.utils;

public class ReadScheduleFactory {
    public static ReadScheduleObj createReadScheduleObj(String type) {
        if ("playwright".equalsIgnoreCase(type)) {
            return new PlaywrightScheduleObj();
        } else if ("selenium".equalsIgnoreCase(type)) {
            return new SeleniumScheduleObj();
        } else {
            throw new IllegalArgumentException("Unsupported schedule reader type: " + type);
        }
    }
}
