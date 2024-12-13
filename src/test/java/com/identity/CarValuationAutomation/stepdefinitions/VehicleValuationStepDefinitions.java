package com.identity.CarValuationAutomation.stepdefinitions;

import com.identity.CarValuationAutomation.models.Vehicle;
import com.identity.CarValuationAutomation.pages.CarDetailsPage;
import com.identity.CarValuationAutomation.pages.WeBuyAnyCarHomePage;
import com.identity.CarValuationAutomation.utils.CsvUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.SoftAssertions;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class VehicleValuationStepDefinitions {

    @Autowired
    private WeBuyAnyCarHomePage homePage;
    @Autowired
    private CarDetailsPage carDetailsPage;
    @Autowired
    private CsvUtil csvUtil;
    private List<String> inputVehicleRegNumbersList;
    private Map<String, Vehicle> outputVehicleDetailsMap;
    private Map<String, Vehicle> actualCarDetailsMap;

    @Given("I get the vehicle registration numbers list from the {string}")
    public void iGetTheVehicleRegistrationNumbersListFromThe(String inputFilePath) throws IOException {
        String inputFileContent = CsvUtil.readInputFileAsString(inputFilePath);
        inputVehicleRegNumbersList = CsvUtil.getVehicleRegistrationNumbers(inputFileContent);
    }

    @When("I navigate to webuyanycar website to get vehicle details")
    public void i_navigate_to_webuyanycar_website_to_get_vehicle_details() throws InterruptedException {
        homePage.navigateToBaseUrl();
        homePage.acceptCookies();
        actualCarDetailsMap = new HashMap<>();
        for (String carRegistrationNumber : inputVehicleRegNumbersList) {
            Vehicle actualVehicle = new Vehicle();
            homePage.enterRegistrationAndClickGetCarValuation(carRegistrationNumber);
            if (carDetailsPage.isCarDetailsPageDisplayed()) {
                actualVehicle.setRegistration(carDetailsPage.getCarRegistrationNumber());
                actualVehicle.setMake(carDetailsPage.getCarManufacturer());
                actualVehicle.setModel(carDetailsPage.getCarModel());
                actualVehicle.setYear(carDetailsPage.getCarYear());
                actualCarDetailsMap.put(carRegistrationNumber, actualVehicle);
                carDetailsPage.navigateBack();
            } else {
                log.info("Vehicle data not found for Registration Number: " + carRegistrationNumber);
                homePage.navigateBack();
            }
        }
    }

    @And("I get the expected vehicle details from the {string}")
    public void iGetTheExpectedVehicleDetailsFromThe(String outputFilePath) throws FileNotFoundException {
        outputVehicleDetailsMap = CsvUtil.getExpectedVehicleDetailsFromCSV(outputFilePath);
    }

    @Then("verify vehicle details from output file are matching with the details on the website")
    public void verifyVehicleDetailsFromOutputFileAreMatchingWithTheDetailsOnTheWebsite() {
        SoftAssertions softAssertions = new SoftAssertions();
        for (String carRegistrationNumber : inputVehicleRegNumbersList) {
            log.info("Verifying the details for registration Number:"+ carRegistrationNumber);
            Vehicle actualVehicleDetails = actualCarDetailsMap.get(carRegistrationNumber);
            Vehicle outputVehicleDetails = outputVehicleDetailsMap.get(carRegistrationNumber.replaceAll("\\s", ""));
            softAssertions.assertThat(actualVehicleDetails).as("Details doesn't exist in webuyanycar for the registration Number:"+carRegistrationNumber).isNotNull();
            if(actualVehicleDetails != null){
                softAssertions.assertThat(outputVehicleDetails.getRegistration()).isEqualTo(actualVehicleDetails.getRegistration());
                softAssertions.assertThat(outputVehicleDetails.getMake()).isEqualTo(actualVehicleDetails.getMake());
                softAssertions.assertThat(outputVehicleDetails.getYear()).isEqualTo(actualVehicleDetails.getYear());
                softAssertions.assertThat(outputVehicleDetails.getModel()).isEqualTo(actualVehicleDetails.getModel());
            }else{
                log.error("Details doesn't exist in webuyanycar for the registration Number:"+carRegistrationNumber);
            }
        }
        softAssertions.assertAll();
    }
}
