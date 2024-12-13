package com.identity.CarValuationAutomation.pages;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;

public class BasePage {

    @Autowired
    public WebDriver driver;

    @PostConstruct
    public void initElements() {
        PageFactory.initElements(driver, this);
    }

    @Value("${application.url}")
    private String url;

    private void navigate(String url) {
        this.driver.navigate().to(url);
    }

    public void waitForWebElementTobeClickable(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitForWebElementTobeVisible(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofMillis(5000));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void navigateToBaseUrl(){
        navigate(url);
    }

    public void navigateBack(){
        this.driver.navigate().back();
    }


}
