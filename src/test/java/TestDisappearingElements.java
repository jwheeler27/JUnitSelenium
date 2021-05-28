import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.*;

import java.util.ArrayList;
import java.util.List;

public class TestDisappearingElements {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        driver = new HtmlUnitDriver();
        driver.get("https://the-internet.herokuapp.com/disappearing_elements");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null)
            driver.quit();
    }

    @Test
    public void TestFindElements(){
        List<WebElement> disappearingElements = new ArrayList<>();
        disappearingElements = driver.findElements(By.cssSelector("div.example a"));
        Assertions.assertEquals(5, disappearingElements.size());
    }

    @Test
    public void TestReloadTillFiveLinks() {
        driver.navigate().refresh();
        List<WebElement> disappearingElements = new ArrayList<>();
        disappearingElements = driver.findElements(By.cssSelector("div.example a"));
        int reloadNum = 0;

        while(disappearingElements.size() != 5) {
            driver.navigate().refresh();
            reloadNum++;
        }

        Assertions.assertTrue(disappearingElements.size() <= 5);


    }
}
