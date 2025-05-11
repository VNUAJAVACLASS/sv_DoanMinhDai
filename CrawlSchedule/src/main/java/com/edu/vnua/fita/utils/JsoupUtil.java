package com.edu.vnua.fita.utils;

import com.edu.vnua.fita.entity.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class JsoupUtil {
    public static Schedule parseSchedule(String html) {
        Schedule tkb = new Schedule();
        Subject lastSubject = null;

        Document doc = Jsoup.parse(html);

        Element table = doc.select("table").first();
        Elements rows = table.select("tr");

        for (Element row : rows) {
            Elements cols = row.select("td");
            if (!cols.isEmpty() && !cols.get(0).attr("rowspan").isEmpty()) {
                String maMonHoc = cols.get(0).text();
                String tenMonHoc = cols.get(1).text();
                String[] nhomTo = cols.get(2).text().split("-");

                int nhom = 0, to = 0;
                if (nhomTo.length == 2) {
                    nhom = Integer.parseInt(nhomTo[0]);
                    to = Integer.parseInt(nhomTo[1]);
                } else if (nhomTo.length == 1) {
                    nhom = Integer.parseInt(nhomTo[0]);
                    to = 0;
                }

                int soTinChi = Integer.parseInt(cols.get(3).text());
                int soTietHocLT = cols.get(4).text().isEmpty() ? 0 : Integer.parseInt(cols.get(4).text());
                int soTietHocTH = cols.get(5).text().isEmpty() ? 0 : Integer.parseInt(cols.get(5).text());
                String lopHoc = cols.get(6).text();
                int thu = cols.get(8).text().equals("CN") ? 8 : Integer.parseInt(cols.get(8).text());
                int tietBD = Integer.parseInt(cols.get(9).text());
                int soTiet = Integer.parseInt(cols.get(10).text());
                String phong = cols.get(11).text();
                String gv = cols.get(12).text();
                List<Integer> tuan = extractWeeks(cols.get(13).text());
                lastSubject = new Subject(maMonHoc, tenMonHoc, nhom, to, soTinChi, soTietHocLT, soTietHocTH, lopHoc);
                addMonHoc(tkb, lastSubject, thu, tietBD, soTiet, phong, gv, tuan);
            } else if (!cols.isEmpty()) {
                int thu = cols.get(0).text().equals("CN") ? 8 : Integer.parseInt(cols.get(0).text());
                int tietBD = Integer.parseInt(cols.get(1).text());
                int soTiet = Integer.parseInt(cols.get(2).text());
                String phong = cols.get(3).text();
                String gv = cols.get(4).text();
                List<Integer> tuan = extractWeeks(cols.get(5).text());
                addMonHoc(tkb, lastSubject, thu, tietBD, soTiet, phong, gv, tuan);
            }
        }
        return tkb;
    }

    private static void addMonHoc(Schedule tkb, Subject lastSubject, int thu, int tietBD, int soTiet, String phong, String gv, List<Integer> tuan) {
        for (Integer s : tuan) {
            Subject mh = new Subject(lastSubject);
            if (tkb.getWeekMap().containsKey(s)) {
                Week t = tkb.getWeekMap().get(s);
                Day n = t.getDay(thu);
                if (n == null) {
                    n = new Day();
                    t.addDay(thu, n);
                }
                mh.setTietBD(tietBD);
                mh.setSoTiet(soTiet);
                mh.setPhong(phong);
                mh.setGv(gv);
                n.addSubject(mh);
            } else {
                Week t = new Week();
                Day n = new Day();
                mh.setTietBD(tietBD);
                mh.setSoTiet(soTiet);
                mh.setPhong(phong);
                mh.setGv(gv);
                n.addSubject(mh);
                t.addDay(thu, n);
                tkb.addWeek(s, t);
            }
        }
    }

    public static List<Integer> extractWeeks(String text) {
        List<Integer> tuanThu = new ArrayList<>();
        int currentWeek = 1;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != '-') {
                tuanThu.add(currentWeek);
            }
            currentWeek++;
        }
        return tuanThu;
    }
}
