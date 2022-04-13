package smoke.page;

import core.component.BaseElement;
import core.pageobject.BasePO;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePO {

    @FindBy(css = "input[name=\"email\"]")
    BaseElement emailField;
    private String emailText;

    @FindBy(css = "input[name=\"password\"]")
    BaseElement passwordField;
    private String passwordText;

    @FindBy(id = "loginButton")
    BaseElement loginButton;

    @FindBy(css = "a[href=\"#/register\"]")
    BaseElement notYetCustomer;

    @FindBy(css = "div.error")
    BaseElement errorAlert;

    public BaseElement getLoginButton() {
        return loginButton;
    }

    public void clickOnLoginButton() {
        getLoginButton().click();
    }

    public BaseElement getEmailField() {
        return emailField;
    }

    public BaseElement getPasswordField() {
        return passwordField;
    }

    public void setEmailEditText(String email) {
        emailText = email;
    }

    private void fillEmailEditText() {
        getEmailField().sendKeys(emailText);
    }

    public void setPasswordEditText(String password) {
        passwordText = password;
    }

    private void fillPasswordEditText() {
        getPasswordField().sendKeys(passwordText);
    }

    public void sync() {
        fillEmailEditText();
        fillPasswordEditText();
        clickOnLoginButton();
    }

    public BaseElement getErrorAlert() {
        return errorAlert;
    }

    public BaseElement getNotYetACustomerLink() {
        return notYetCustomer;
    }

    public void clickOnNotYetCustomerLink() {
        getNotYetACustomerLink().click();
    }
}
