package core.driver;

import core.testcase.BaseTestCase;
import core.util.PropertyUtils;
import core.util.WaitUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.net.URL;
import java.util.logging.Logger;


/**
 * This is used to get browser specific WebDriver instance
 *
 * @author pratikpat
 */
public class WebDriverFactory {

    static final Logger LOGGER = Logger.getLogger(String.valueOf(BaseTestCase.class));
    static WaitUtils waitUtils = new WaitUtils();

    public enum BrowserType {
        FIREFOX, CHROME, SAFARI;
    }

    public static WebDriver getWebDriver() {
        WebDriver driver = null;
        final String remoteURL = PropertyUtils.getProperty("remote.url", "getProperty");
        final BrowserType browserType = BrowserType.valueOf(PropertyUtils.getProperty("automation.browser", "FIREFOX"));
      //  System.out.println(browserType);
        final String environmentType = PropertyUtils.getProperty("automation.execution.type", "local");
        ChromeOptions chromeOptions = new ChromeOptions();
       // FirefoxOptions firefoxOptions = new FirefoxOptions();
        SafariOptions safariOptions = new SafariOptions();
        System.setProperty("webdriver.gecko.driver","/Users/imac/Documents/Paths/geckodriver/geckodriver");
        // REMOTE EXECUTION
        if (environmentType.equals("remote")) {
            System.out.println();
            int tryCount = 0;
            int maxTryCount = 3;
            LOGGER.info("Inside of Remote Execution.");
            if (tryCount < maxTryCount) {
                try {
                    switch (browserType) {
                        case FIREFOX:
                            LOGGER.info("Remote:FF - Selected");
                            if (PropertyUtils.getProperty("automation.execution.mode").equals("headless")) {
                                LOGGER.info("Remote:Headless:FF - started!!!");
                                try {
                                   // driver = new RemoteWebDriver(new URL(remoteURL), firefoxOptions.setHeadless(true));
                                    LOGGER.info("Remote - Headless - FF - Driver Initialization Done.");
                                } catch (Exception e) {
                                    LOGGER.info("Remote - Headless - FF - Driver - Exception has occurred!");
                                    e.printStackTrace();
                                }
                                LOGGER.info("Remote:Headless:FF - ended!!!");
                            } else {
                                LOGGER.info("Remote:Normal:FF - started!!!");
                               // driver = new RemoteWebDriver(new URL(remoteURL), firefoxOptions);
                                LOGGER.info("Remote:Normal:FF - ended!!!");
                            }
                            driver.manage().window().setSize(new Dimension(1920, 1200));
                            break;
                        case CHROME:
                            LOGGER.info("Remote:Chrome - Selected");
                            chromeOptions.addArguments("--window-size=1920,1200");
                            if (PropertyUtils.getProperty("automation.execution.mode").equals("headless")) {
                                LOGGER.info("Remote:Headless:Chrome - started!!!");
                                chromeOptions.addArguments("--headless");
                                try {
                                    driver = new RemoteWebDriver(new URL(remoteURL), chromeOptions);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                LOGGER.info("Remote:Headless:Chrome - ended!!!");
                            } else {
                                LOGGER.info("Remote:Normal:Chrome - started!!!");
                                driver = new RemoteWebDriver(new URL(remoteURL), chromeOptions);
                                LOGGER.info("Remote:Normal:Chrome - ended!!!");
                            }
                            break;
                        case SAFARI:
                            LOGGER.info("Remote:Safari - Selected");
                            if (PropertyUtils.getProperty("automation.execution.mode").equals("normal")) {
                                LOGGER.info("Remote:Headless:Safari - started!!!");
                                try {
                                    driver = new RemoteWebDriver(new URL(remoteURL), safariOptions);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                LOGGER.info("Remote:Headless:Safari - ended!!!");
                            } else {
                                LOGGER.info("Remote:Normal:Safari - started!!!");
                                driver = new RemoteWebDriver(new URL(remoteURL), chromeOptions);
                                LOGGER.info("Remote:Normal:Safari - ended!!!");
                            }
                            break;
                    }
                } catch (Exception e) {
                    // Not able to retrieve Remote driver. Retrying
                    LOGGER.info("Exception has occurred, Retrying to create driver!");
                    tryCount++;
                    waitUtils.staticWait(3000);
                }
            }
        }

        // LOCAL EXECUTION
        else {
            switch (browserType) {
                case FIREFOX:
                    LOGGER.info("Local:FF - started!!!");
                    if (PropertyUtils.getProperty("automation.execution.mode").equals("headless")) {
                        LOGGER.info("Local:Headless:FF - started!!!");
                        try {
                            //driver = new FirefoxDriver(firefoxOptions.setHeadless(true));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        LOGGER.info("Local:Headless:FF - ended!!!");
                    } else {
                        LOGGER.info("Local:Normal:FF - started!!!");
                        driver = new FirefoxDriver();
                        LOGGER.info("Local:Normal:FF - ended!!!");
                    }
                    break;
                case CHROME:
                    LOGGER.info("Local:Chrome - started!!!");
					chromeOptions.addArguments("--window-size=1920,1200");
                    chromeOptions.addArguments("--no-sandbox");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    if (PropertyUtils.getProperty("automation.execution.mode").equals("headless")) {
                        LOGGER.info("Local:Headless:Chrome - started!!!");
                        chromeOptions.addArguments("--headless");
                        try {
                            driver = new ChromeDriver(chromeOptions);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        LOGGER.info("Local:Headless:Chrome - ended!!!");
                    } else {
                        LOGGER.info("Local:Normal:Chrome - started!!!");
                        driver = new ChromeDriver(chromeOptions);
                        LOGGER.info("Local:Normal:Chrome - ended!!!");
                    }
                    break;
                case SAFARI:
                    LOGGER.info("Local:FF - started!!!");
                    if (PropertyUtils.getProperty("automation.execution.mode").equals("headless")) {
                        LOGGER.info("Local:Headless:FF - started!!!");
                        try {
                            driver = new SafariDriver(safariOptions);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        LOGGER.info("Local:Headless:FF - ended!!!");
                    } else {
                        LOGGER.info("Local:Normal:FF - started!!!");
                        driver = new SafariDriver(safariOptions);
                        LOGGER.info("Local:Normal:FF - ended!!!");
                    }
                    break;
            }
        }
        return driver;
    }
}
