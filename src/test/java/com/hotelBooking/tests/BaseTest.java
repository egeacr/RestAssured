package com.hotelBooking.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class BaseTest {
    RequestSpecification spec;
    @BeforeTest
    public void setup(){

         spec = new RequestSpecBuilder()
                .setBaseUri("https://restful-booker.herokuapp.com")
                .addFilters(Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter())) // log requests and responses
                .build();

    }


    protected String bookingObject(String firstName, String lastName, int totalPrice){
        JSONObject body = new JSONObject();
        body.put("firstname", firstName);
        body.put("lastname", lastName);
        body.put ("totalprice", totalPrice);
        body.put("depositpaid", false);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin","2023-02-09");
        bookingDates.put("checkout","2023-02-15");

        body.put("bookingdates",bookingDates);
        body.put("additionalneeds", "Parking Lot");

        return body.toString();
    } //reservation informations

    protected Response createBooking(String FN, String LN, int totalprice){

        Response response = given(spec)
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject(FN,LN,totalprice))
                .post("/booking");

        response.then().statusCode(200);
        return response;
    } // create a new reservations with bookingObject()

    public String createToken(){

        JSONObject tokenInfo = new JSONObject();
        tokenInfo.put("username", "admin");
        tokenInfo.put("password", "password123");
        Response response = given(spec)
                .when()
                .contentType(ContentType.JSON)
                .body(tokenInfo.toString())
                .post("/auth");

        String receivedToken = response.jsonPath().getJsonObject("token");
        String token = "token="+receivedToken;
        System.out.println(token);
        return token;
    }


}
