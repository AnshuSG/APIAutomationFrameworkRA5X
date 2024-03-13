package org.SG.base;

import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.SG.actions.AssertActions;
import org.SG.endpoints.APIConstants;
import org.SG.modules.payloadmanager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseTest {


    public RequestSpecification requestSpecification;
    public AssertActions assertActions;
    public payloadmanager  payloadManager;
    public JsonPath jsonPath;

    public Response response;
     public ValidatableResponse validatableResponse;

    @BeforeMethod(alwaysRun = true)
    public void setConfig(){
  payloadManager = new payloadmanager();
  assertActions = new AssertActions();
  requestSpecification = new RequestSpecBuilder()
          .setBaseUri(APIConstants.BASE_URL)
          .addHeader("Content-Type","application/json")
          .addHeader("Authorization","auth")
          .build().log().all();

    }



    public String getToken() throws JsonProcessingException {



        requestSpecification = RestAssured.given().baseUri(APIConstants.BASE_URL).basePath("/auth");
        String payload = payloadManager.setToken();

        response = requestSpecification.contentType(ContentType.JSON).
                body(payload).when().post();

        jsonPath = new JsonPath(response.asString());
         return jsonPath.getString("token");


    }


}
