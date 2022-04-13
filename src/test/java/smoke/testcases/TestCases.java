package smoke.testcases;

import org.testng.annotations.Test;
import smoke.page.*;

import java.util.concurrent.TimeUnit;

public class TestCases extends BaseTestCases {

    @Test
    public void verifyUserCanSignUp() {
        SignUpPage signUpPage = new SignUpPage();
        HomePage homePage = new HomePage();
        navigateToHomePage(BASE_URL);
        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        homePage.clickDismissButton();
        navigateToSignUpPage();
        signUpPage.isSignUpPageDisplay();
        doSignUp();
    }

    @Test
    public void verifyUserCanLoginWithValidCredential() {
        HomePage homePage = new HomePage();
        navigateToHomePage(BASE_URL);
        loginWithValidCredential();
    }

    @Test
    public void verifyUserCanNotLoginWithInvalidCredential() {
        navigateToHomePage(BASE_URL);
        loginWithInvalidCredential();
    }

    @Test
    public void verifyUserCanSearchValidProduct() {
        SearchPage searchPage = new SearchPage();
        navigateToHomePage(BASE_URL);
        loginWithValidCredential();
        doSearchProduct();
        searchPage.isProductAvailable();
    }

    @Test
    public void verifyUserCanUpdateProfile() {
        navigateToHomePage(BASE_URL);
        loginWithValidCredential();
        doUpdateProfile();
    }

    @Test
    public void verifyValidUserCanPurchaseItem() {
        HomePage homePage = new HomePage();
        BasketPage basketPage = new BasketPage();
        OrderSummaryPage orderSummaryPage = new OrderSummaryPage();
        navigateToHomePage(BASE_URL);
        loginWithValidCredential();
        doSearchProduct();
        homePage.clickToAddToCart();
        homePage.clickToBasket();
        basketPage.isBasketDisplay();
        basketPage.clickToCheckOut();
        addNewAddressAndSelectAddress();
        doPayment();
        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        orderSummaryPage.clickToPlaceOrder();
        orderSummaryPage.isDeliveryDisplayed();
    }
}
