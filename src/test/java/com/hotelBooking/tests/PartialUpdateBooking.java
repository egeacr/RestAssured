package com.hotelBooking.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PartialUpdateBooking extends BaseTest{

    @Test
    public void partialUpdateBooking(){

        Response newBooking = createBooking("Ege","Acar", 1234);
        int bookingid = newBooking.jsonPath().getJsonObject("bookingid");

        JSONObject patchBody = new JSONObject();
        patchBody.put("firstname", "Elis");


        Response response = given(spec)
                .contentType(ContentType.JSON)
                .header("Cookie", createToken())
                .body(patchBody.toString())
                .patch("/booking/"+bookingid);

        response.prettyPrint();
    }
}
