package com.wheeler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumDriver {

    static WebDriver driver;

    public static void setupDriver(String browser) throws Exception {
        if(browser.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
            driver = new ChromeDriver();
        }
    }
}
