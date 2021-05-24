import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.http.client.*;

import java.util.List;


public class TestBrokenImages {

    private static ChromeDriver driver;
    //private WebDriver driver;
    private int invalidImageCount;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/broken_images");
    }

    @Test
    public void validImageTest() {

        try {
            invalidImageCount = 0;
            List<WebElement> imagesList = driver.findElements(By.tagName("img"));
            System.out.println("Total no. of images are " + imagesList.size());
            for (WebElement imgElement : imagesList) {
                if (imgElement != null) {
                    verifyimageActive(imgElement);
                }
            }
            System.out.println("Total no. of invalid images are " + invalidImageCount);
            Assertions.assertAll("Number of Images and broken images",
                    () -> Assertions.assertEquals(imagesList.size(), 4),
                    () -> Assertions.assertEquals(invalidImageCount, 2)
            );
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }

    @AfterAll
    public static void tearDown() {
        if (driver != null)
            driver.quit();
    }

    public void verifyimageActive(WebElement imgElement) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(imgElement.getAttribute("src"));
            HttpResponse response = client.execute(request);
            // verifying response code he HttpStatus should be 200 if not,
            // increment as invalid images count
            if (response.getStatusLine().getStatusCode() != 200)
                invalidImageCount++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



