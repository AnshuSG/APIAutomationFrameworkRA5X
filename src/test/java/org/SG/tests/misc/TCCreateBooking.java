package org.SG.tests.misc;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.SG.endpoints.APIConstants;
import org.testng.annotations.Test;
import org.SG.base.BaseTest;

import static io.restassured.RestAssured.requestSpecification;

public class TCCreateBooking  extends BaseTest {


    //step1 - POST
    //Url
    //Header
    //BODY
    //Auth - no

    //step2
    //Prepare the payload(object to json string)
    // send the request


    //Step3
    //Validate Response(Json String - object)
    //Firstname
    //Status code
    //Time response

    //prepare your payload

     @Owner("Surekha")
     @Description("Verify that the " +
             "create booking with " +
             "valid payload .Status code 200")
    @Test
    public void testPositivePostReq(){

    requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
     response = RestAssured.given().spec(requestSpecification)
             .when().body(payloadManager.createPayloadGSON()).post();
      validatableResponse   =  response.then().log().all();
      validatableResponse.statusCode(200);



    }

}
