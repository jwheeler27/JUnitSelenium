import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class TestCheckboxes {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/checkboxes");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null)
            driver.quit();
    }

    @Test
    public void TestFindCheckBoxes (){
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//form[@id='checkboxes']/input"));
        Assertions.assertEquals(2, checkBoxes.size());

    }

    @Test
    public void TestCheckCheckBox1() {
        driver.navigate().refresh();
        WebElement checkBox1 = driver.findElement(By.xpath("//form[@id='checkboxes']/input"));
        checkBox1.click();
        Assertions.assertTrue(checkBox1.isSelected());
    }

    @Test
    public void TestUncheckCheckBox2() {
        driver.navigate().refresh();
        WebElement checkBox2 = driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]"));
        checkBox2.click();
        Assertions.assertFalse(checkBox2.isSelected());
    }

    @Test
    public void TestCheckUncheckBoxes() {
        driver.navigate().refresh();
        List<WebElement> checkBoxes = driver.findElements(By.xpath("//form[@id='checkboxes']/input"));
        WebElement checkBox1 = checkBoxes.get(0);
        WebElement checkbox2 = checkBoxes.get(1);

        checkBox1.click();
        checkbox2.click();

        Assertions.assertAll("Check that box 1 is check and box 2 i unchecked",
                () -> Assertions.assertTrue(checkBox1.isSelected()),
                () -> Assertions.assertFalse(checkbox2.isSelected())
        );

    }
}
