package smoke.page;

import core.component.BaseElement;
import core.pageobject.BasePO;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePO {

    @FindBy(xpath = "//*[@id=\"card\"]/div/h1")
    BaseElement profileHeader;

    @FindBy(id = "username")
    BaseElement userName;
    String userNameText;

    public void isProfilePageDisplayed() {
        getProfileHeader().isDisplayed();
    }

    public BaseElement getUsername() {
        return userName;
    }

    public void setUsername(String s) {
        userNameText = s;
    }

    public void fillUsername() {
        getUsername().sendKeys(userNameText);
    }

    public BaseElement getProfileHeader() {
        return profileHeader;
    }
}
