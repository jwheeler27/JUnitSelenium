import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class TestAllLinks {

    private static WebDriver driver;
    private static  boolean broken = false;

    @BeforeAll
    public static void setUp() {
        driver = new HtmlUnitDriver();
        driver.get("https://the-internet.herokuapp.com/");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null)
            driver.quit();
    }

    @Test
    @DisplayName("Test for broken links")
    public void TestForBrokenLinks(){
        List<WebElement> links = new ArrayList<>();
        links = driver.findElements(By.tagName("a"));

        for (WebElement i: links) {
            verifyLink(i);
            //System.out.println(i.getAttribute("href"));
        }

        Assertions.assertFalse(broken);


    }

    //Verify link gives us a 200 status or a 401 bad auth status
    public void verifyLink(WebElement linkElement) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(linkElement.getAttribute("href"));
            HttpResponse response = client.execute(request);
            // verifying response code he HttpStatus should be 200 or 401
            // if not, set broken to true
            if (response.getStatusLine().getStatusCode() != 200 &&
                    response.getStatusLine().getStatusCode() != 401 ) {
                broken = true;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
