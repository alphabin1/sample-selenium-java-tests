package core.util;

import core.driver.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This will contain screen short related methods, which can captures the screen
 * shot on particular event
 * 
 * @author prat3ik
 *
 */
public class ScreenshotUtils {
	public void takeScreenShot(final ITestResult tr) {
		final String methodName = tr.getName();

		final WebDriver augmentedDriver = WebDriverManager.getScreenshotableWebDriver();
		final File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");

		String path = System.getProperty("user.dir") + "/test-output/screenshots/" + methodName + "_"
				+ dateFormat.format(new Date()) + ".png";
		try {
			FileUtils.copyFile(source, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
