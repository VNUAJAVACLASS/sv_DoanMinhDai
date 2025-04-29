package com.edu.vnua.fita.entity;

import java.util.HashMap;
import java.util.Map;

public class ThoiKhoaBieu {
    private Map<Integer,Tuan> dsTuan = new HashMap();

    public void addTuan(Integer tuanThu,Tuan tuan) {
        dsTuan.put(tuanThu, tuan);
    }

    public Map<Integer,Tuan> getDsTuan() {
        return dsTuan;
    }

    public Tuan getTuan(Integer tuanThu) {
        return dsTuan.get(tuanThu);
    }
}
