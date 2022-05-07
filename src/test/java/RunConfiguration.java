import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class RunConfiguration {

    static public final boolean RUN_ON_YANDEX = true;
    static public boolean noDriver = true;

    public RunConfiguration() {
        if (RUN_ON_YANDEX && noDriver) {
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Users\\Александр\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            WebDriver driver = new ChromeDriver(options);
            WebDriverRunner.setWebDriver(driver);
            noDriver = false;
        }
    }

}
