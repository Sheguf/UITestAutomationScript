Feature: Payment Functionality



@Reg
Scenario: Payment with COD
When user enter "standard_user" and "secret_sauce"
And user clicks on login button
And user add product into the cart
Then validate cart count is "1"
And user click on checkout button
And user enter "test" "automation" "201301"
And user click on continue button
And user click on finish button
Then validate order success message