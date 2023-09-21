Feature: Login Functionality

Background: swagLab application

@Reg
Scenario: login with valid details
When user enter "standard_user" and "secret_sauce"
And user clicks on login button
Then validate successful login

@Reg
Scenario: login with invalid details
When user enter "standard_test" and "secret_test"
And user clicks on login button
Then validate error message

