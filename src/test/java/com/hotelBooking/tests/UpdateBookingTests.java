package com.hotelBooking.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTests extends BaseTest{

    @Test
    public void updateBooking(){
  /*      curl -X PUT \
        https://restful-booker.herokuapp.com/booking/1 \
        -H 'Content-Type: application/json' \
        -H 'Accept: application/json' \
        -H 'Cookie: token=abc123' \
        -d '{
        "firstname" : "James",
                "lastname" : "Brown",
                "totalprice" : 111,
                "depositpaid" : true,
                "bookingdates" : {
            "checkin" : "2018-01-01",
                    "checkout" : "2019-01-01"
        },
        "additionalneeds" : "Breakfast"
    }'
*/

        Response getBookingid = createBooking("ege", "acar", 4848);
        int bookingId = getBookingid.jsonPath().getJsonObject("bookingid");

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Cookie",createToken())
                .body(bookingObject("Ege", "RestTraining", 4848))
                .put("https://restful-booker.herokuapp.com/booking/"+bookingId);
        response.prettyPrint();

        String firstName = response.jsonPath().getJsonObject("firstname");
        String lastname = response.jsonPath().getJsonObject("lastname");
        int totalprice = response.jsonPath().getJsonObject("totalprice");

        Assert.assertEquals(firstName,"Ege");
        Assert.assertEquals(lastname, "RestTraining");
        Assert.assertEquals(totalprice, 4848);
    }






}
