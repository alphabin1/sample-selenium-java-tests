package smoke.page;

import core.component.BaseElement;
import core.pageobject.BasePO;

import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePO {

    @FindBy(id = "registration-form")
    BaseElement registrationContainer;

    @FindBy(id = "emailControl")
    BaseElement email;
    private String emailText;

    @FindBy(id = "passwordControl")
    BaseElement password;
    private String passwordText;

    @FindBy(id = "repeatPasswordControl")
    BaseElement repeatPassword;
    private String repeatPasswordText;

    @FindBy(css = "mat-select[name=\"securityQuestion")
    BaseElement securityDropDown;

    @FindBy(xpath = ".//mat-option[contains(.,\" Mother's birth date? (MM/DD/YY) \")]")
    BaseElement securityQuestion;

    @FindBy(id = "securityAnswerControl")
    BaseElement securityAnswer;
    private String answerText;

    @FindBy(id = "registerButton")
    BaseElement registerButton;

    public BaseElement getRegisterButton() {
        return registerButton;
    }

    public void clickOnRegestration() {
        getRegisterButton().doubleClick();
    }

    public BaseElement getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String answer) {
        answerText = answer;
    }

    public void fillSecurityAnswer() {
        getSecurityAnswer().sendKeys(answerText);
    }

    public BaseElement getOption() {
        return securityQuestion;
    }

    public void clickOnOption() {
        getOption().click();
    }

    public BaseElement getSecurityDropDown() {
        return securityDropDown;
    }

    public void clickToDropDown() {
        getSecurityDropDown().click();
    }

    public BaseElement getRegistrationContainer() {
        return registrationContainer;
    }

    public boolean isSignUpPageDisplay() {
        return getRegistrationContainer().isDisplayed();
    }

    public BaseElement getEmail() {
        return email;
    }

    public BaseElement getPassword() {
        return password;
    }

    public BaseElement getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        repeatPasswordText = repeatPassword;
    }

    public void setPasswordEditText(String password) {
        passwordText = password;
    }

    public void fillPasswordEditText() {
        getPassword().sendKeys(passwordText);
    }

    public void setEmailEditText(String email) {
        emailText = email;
    }

    public void fillEmailEditText() {
        getEmail().sendKeys(emailText);
    }

    public void fillRepeatPasswordText() {
        getRepeatPassword().sendKeys(repeatPasswordText);
    }

    public void sync() {
        fillEmailEditText();
        fillPasswordEditText();
        fillRepeatPasswordText();
        clickToDropDown();
        clickOnOption();
        setSecurityAnswer("test");
        fillSecurityAnswer();
    }
}
