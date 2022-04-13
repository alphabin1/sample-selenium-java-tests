package smoke.page;

import core.component.BaseElement;
import core.pageobject.BasePO;
import org.openqa.selenium.support.FindBy;

public class OrderSummaryPage extends BasePO {

    @FindBy(id = "#checkoutButton")
    BaseElement placeOrder;

    @FindBy(xpath = "//mat-card//table//tr[2]//td")
    BaseElement delivery;

    public BaseElement getDelivery() {
        return delivery;
    }

    public BaseElement getPlaceOrder() {
        return placeOrder;
    }

    public void clickToPlaceOrder() {
        getPlaceOrder().click();
    }

    public void isDeliveryDisplayed() {
        getDelivery().isDisplayed();
    }
}
