package org.SG.tests.crud.INT;
import com.google.gson.Gson;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.SG.base.BaseTest;
import org.SG.endpoints.APIConstants;
import org.SG.payloads.pojos.Booking;
import org.SG.payloads.pojos.BookingResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;


import static org.assertj.core.api.Assertions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class TC_Integration extends BaseTest {

    private static final Logger log = LogManager.getLogger(TC_Integration.class);


  String token;
    String BookingId;
    String BookingIdPojo;


    //get a token
    //create a booking
    //update the booking with booking id and token. how to pass variables from one test to another.
      // 1. Auth- API KEY
    // 2. Cookie based auth side
    //oAuth 2.0 - methd how you can use auth 2.0
    //delete the booking



    //get a token






    //create a booking

    @Test(groups="P0")
    public void testCreateBooking() throws JsonProcessingException {
        token = getToken();
        assertThat(token).isNotNull().isNotEmpty();
        Gson gson = new Gson();
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
        response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayloadGSON()).post();
        jsonPath = JsonPath.from(response.asString());
        validatableResponse   =  response.then().log().all();
        validatableResponse.statusCode(200);

        // Direct extraction from jsonpath
         BookingId = jsonPath.getString("bookingid");


        //Booking Response Class
        //BookingResponse bookingresponse = gson.fromJson(response.asString(),BookingResponse.class);
        BookingResponse bookingresponse = payloadManager.JsontoObject(response.asString());
         BookingIdPojo  = bookingresponse.getBookingid().toString();
        log.info("This is My Booking ID"+BookingIdPojo);
         assertThat(BookingId).isNotNull().isNotEmpty();








    }



//update the booking with booking id and token.
@Test(groups="P0",dependsOnMethods = {"testCreateBooking"})
    public void testCreateandUpdateBooking() throws JsonProcessingException {

    requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + BookingId);
    response = RestAssured.given().spec(requestSpecification).cookie("token",token)
            .when().body(payloadManager.updatePayload()).put();

    validatableResponse   =  response.then().log().all();
    //validatableResponse.statusCode(200);
    //validatableResponse.body("firstname", Matchers.is("Anshu"));


    Booking bookingresponse = payloadManager.JsontoObjectPUT(response.asString());

    String firstname  = bookingresponse.getFirstname().toString();

    assertThat(firstname).isEqualTo("Anshu").isNotNull();
    assertThat(bookingresponse.getLastname()).isNotNull();
    assertThat(bookingresponse.getDepositpaid()).isNotNull();
    assertThat(bookingresponse.getTotalprice()).isNotNull();
    assertThat(bookingresponse.getBookingdates().getCheckin()).isNotNull();
    assertThat(bookingresponse.getBookingdates().getCheckout()).isNotNull();







    }


    @Test(groups="P0",dependsOnMethods = {"testCreateandUpdateBooking"})
    public void DeleteCreatedBooking(){
        System.out.println("DeleteCreatedBooking" + token);
        System.out.println("DeleteCreatedBooking" + BookingId);
        System.out.println("DeleteCreatedBooking" + BookingIdPojo);



        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + BookingId).cookie("token",token);
        ValidatableResponse validatableResponse = RestAssured.given().spec(requestSpecification).auth().basic("admin", "password123")
                .when().delete().then().log().all();
        validatableResponse.statusCode(201);

    }

    @Test(groups="P0",dependsOnMethods = {"DeleteCreatedBooking"})
  public void testDeletebookingbyGet(){
        requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + BookingId).cookie("token",token);
        ValidatableResponse validatableResponse = RestAssured.given().spec(requestSpecification).auth().basic("admin", "password123")
                .when().get().then().log().all();
        validatableResponse.statusCode(404);
  }


}
