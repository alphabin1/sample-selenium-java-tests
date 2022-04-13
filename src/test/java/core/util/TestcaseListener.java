package core.util;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * TestNG test case listener
 * 
 * @author prat3ik
 *
 */
public class TestcaseListener extends TestListenerAdapter {

	private ScreenshotUtils screenShot;

	public TestcaseListener() {
		screenShot = new ScreenshotUtils();
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		screenShot.takeScreenShot(tr);
	}

}
