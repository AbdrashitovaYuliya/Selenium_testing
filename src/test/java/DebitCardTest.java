import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.List;
import java.util.Objects;

public class DebitCardTest {
    private WebDriver driver;


    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "./driver/linux/chromedriver");

    }

    @BeforeEach
    void setUp() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }


    @Test
    void shouldDataPositive() throws InterruptedException {
        driver.get("http://0.0.0.0:9999");
        Thread.sleep(8000);
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        inputs.get(0).sendKeys("Иванов Василий");
        inputs.get(1).sendKeys("+79998888888");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.className("button__text")).click();
        Thread.sleep(8000);
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        List<WebElement> tagsP = driver.findElements(By.tagName("p"));
        boolean flag = false;
        for (WebElement tag : tagsP) {
            tag.getAttribute("data-test-id");
            if (Objects.equals(tag.getAttribute("data-test-id"), "order-success")) {
                flag = true;
                String actual = tag.getText().trim();
                Assertions.assertEquals(expected, actual);
            }

        }
        Assertions.assertTrue(flag);

    }

}
