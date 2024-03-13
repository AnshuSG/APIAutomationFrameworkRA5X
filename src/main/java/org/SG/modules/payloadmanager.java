package org.SG.modules;

import com.github.javafaker.Book;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.SG.payloads.pojos.Auth;
import org.SG.payloads.pojos.Booking;
import org.SG.payloads.pojos.BookingResponse;
import org.SG.payloads.pojos.Bookingdates;

public class payloadmanager {

    //JAVA - JSON

    public String createPayloadGSON(){
       // Faker faker = new Faker();


        Booking booking = new Booking();
        //String expectedFirstName = faker.name().firstName();
        booking.setFirstname("Surekha");
        //booking.setFirstname(expectedFirstName);
        booking.setLastname("Ganti");
        booking.setTotalprice(1000);
        booking.setDepositpaid(true);

        Bookingdates bookingdates =  new Bookingdates();
        bookingdates.setCheckin("2018-01-01");
        bookingdates.setCheckout("2019-01-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");



        //Object -> Json string

        Gson gson = new Gson();
        String jsonStringbooking = gson.toJson(booking);
        System.out.println(jsonStringbooking);
         return jsonStringbooking;


    }


    public void createPayloadJackson(){

    }


    public BookingResponse JsontoObject(String JsonString) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper() ;
        BookingResponse bookingresponse = objectMapper.readValue(JsonString,BookingResponse.class);
        return bookingresponse;
    }

    public Booking JsontoObjectPUT(String jsonString) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper() ;
        Booking bookingObject = objectMapper.readValue(jsonString, Booking.class);
        return bookingObject;
    }


//    public Booking JsonToObjectPUT(String jsonString) throws JsonProcessingException {
//        objectMapper = new ObjectMapper();
//        Booking bookingRespons = objectMapper.readValue(jsonString, Booking.class);
//        return bookingRespons;
//    }


    public String  updatePayload() throws JsonProcessingException{
        ObjectMapper objectMapper =  new ObjectMapper();
        Booking booking = new Booking();
       // String expectedFirstName = faker.name().firstName();
        booking.setFirstname("Anshu");
        booking.setLastname("G");
        booking.setTotalprice(200);
        booking.setDepositpaid(false);

        Bookingdates bookingdates =  new Bookingdates();
        bookingdates.setCheckin("2018-09-09");
        bookingdates.setCheckout("2019-08-09");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Lunch");

        String payload = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return payload;



    }


    public String setToken() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper() ;
        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
       // System.out.println("OM" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(auth));
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(auth);
    }













}
