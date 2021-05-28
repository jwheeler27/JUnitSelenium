import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.*;

public class TestDragAndDrop {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        driver = new HtmlUnitDriver();
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null)
            driver.quit();
    }


}
