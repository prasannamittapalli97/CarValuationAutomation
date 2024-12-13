@ui
Feature: Vehicle Valuation Details Verification

  Scenario Outline: Verify Vehicle Valuation details from webuyanycar website

    Given I get the vehicle registration numbers list from the "<car_input_file>"
    When I navigate to webuyanycar website to get vehicle details
    And I get the expected vehicle details from the "<car_output_file>"
    Then verify vehicle details from output file are matching with the details on the website
    Examples:
      | car_input_file                            | car_output_file                            |
      | src/test/resources/testdata/car_input.txt | src/test/resources/testdata/car_output.txt |