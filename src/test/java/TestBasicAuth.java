package com.wheeler

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBasicAuth {

    @Test
    public void testBasicAuthTitle() {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String baseURL = "https://the-internet.herokuapp.com/";
        driver.get(baseURL);

        String title = driver.getTitle();
        Assertions.assertEquals("The Internet", title);
        driver.close();

    }

    @Test
    public void basicAuthLogin(){
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String userPass = "admin:admin@";

        String baseURL = "https://the-internet.herokuapp.com/";
        driver.get(baseURL);

        WebElement basicAuthLink = driver.findElement(By.linkText("Basic Auth"));
        String authLink = basicAuthLink.getAttribute("href");

        String test = authLink.substring(0,8) + userPass + authLink.substring(8);
        driver.get(test);

        WebElement congrats = driver.findElement(By.cssSelector("p"));
        Assertions.assertTrue(congrats.getText().contains("Congratulations"));
        driver.close();
    }

}
