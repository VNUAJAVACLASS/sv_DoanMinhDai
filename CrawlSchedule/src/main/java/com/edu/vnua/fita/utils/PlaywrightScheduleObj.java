package com.edu.vnua.fita.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitForSelectorState;

public class PlaywrightScheduleObj implements ReadScheduleObj {
    private Playwright playwright;
    private Browser browser;
    private Page page;

    private String username;
    private String password;

    public PlaywrightScheduleObj() {
    	Scanner scanner = new Scanner(System.in);
		System.out.print("Nhập tên đăng nhập: ");
		String username = scanner.nextLine();
		System.out.print("Nhập mật khẩu: ");
		String password = scanner.nextLine();
			
		this.username = username;
		this.password = password;
        this.playwright = Playwright.create();
        this.browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        this.page = browser.newPage();
    }

	@Override
    public void readSchedule() {
        page.navigate("https://daotao.vnua.edu.vn/#/home");
        page.waitForSelector("button:has-text('Đăng nhập')");
        page.click("button:has-text('Đăng nhập')");

        page.waitForSelector("input[formcontrolname='username']");
        page.fill("input[formcontrolname='username']", username);
        page.fill("input[formcontrolname='password']", password);

        page.click("button:has-text('Đăng nhập')");

        page.waitForSelector("span.text-primary.text-justify");
        page.click("#WEB_TKB_HK");

        page.waitForSelector("table.table");
        page.waitForSelector("div[role='combobox']");
        page.click("div[role='combobox']");
        page.waitForSelector("div.ng-option", new Page.WaitForSelectorOptions().setTimeout(60000));
        page.click("div.ng-option:has-text('Học kỳ 2 - Năm học 2024 - 2025')");

        page.waitForTimeout(5000);

        String tkbHtml = (String) page.evaluate("() => document.querySelector('table.table').outerHTML");
        
        Path folderPath = Paths.get("src", "main", "resources");
        try {
            // Nếu thư mục chưa tồn tại, tạo thư mục
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }

            // Ghi file HTML
            Path htmlFile = folderPath.resolve("schedule.html");
            Files.write(htmlFile, tkbHtml.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Override
    public void readStartDate() {
        page.locator("//a[@id='WEB_TKB_1TUAN']").click();
        page.waitForSelector("//a[@id='WEB_TKB_1TUAN']");

        Locator weekDropdown = page.locator(
            "#fullScreen > div.card-body.p-0 > div.row.text-nowrap.px-1.pb-1 > div.d-inline-block.col-lg-7.col-md-12.col-sm-12.mb-1 > ng-select > div > div > div.ng-input"
        );
        weekDropdown.click();

        page.waitForSelector(".ng-dropdown-panel-items.scroll-host");

        page.evaluate("() => document.querySelector('.ng-dropdown-panel-items.scroll-host')?.scrollTo(0, 0)");
        
        page.waitForTimeout(1000);

        Locator firstOption = page.locator(
            "//div[@class='ng-dropdown-panel-items scroll-host']//div[contains(@class, 'ng-option')][1]"
        );

        firstOption.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

        String weekText = firstOption.textContent();
        if (weekText == null || !weekText.contains("từ ngày")) {
            throw new RuntimeException("Không tìm thấy thông tin tuần hợp lệ.");
        }

        Pattern pattern = Pattern.compile("từ ngày (\\d{2}/\\d{2}/\\d{4})");
        Matcher matcher = pattern.matcher(weekText);

        Path folderPath = Paths.get("src", "main", "resources");
        if (matcher.find()) {
            String dateString = matcher.group(1);
            try {
                // Tạo thư mục nếu chưa tồn tại
                if (!Files.exists(folderPath)) {
                    Files.createDirectories(folderPath);
                }

                // Nối folderPath với tên file
                Path filePath = folderPath.resolve("start_date.txt");

                Files.write(filePath, dateString.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new RuntimeException("Không trích xuất được ngày bắt đầu từ thông tin tuần.");
        }    
    }
}
