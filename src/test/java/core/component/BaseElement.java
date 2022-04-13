package core.component;

import core.driver.WebDriverManager;
import core.pageobject.IgnoreInit;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

/**
 * This is base element extended from WebElement. This is wrapper around
 * WebElement. All user defined components would extend this class. This class
 * would contain all common methods applicable to all elements
 * 
 * @author prat3ik
 *
 */
public class BaseElement implements WebElement, WrapsElement {
	@IgnoreInit
	protected WebElement el;
	protected String fieldName = "";
	protected Integer explicitWait;

	public BaseElement(final String css) {
		this.el = this.getDriver().findElement(By.cssSelector(css));
	}

	public BaseElement(WebElement el) {
		this.el = el;
	}


	protected WebDriver getDriver() {
		return WebDriverManager.getThreadLocalDriver();
	}

	@Override
	public void clear() {
		this.el.clear();
	}

	@Override
	public void click() {
		this.scrollIntoView();
		this.el.click();
	}
	protected Actions getActionsDriver() {
		return new Actions(this.getDriver());
	}
	public void doubleClick() {
		this.getActionsDriver().doubleClick(this.el).build().perform();
	}

	@Override
	public WebElement findElement(final By arg0) {
		return this.el.findElement(arg0);

	}

	@Override
	public List<WebElement> findElements(final By arg0) {
		return this.el.findElements(arg0);
	}

	@Override
	public String getAttribute(final String arg0) {
		return this.el.getAttribute(arg0);
	}

	@Override
	public String getCssValue(final String arg0) {
		return this.el.getCssValue(arg0);
	}

	@Override
	public Point getLocation() {
		return this.el.getLocation();
	}

	@Override
	public Dimension getSize() {
		return this.el.getSize();
	}

	@Override
	public String getTagName() {
		return this.el.getTagName();
	}

	@Override
	public String getText() {
		return this.el.getText();
	}

	@Override
	public WebElement getWrappedElement() {
		return this.el;
	}

	@Override
	public boolean isDisplayed() {
		return this.isElementPresent() && this.el.isDisplayed();
	}

	public boolean isElementPresent() {
		boolean isPresent = false;
		try {
			this.el.isDisplayed();
			isPresent = true;
		} catch (final Exception e) {
			isPresent = false;
		}
		return isPresent;
	}

	@Override
	public boolean isEnabled() {
		return this.el.isEnabled();
	}

	@Override
	public boolean isSelected() {
		return this.el.isSelected();
	}

	public void scrollIntoView() {
		((JavascriptExecutor) this.getDriver()).executeScript("arguments[0].scrollIntoView(false);", this.el);
	}

	@Override
	public void sendKeys(final CharSequence... arg0) {
		this.el.clear();
		this.el.sendKeys(arg0);
	}

	@Override
	public void submit() {
		this.el.submit();
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> arg0) throws WebDriverException {
		return this.el.getScreenshotAs(arg0);
	}

	@Override
	public Rectangle getRect() {
		return this.el.getRect();
	}

	public void setExplicitWait(Integer explicitWait) {
		this.explicitWait = explicitWait;
	}

	public void setElementName(String elementName) {
		this.fieldName = elementName;
	}
}
