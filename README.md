# Command Line Shopping Basket Cost Calculator
Takes a shopping list of arguments, applies a set of disounts and prints to the console: a subtotal, a list of discounts applied and a final total after discounts.

Used to practice design patterns and test driven development.

## Decisions made:
1. Dependency injection used so that classes can be tested with unchangable dummy data.
2. Classes separated to create a more loosely coupled architecture.
3. All 'System.out's are contained in a method within the PriceBasket class. This is to make it easier to change into a different output method, possibly using dependency injection
4. Item names and prices stored in the Catalogue record which wraps a HashMap. This method chosen as only name and price are needed. However, if more information and/or methods were required, I would consider replacing this with a factory pattern creating a class for each item type implementing an Item interface.
5. Catalogue uses subclasses to allow for dummy data to test with.
6. Money class created to expose the methods I needed rather than using BigDecimal directly.
7. Discount interface allows for easy swapping in/out of discount logic.
8. Unit tests written on individual classes and more complete integration test written for the PriceBasket main class.
