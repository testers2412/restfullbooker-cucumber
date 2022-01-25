Feature: Testing the CRUD operations on booking app

  @SMOKE
@REGRESSION
  Scenario Outline: User can create booking with valid data
    When User create a booking providing the information firstName "<firstName>", lastName "<lastName>", totalPrice "<totalPrice>", depositPaid "<depositPaid>", checkIn "<checkIn>", checkOut "<checkOut>", additionalNeeds "<additionalNeeds>"
    Then User should verify the status code 200, request successful
    And  User should be able to get the newly created booking Id
    Examples:
      | firstName | lastName | totalPrice | depositPaid | checkIn    | checkOut   | additionalNeeds |
      | Prime     | Trainee  | 400        | true        | 2022-01-24 | 2022-01-30 | Breakfast       |

  @SMOKE
  @REGRESSION
  Scenario: User Should verify newly created booking by getting data by Id
    When User should get the newly created booking by Id
    Then User should verify the status code 200, request successful

  @REGRESSION
  Scenario Outline: User should be able to update the booking
    When User provides updated information with authorization token, firstName "<firstName>", lastName "<lastName>", totalPrice "<totalPrice>", depositPaid "<depositPaid>", checkIn "<checkIn>", checkOut "<checkOut>", additionalNeeds "<additionalNeeds>"
    Then User should verify the status code 200, request successful
    Examples:
      | firstName     | lastName        | totalPrice | depositPaid | checkIn    | checkOut   | additionalNeeds |
      | Updated_Prime | Updated_Trainee | 400        | true        | 2022-01-24 | 2022-01-30 | Bed & Breakfast |

  @REGRESSION
  Scenario: User should be able to delete the booking
    When User request to delete the booking by providing Id and authorization token
    Then User should verify the status code 201
    And User should verify the deletion by searching the booking by Id and verifying the status code 404, not found