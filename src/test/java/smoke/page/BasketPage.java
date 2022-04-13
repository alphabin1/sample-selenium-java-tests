package smoke.page;

import core.component.BaseElement;
import core.pageobject.BasePO;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BasketPage extends BasePO {

    @FindBy(xpath = "//app-purchase-basket/h1")
    BaseElement basketHeader;

    @FindBy(xpath = "//mat-cell[contains(@class,'mat-column-remove')]/button")
    BaseElement remove;

    @FindBy(id = "checkoutButton")
    BaseElement checkOutButton;

    public BaseElement getCheckOutButton() {
        return checkOutButton;
    }

    public void clickToCheckOut() {
        getCheckOutButton().click();
    }

    public BaseElement getRemove() {
        return remove;
    }

    public void clickToRemove() {
        getRemove().click();
    }

    public BaseElement getBasketHeader() {
        return basketHeader;
    }

    public void isBasketDisplay() {
        getBasketHeader().isDisplayed();
    }
}
