package smoke.page;

import core.component.BaseElement;
import core.pageobject.BasePO;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePO {

    @FindBy(xpath = "//*[@id=\"mat-dialog-0\"]/app-welcome-banner/div/div[2]/button[2]/span[1]/span")
    BaseElement dismiss;

    @FindBy(xpath = "//button[contains(., \"OWASP Juice Shop\")]")
    BaseElement pageHeaderTitle;

    @FindBy(id = "navbarAccount")
    BaseElement headerAccountButton;

    @FindBy(id = "navbarLoginButton")
    BaseElement login;

    @FindBy(css = "mat-icon.mat-search_icon-search")
    BaseElement searchIcon;

    @FindBy(id = "mat-input-0")
    BaseElement searchInput;
    private String searchText;

    @FindBy(xpath = "//*[@id=\"mat-menu-panel-0\"]/div/button[1]")
    BaseElement userProfile;

    @FindBy(xpath = "//button[contains(@aria-label,'Add to Basket')]")
    BaseElement addToCart;

    @FindBy(xpath = "//button[@aria-label='Show the shopping cart']")
    BaseElement basket;

    public BaseElement getBasket() {
        return basket;
    }

    public void clickToBasket() {
        getBasket().click();
    }

    public BaseElement getAddToCart() {
        return addToCart;
    }

    public void clickToAddToCart() {
        getAddToCart().click();
    }

    public BaseElement getUserProfile() {
        return userProfile;
    }

    public void clickToUserProfile() {
        getUserProfile().click();
    }

    public BaseElement getSearchInput() {
        return searchInput;
    }

    public void setSearchInput(String s) {
        searchText = s;
    }

    public void fillSearchTextInput() {
        getSearchInput().sendKeys(searchText);
        getSearchInput().sendKeys(Keys.ENTER);
    }

    public BaseElement getSearchIcon() {
        return searchIcon;
    }

    public void clickOnSearchIcon() {
        getSearchIcon().click();
    }

    public BaseElement getDismiss() {
        return dismiss;
    }

    public void clickDismissButton() {
        getDismiss().click();
    }

    public BaseElement getPageHeaderTitle() {
        return pageHeaderTitle;
    }

    public BaseElement getHeaderAccountButton() {
        return headerAccountButton;
    }

    public void clickToAccountButton() {
        getHeaderAccountButton().click();
    }

    public BaseElement getLogin() {
        return login;
    }

    public void clickToLogin() {
        getLogin().click();
    }

    public boolean isHomePageDisplayed() {
        waitForHomePageToBeDisplayed();
        return getPageHeaderTitle().isDisplayed();
    }

    public void waitForHomePageToBeDisplayed() {
        waitUtils.waitForPageToLoad();
        waitUtils.waitForElementToBeVisible(getPageHeaderTitle());
    }
}
