Feature: Basket Functionality


@Reg
Scenario: Add to cart from project listed page
When user enter "standard_user" and "secret_sauce"
And user clicks on login button
And user add product into the cart
Then validate cart count is "1"
