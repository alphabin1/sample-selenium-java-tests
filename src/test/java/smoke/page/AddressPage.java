package smoke.page;

import core.component.BaseElement;
import core.pageobject.BasePO;
import org.openqa.selenium.support.FindBy;

public class AddressPage extends BasePO {

    @FindBy(xpath = "//*[@aria-label='Add a new address']")
    BaseElement addNewAddress;

    @FindBy(id = "mat-input-3")
    BaseElement country;

    @FindBy(id = "mat-input-4")
    BaseElement name;

    @FindBy(id = "mat-input-5")
    BaseElement mobileNumber;

    @FindBy(id = "mat-input-6")
    BaseElement zipCode;

    @FindBy(id = "address")
    BaseElement address;

    @FindBy(id = "mat-input-8")
    BaseElement city;

    @FindBy(id = "mat-input-9")
    BaseElement state;

    @FindBy(id = "submitButton")
    BaseElement submitButton;

    @FindBy(xpath = "//*[@id=\"card\"]/app-address/mat-card/mat-table/mat-row[1]/mat-cell[2]")
    BaseElement selectAddress;

    @FindBy(xpath = "//*[@id=\"card\"]/app-address/mat-card/button/span[1]/span")
    BaseElement continueButton;

    @FindBy(xpath = "//app-delivery-method/mat-card/div[4]//button[2]//span//mat-icon")
    BaseElement continueToNext;

    public BaseElement getContinueToNext() {
        return continueToNext;
    }

    public void clickToContinueNextButton() {
        getContinueToNext().click();
    }

    public BaseElement getContinueButton() {
        return continueButton;
    }

    public void clickContinueButton() {
        getContinueButton().click();
    }

    public BaseElement getSelectAddress() {
        return selectAddress;
    }

    public void clickToSelectAddress() {
        getSelectAddress().click();
    }

    public BaseElement getCity() {
        return city;
    }

    public void fillCity(String city) {
        getCity().sendKeys(city);
    }

    public BaseElement getSubmitButton() {
        return submitButton;
    }

    public void clickToSubmit() {
        getSubmitButton().click();
    }

    public BaseElement getState() {
        return state;
    }

    public void fillState(String state) {
        getState().sendKeys(state);
    }

    public BaseElement getAddress() {
        return address;
    }

    public void fillAddress(String address) {
        getAddress().sendKeys(address);
    }

    public BaseElement getZipCode() {
        return zipCode;
    }

    public void fillZipCode(String zipCode) {
        getZipCode().sendKeys(zipCode);
    }

    public BaseElement getMobileNumber() {
        return mobileNumber;
    }

    public void fillMobileNumber(String mobileNumber) {
        getMobileNumber().sendKeys(mobileNumber);
    }

    public BaseElement getName() {
        return name;
    }

    public void fillName(String name) {
        getName().sendKeys(name);
    }

    public BaseElement getCountry() {
        return country;
    }

    public void fillCounty(String country) {
        getCountry().sendKeys(country);
    }

    public BaseElement getAddNewAddress() {
        return addNewAddress;
    }

    public void clickToAddNewAddress() {
        getAddNewAddress().click();
    }

    public void fillAddressForm(String country,String name,String mobileNumber,String zipCode,String  address,String city,String state){
        fillCounty(country);
        fillName(name);
        fillMobileNumber(mobileNumber);
        fillZipCode(zipCode);
        fillAddress(address);
        fillCity(city);
        fillState(state);
    }
}

