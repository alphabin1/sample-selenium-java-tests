package smoke.page;

import core.component.BaseElement;
import core.pageobject.BasePO;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends BasePO {

    @FindBy(xpath = "//mat-radio-button//label")
    BaseElement card;

    @FindBy(xpath = "//app-payment/mat-card/div/div[2]/button[2]/span[1]/mat-icon")
    BaseElement continueNext;

    public BaseElement getContinueNext() {
        return continueNext;
    }

    public void clickOnContinueNext() {
        getContinueNext().click();
    }

    public BaseElement getCard() {
        return card;
    }

    public void clickOnCard() {
        getCard().click();
    }

}
