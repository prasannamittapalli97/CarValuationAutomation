package com.identity.CarValuationAutomation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class WeBuyAnyCarHomePage extends BasePage{

    @FindBy(how = How.ID, using= "onetrust-accept-btn-handler")
    private WebElement acceptCookies;
    @FindBy(how = How.ID, using = "vehicleReg")
    private WebElement vehicleRegistrationNumber;

    @FindBy(how = How.ID, using = "btn-go")
    private WebElement getCarValuationButton;

    @FindBy(how = How.ID, using = "Mileage")
    private WebElement mileage;

    public void enterRegistrationAndClickGetCarValuation(String registrationNumber) throws InterruptedException {
        enterRegistrationNumber(registrationNumber);
        enterMileage();
        clickOnGetCarValuation();
    }

    public void acceptCookies() throws InterruptedException {
        acceptCookies.click();
    }

    private void enterRegistrationNumber(String regNumber) {
        waitForWebElementTobeClickable(vehicleRegistrationNumber);
        vehicleRegistrationNumber.clear();
        vehicleRegistrationNumber.sendKeys(regNumber);
    }

    private void enterMileage() {
        waitForWebElementTobeClickable(mileage);
        mileage.clear();
        mileage.sendKeys(String.valueOf(new Random().nextInt(10000)));
    }

    private void clickOnGetCarValuation() {
        waitForWebElementTobeClickable(getCarValuationButton);
        getCarValuationButton.click();
    }

}
