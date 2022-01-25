package com.restfullbooker.cucumber.steps;

import com.restfullbooker.teststeps.AuthSteps;
import com.restfullbooker.teststeps.BookingSteps;
import com.restfullbooker.utils.TestUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import static org.hamcrest.CoreMatchers.equalTo;

public class BookingStepdefs {
    static String firstName ;
    static String lastName ;
    static int totalPrice ;
    static boolean depositPaid ;
    static String checkIn ;
    static String checkOut ;
    static String additionalNeeds ;
    static String username = "admin";
    static String password = "password123";
    static int bookingId;
    static String token;
    ValidatableResponse response;
    @Steps
    BookingSteps bookingSteps;
    @Steps
    AuthSteps authSteps;


    @When("^User create a booking providing the information firstName \"([^\"]*)\", lastName \"([^\"]*)\", totalPrice \"([^\"]*)\", depositPaid \"([^\"]*)\", checkIn \"([^\"]*)\", checkOut \"([^\"]*)\", additionalNeeds \"([^\"]*)\"$")
    public void userCreateABookingProvidingTheInformationFirstNameLastNameTotalPriceDepositPaidCheckInCheckOutAdditionalNeeds(String firstName, String lastName, int totalPrice, boolean depositPaid, String checkIn, String checkOut, String additionalNeeds)  {
        response= bookingSteps.createBooking(firstName,lastName,totalPrice,depositPaid,checkIn,checkOut,additionalNeeds);

    }

    @Then("^User should verify the status code 200, request successful$")
    public void userShouldVerifyTheStatusCode200RequestSuccessful() {
        response.statusCode(200).log().ifValidationFails();
    }

    @And("^User should be able to get the newly created booking Id$")
    public void userShouldBeAbleToGetTheNewlyCreatedBookingId() {
        bookingId= response.extract().path("bookingid");
    }

    @When("^User should get the newly created booking by Id$")
    public void userShouldGetTheNewlyCreatedBookingById() {
        response= bookingSteps.getBookingWithBookingId(bookingId);

    }

    @When("^User provides updated information with authorization token, firstName \"([^\"]*)\", lastName \"([^\"]*)\", totalPrice \"([^\"]*)\", depositPaid \"([^\"]*)\", checkIn \"([^\"]*)\", checkOut \"([^\"]*)\", additionalNeeds \"([^\"]*)\"$")
    public void userProvidesUpdatedInformationWithAuthorizationTokenFirstNameLastNameTotalPriceDepositPaidCheckInCheckOutAdditionalNeeds(String firstName, String lastName, int totalPrice, boolean depositPaid, String checkIn, String checkOut, String additionalNeeds)  {
     token= authSteps.getAuthToken(username,password);
     response= bookingSteps.updateBooking(bookingId,firstName,lastName,totalPrice,depositPaid,checkIn,checkOut,additionalNeeds,token);
    }

    @When("^User request to delete the booking by providing Id and authorization token$")
    public void userRequestToDeleteTheBookingByProvidingIdAndAuthorizationToken() {
        response=bookingSteps.deleteBookingWithBookingId(bookingId,token);
    }

    @Then("^User should verify the status code 201$")
    public void userShouldVerifyTheStatusCode201() {
        response.statusCode(201).log().ifValidationFails();
    }

    @And("^User should verify the deletion by searching the booking by Id and verifying the status code 404, not found$")
    public void userShouldVerifyTheDeletionBySearchingTheBookingByIdAndVerifyingTheStatusCode404NotFound() {
        bookingSteps.getBookingWithBookingId(bookingId).log().all().statusCode(404);
    }
}
