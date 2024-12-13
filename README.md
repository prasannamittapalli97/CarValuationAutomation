## CarValuation Automation

### As part of the test we are doing below tasks:
- Reading the registration numbers from car_input.txt file
- Go to webuyanycar.com website and get the details of the car and compare the details with the car_output.txt file.
- if the details from webuyanycar website doesn't match then the test will fail
- Test will also fail incase if the registration number we read from car_input.txt file
  doesn't exist in the website

### Technology Stack

- Java 17
- Selenium Webdriver
- Spring Boot
- Cucumber
- Maven
- OpenCSV
- AssertJ
- Slf4j


### IDE

- IntelliJ IDEA


### Browser Support

- Google Chrome


### Run Tests

- `mvn clean test`

### Reports
- Generated reports are under target/cucumber-reports/cucumber.html
- Screenshots are attached if the scenario fail and I have used Soft Assertions to make sure it to go through all registration numbers and list all issues


### Test Evidence
- Test execution is recorded and can be found under test_evidence folder.

