package com.edu.vnua.fita.entity;

import java.util.HashMap;
import java.util.Map;

public class Tuan {
    private Map<Integer,Ngay> dsNgay = new HashMap<>();

    public void addNgay(Integer thu, Ngay ngay) {
        dsNgay.put(thu,ngay);
    }

    public Map<Integer,Ngay> getDsNgay() {
        return dsNgay;
    }

    public Ngay getNgay(Integer thu) {
        return dsNgay.get(thu);
    }
}