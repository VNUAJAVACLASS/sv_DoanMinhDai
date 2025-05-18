package com.edu.vnua.fita.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateHelper {
    private static LocalDate startDate;
   
    // Constructor đọc startDate từ file
    public DateHelper() throws IOException {
        DateHelper.startDate = readDateFromFile("src/main/resources/start_date.txt");
    }

    // Hàm đọc LocalDate từ file (file chứa 1 dòng ngày theo dd/MM/yyyy)
    private LocalDate readDateFromFile(String filePath) throws IOException {
        String dateStr = new String(Files.readAllBytes(Paths.get(filePath))).trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateStr, formatter);
    }

	public static int getWeekFromDate(LocalDate date) {
        long daysBetween = ChronoUnit.DAYS.between(startDate, date);
        return (int)(daysBetween / 7) + 1;
    }

    public static int getThuFromDate(LocalDate date) {
        int thu = date.getDayOfWeek().getValue();
        return thu + 1;
    }
    
    public LocalDate getStartDate() {
    	return startDate;
    }
}
