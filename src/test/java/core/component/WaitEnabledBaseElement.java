package core.component;

import core.util.WaitUtils;
import org.openqa.selenium.WebElement;


/**
 * This class would represent an element that need a wait statement after click
 * and sendKeys operations.
 *
 * @author pratikpat
 *
 */
public abstract class WaitEnabledBaseElement extends BaseElement {

	protected WaitUtils waitUtils;

	public WaitEnabledBaseElement(final String css) {
		super(css);
		this.waitUtils = new WaitUtils();
	}

	public WaitEnabledBaseElement(final WebElement baseElement) {
		super(baseElement);
		this.waitUtils = new WaitUtils();
	}

	@Override
	public void click() {
		this.waitUtils.staticWait(1000);
		super.click();
	}

	@Override
	public String getText(){
		return  super.getText();
	}

	@Override
	public void sendKeys(final CharSequence... text) {
		super.sendKeys(text);
	}

}
