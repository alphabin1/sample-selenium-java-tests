package smoke.page;

import core.component.BaseElement;
import core.pageobject.BasePO;
import org.openqa.selenium.support.FindBy;

public class DeliveryPage extends BasePO {

    @FindBy(xpath = "//mat-card//mat-table//mat-row[3]//mat-cell[1]")
    BaseElement standardDelivery;

    public BaseElement getStandardDelivery() {
        return standardDelivery;
    }

    public void clickToStandardDelivery() {
        getStandardDelivery().click();
    }

}
