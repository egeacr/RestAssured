package com.hotelBooking.tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class GetAllBookingsTests extends BaseTest{

    @Test
    public void getAllBookingTests(){
        given(spec)
                .when()
                .get("/booking")
                .then()
                .log().all()
                .statusCode(200);
    }


    @Test
    public void getBookingsWith_firstname_filter(){
        // create a new booking
        // add query parameter
        // get bookings
        // add assertion

        Response getBookingID =createBooking("Elis","Acar", 4848);
        int bookingid = getBookingID.jsonPath().getJsonObject("bookingid");

        spec.queryParam("firstname", "Elis");
        spec.queryParam("lastname", "Acar");

        Response response = given(spec)
                .when()
                .get("/booking");

        response.then().statusCode(200);

        List <Integer> list = response.jsonPath().getList("bookingid");  //write all booking ids on a list
        System.out.println(list);
        Assert.assertTrue(list.contains(bookingid));
    }


}
