package com.identity.CarValuationAutomation.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.stereotype.Component;

@Component
public class CarDetailsPage extends BasePage{

    @FindBy(how = How.ID, using= "questions-title")
    private WebElement carDetails;

    @FindBy(how = How.XPATH, using = "(//div[contains(@class, 'd-lg-block vehicle-details')])[2]//div[contains(text(),'Manufacturer:')]/following-sibling::div")
    private WebElement manufacturer;

    @FindBy(how = How.XPATH, using = "(//div[contains(@class, 'd-lg-block vehicle-details')])[2]//div[contains(text(),'Model:')]/following-sibling::div")
    private WebElement model;

    @FindBy(how = How.XPATH, using = "(//div[contains(@class, 'd-lg-block vehicle-details')])[2]//div[contains(text(),'Year:')]/following-sibling::div")
    private WebElement year;

    @FindBy(how = How.XPATH, using = "(//div[@id='vehicleImage'])[2]//div[contains(@class,'details-vrm')]")
    private WebElement registrationNumber;

    public boolean isCarDetailsPageDisplayed(){
        boolean isDisplayed;
        try{
            isDisplayed = carDetails.isDisplayed();
        }catch (NoSuchElementException e){
            isDisplayed = false;
        }
        return isDisplayed;
    }

    public String getCarManufacturer(){
        waitForWebElementTobeVisible(manufacturer);
        return manufacturer.getText();
    }

    public String getCarModel(){
        waitForWebElementTobeVisible(model);
        return model.getText();
    }

    public String getCarYear(){
        waitForWebElementTobeVisible(year);
        return year.getText();
    }

    public String getCarRegistrationNumber(){
        waitForWebElementTobeVisible(registrationNumber);
        return registrationNumber.getText();
    }
}
