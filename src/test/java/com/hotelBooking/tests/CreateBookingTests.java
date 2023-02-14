package com.hotelBooking.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotelBooking.models.Booking;
import com.hotelBooking.models.BookingDates;
import com.hotelBooking.models.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateBookingTests extends BaseTest{

    @Test
    public void createBookingTests(){

        Response response = createBooking("Ege", "rest", 4849);
        response.prettyPrint();

        Assert.assertEquals(response.jsonPath().getJsonObject("booking.firstname"), "Ege");
        Assert.assertEquals(response.jsonPath().getJsonObject("booking.lastname"), "rest");
        Assert.assertEquals((Integer) response.jsonPath().getJsonObject("booking.totalprice"), 4849);
        Assert.assertEquals(response.jsonPath().getJsonObject("booking.depositpaid"), false);


    }

    @Test
    public void createBookingWithPojo(){
        BookingDates bookingdates = new BookingDates("2023-03-01", "2023-03-04");

        Booking booking = new Booking("Nil","Elis",500,true,bookingdates,"No Smoking Room");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                        .post("/booking");
    response
            .then()
            .statusCode(200);


        BookingResponse bookingResponse = response.as(BookingResponse.class); // gelen response BookingResponse classina yazildi.

        Assert.assertEquals("Nil", bookingResponse.getBooking().getFirstname());
        Assert.assertEquals("Elis", bookingResponse.getBooking().getLastname());
    }



}
