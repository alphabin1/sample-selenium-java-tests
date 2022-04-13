package smoke.page;

import core.component.BaseElement;
import core.pageobject.BasePO;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class SearchPage extends BasePO {

    @FindBy(xpath = "//*[@class=\"noResultText\" and contains(text(), \"No results found\")]")
    BaseElement noResultFound;

    public BaseElement getNoResultFound() {
        return noResultFound;
    }

    public void isProductAvailable() {
        Assert.assertFalse(getNoResultFound().isDisplayed(), "No Product Found");
    }
}
