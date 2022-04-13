package smoke.testcases;

import core.testcase.BaseTestCase;
import core.util.PropertyUtils;
import org.testng.Assert;
import smoke.page.*;

import java.util.concurrent.TimeUnit;

public class BaseTestCases extends BaseTestCase {

    String BASE_URL = PropertyUtils.getProperty("base.url");

    String VALID_EMAIL = PropertyUtils.getProperty("validemail");
    String VALID_PASSWORD = PropertyUtils.getProperty("validpassword");

    String INVALID_EMAIL = PropertyUtils.getProperty("invalidemail");
    String INVALID_PASSWORD = PropertyUtils.getProperty("invalidpassword");

    String expectedErrorMessage = "Invalid email or password.";
    String productName = "Apple Juice (1000ml)";

    public HomePage navigateToHomePage(String baseurl){
        navigateToUrl(baseurl);
        HomePage homePage = new HomePage();
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Home page didn't display, Login might have been failed. Please see the failure screenshot!");
        return homePage;
    }

    public void navigateToUrl(String url){
        getDriver().get(url);
        waitUtils.waitForPageToLoad();
    }

    public void doSignUp(){
        SignUpPage signUpPage = new SignUpPage();

        signUpPage.setEmailEditText(VALID_EMAIL);
        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        signUpPage.setPasswordEditText(VALID_PASSWORD);
        signUpPage.setRepeatPassword(VALID_PASSWORD);
        signUpPage.sync();
        signUpPage.clickOnRegestration();
    }
    public void navigateToSignUpPage(){
        HomePage homePage = new HomePage();
        LoginPage loginPage = new LoginPage();

        homePage.clickDismissButton();
        homePage.clickToAccountButton();
        homePage.clickToLogin();
        loginPage.clickOnNotYetCustomerLink();
    }
    public void navigateToLoginPage(){
        HomePage homePage = new HomePage();

        homePage.clickDismissButton();
        homePage.clickToAccountButton();
        homePage.clickToLogin();
    }
    public void loginWithValidCredential(){
        LoginPage loginPage = new LoginPage();

        navigateToLoginPage();
        loginPage.setEmailEditText(VALID_EMAIL);
        loginPage.setPasswordEditText(VALID_PASSWORD);
        loginPage.sync();
    }

    public void loginWithInvalidCredential(){
        LoginPage loginPage = new LoginPage();

        navigateToLoginPage();
        loginPage.setEmailEditText(INVALID_EMAIL);
        loginPage.setPasswordEditText(INVALID_PASSWORD);
        loginPage.sync();
        getDriver().manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        String actualErrorMessageFromAlert = loginPage.getErrorAlert().getText();
        Assert.assertEquals(actualErrorMessageFromAlert, expectedErrorMessage, "Actual and Expected Error message didn't match!");
    }

    public void addNewAddressAndSelectAddress(){
        String country = "UK";
        String city = "New Jersey";
        String mobileNumber = "123456789";
        String state = "Ohio";
        String name = "test";
        String zipCode = "123456";
        String address = "202,Near library";
        AddressPage addressPage = new AddressPage();
        DeliveryPage deliveryPage = new DeliveryPage();

        addressPage.clickToAddNewAddress();
        addressPage.fillAddressForm(country,name,mobileNumber,zipCode,address,city,state);
        addressPage.clickToSubmit();
        addressPage.clickToSelectAddress();
        addressPage.clickContinueButton();
        deliveryPage.clickToStandardDelivery();
        addressPage.clickToContinueNextButton();
    }

    public void doPayment(){
        PaymentPage paymentPage = new PaymentPage();
        paymentPage.clickOnCard();
        paymentPage.clickOnContinueNext();
    }

    public void doSearchProduct(){
        HomePage homePage = new HomePage();
        homePage.clickOnSearchIcon();
        homePage.setSearchInput(productName);
        homePage.fillSearchTextInput();
    }

    public void doUpdateProfile(){
        String userName = "ferrero07";
        HomePage homePage = new HomePage();
        ProfilePage profilePage = new ProfilePage();
        homePage.clickToAccountButton();
        homePage.clickToUserProfile();
        profilePage.isProfilePageDisplayed();
        profilePage.setUsername(userName);
        profilePage.fillUsername();
    }
}
