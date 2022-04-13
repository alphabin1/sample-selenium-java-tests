package core.pageobject;

import core.driver.WebDriverManager;
import core.util.WaitUtils;
import org.openqa.selenium.WebDriver;

/**
 * Base Page Object class, all other page object class must extend this class
 * 
 * @author prat3ik
 *
 */
public abstract class BasePO {
	protected WaitUtils waitUtils;

	public BasePO() {
		waitUtils = new WaitUtils();
		loadProperties();
		initElements();
	}

	private void initElements() {
		CustomPageFactory.initElements(getDriver(), this);
	}

	private void loadProperties() {

	}

	protected WebDriver getDriver() {
		return WebDriverManager.getThreadLocalDriver();
	}

}
