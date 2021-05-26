import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.*;

public class TestContextMenu {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/context_menu");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null)
            driver.quit();
    }

    @Test
    @DisplayName("Verify context menu area exists")
    public void TestFindContextMenu() {
        WebElement contextMenu = driver.findElement(By.xpath("//div[@id='hot-spot']"));
        Assertions.assertNotNull(contextMenu);
    }

    @Test
    @DisplayName("Right click context menu")
    public void TestRightClickContextMenu() {

        WebElement contextMenu = driver.findElement(By.xpath("//div[@id='hot-spot']"));

        try {
            Actions actions = new Actions(driver);
            actions.contextClick(contextMenu).perform();
            driver.switchTo().alert().accept();
        }
        //If everything goes well, test passes
        //otherwise, catch exception and fail the test
        catch (NoAlertPresentException nape) {
            Assertions.assertNull(nape);
        }
    }

}