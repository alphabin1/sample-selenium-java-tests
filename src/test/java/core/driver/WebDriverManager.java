package core.driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import java.util.logging.Logger;

public class WebDriverManager {
    public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    static final Logger LOGGER = Logger.getLogger(String.valueOf(WebDriverManager.class));

    public static void setThreadLocalWebDriver(final WebDriver driver) {
        webDriver.set(driver);
    }
    public static WebDriver getScreenshotableWebDriver() {
        final WebDriver augmentedDriver = new Augmenter().augment(WebDriverManager.getThreadLocalDriver());
        return augmentedDriver;
    }

    public static WebDriver getThreadLocalDriver() {
        WebDriver driver = webDriver.get();
        if (driver == null) {
            //throw new RuntimeException("Driver is not created for this Thread");
            createThreadLocalWebDriver();
        }
        return driver;
    }

    public static void createThreadLocalWebDriver () {
        //WebDriver driver = null;
        // public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
        WebDriver driver = null;
        try {
            driver = WebDriverFactory.getWebDriver();
            driver.manage().window().maximize();
            LOGGER.info("Driver successfully initialized.");
        } catch (Exception e) {
            LOGGER.info("Exception has occurred while assigning the driver object in manager class.");
            e.printStackTrace();

        }
        if (driver == null) {
            LOGGER.info("Driver is null.");
            throw new RuntimeException("Unable to retrieve driver. Grid might be down");

        }

        //setTimeOuts(driver);
        webDriver.set(driver);
        LOGGER.info("Test execution has started......");
    }
}