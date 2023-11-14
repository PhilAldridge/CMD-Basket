# Command Line Shopping Basket Cost Calculator
Takes a shopping list of arguments, applies a set of disounts and prints to the console: a subtotal, a list of discounts applied and a final total after discounts.

Used to practice design patterns and test driven development.

## Decisions made:
1. Discounts applied through the class, Offers, as a factory. This is to enable different offers to be added or removed as required.
2. All 'System.out's are contained in a method within the PriceBasket class. This is to make it easier to change into a different output method, possibly using dependency injection
3. Item names and prices stored in the Catalogue record which wraps a HashMap. This method chosen as only name and price are needed. However, if more information and/or methods were required, I would consider replacing this with a factory pattern creating a class for each item type implementing an Item interface.
4. Money class created to expose the methods I needed rather than using BigDecimal directly.
