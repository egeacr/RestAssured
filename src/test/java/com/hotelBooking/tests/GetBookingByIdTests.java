package com.hotelBooking.tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Test
public class GetBookingByIdTests extends BaseTest{

    public void getBookingById() {

        Response newBooking = createBooking("ege", "acar", 488);
        int bookingId = newBooking.jsonPath().getJsonObject("bookingid");

        Response   response =given().when().get("https://restful-booker.herokuapp.com/booking/" + bookingId) ;

        response .then()
                .statusCode(200);

        response.prettyPrint(); //print the response on the console

        String firstname = response.jsonPath().getJsonObject("firstname");
        String lastname = response.jsonPath().getJsonObject("lastname");
        int totalprice = response.jsonPath().getJsonObject("totalprice");

        Assert.assertEquals(firstname, "Ege");
        Assert.assertEquals(lastname, "Software");
        Assert.assertEquals(totalprice, 4848);


    }
}
