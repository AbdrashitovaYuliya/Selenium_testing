import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DebitCardTest {
    private WebDriver driver;


    @BeforeAll
    public static void setUpAll() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
  public void beforeEach() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");

    }

    @AfterEach
    public void afterEach() {
        driver.quit();
        driver = null;
    }


    @Test
    void shouldDataPositive() throws InterruptedException {
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Василий");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79998888888");
        driver.findElement(By.cssSelector("[data-test-id=agreement")).click();
        driver.findElement(By.cssSelector("button.button")).click();

        var actualText = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
        Assertions.assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText);

    }

}