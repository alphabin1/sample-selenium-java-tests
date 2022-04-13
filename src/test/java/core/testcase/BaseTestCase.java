package core.testcase;

import core.driver.WebDriverManager;
import core.util.TestcaseListener;
import core.util.WaitUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

/**
 * This is a Base test case class. This would define global before and after
 * methods. These methods can be used to initialize/destroy objects (like web
 * driver, reporter, etc...) needed in all tests. All Test classes would be
 * extending this class
 *
 * @author prat3ik
 *
 */
@Listeners({ TestcaseListener.class })
public class BaseTestCase {
    final Logger LOGGER = Logger.getLogger(String.valueOf(BaseTestCase.class));
    protected WaitUtils waitUtils;


    @BeforeSuite
    public void beforeSuite() throws IOException {
        this.loadProperties();
        waitUtils = new WaitUtils();
        try{
            FileUtils.cleanDirectory(new File(System.getProperty("user.dir") + "/test-output/screenshots/"));
        }catch (Exception e) {
            new File(System.getProperty("user.dir") + "/test-output").mkdirs();
            new File(System.getProperty("user.dir") + "/test-output/screenshots").mkdirs();
        }
    }

    @BeforeClass
    public void beforeClass() {
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(final ITestContext context, Method m) throws InterruptedException {
        Thread.currentThread().setName(m.getName() + "_" + Thread.currentThread().getId());
        LOGGER.info("Thread:'" + Thread.currentThread().getName() + "' is executing");
        LOGGER.info("Execution Of Test Case: "+m.getName()+" has started!");
        WebDriverManager.createThreadLocalWebDriver();

	}

    @AfterMethod(alwaysRun = true)
    public void afterMethod(final ITestResult result) {
        String fileName = result.getTestClass().getName() + "_" + result.getName();
        String testResult = "Not executed well :(";
        if (result.getStatus() == ITestResult.SUCCESS) {
            testResult = "Passed.";
        } else if (result.getStatus() == ITestResult.FAILURE) {
            testResult = "Failed.";
        } else if (result.getStatus() == ITestResult.SKIP) {
            testResult = "Skipped.";
        }
        LOGGER.info("Test Case: " + fileName + " executed..! and it's: " + testResult);
        //quitWebDriver();
    }

    @AfterClass
    public void afterClass() {

    }

    @AfterSuite
    public void afterSuite() {
    }

    protected WebDriver getDriver() {
        return WebDriverManager.getThreadLocalDriver();
    }

    public void loadProperties() {
    }

    private void quitWebDriver() {
        try {
            this.getDriver().close();
            this.getDriver().quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebDriverManager.setThreadLocalWebDriver(null);
    }
}