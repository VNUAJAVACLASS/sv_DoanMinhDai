package com.edu.vnua.fita.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.Scanner;

public class PlaywrightUtil {
    public static String CrawlData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập tên đăng nhập: ");
        String username = scanner.nextLine();
        System.out.print("Nhập mật khẩu: ");
        String password = scanner.nextLine();

        String tkbHtml = "";
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(true)
        );
        Page page = browser.newPage();

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
        page.waitForSelector("div.ng-option");
        page.click("div.ng-option:has-text('Học kỳ 2 - Năm học 2024 - 2025')");

        page.waitForTimeout(5000);

        tkbHtml = (String) page.evaluate("document.querySelector('table.table').outerHTML");
        browser.close();

        return tkbHtml;
    }
}
