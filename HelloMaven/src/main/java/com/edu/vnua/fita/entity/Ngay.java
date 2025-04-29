package com.edu.vnua.fita.entity;

import java.util.ArrayList;
import java.util.List;

public class Ngay {
    private List<MonHoc> dsMon = new ArrayList<>();

    public void addMonHoc(MonHoc monHoc) {
        dsMon.add(monHoc);
    }

    public List<MonHoc> getDsMon() {
        return dsMon;
    }
}