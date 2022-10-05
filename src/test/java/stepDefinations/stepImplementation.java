package stepDefinations;

import BDDCucumber.App;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.Json;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.ResourcesAPIsEnum;
import resources.TestDataBuild;
import resources.UtillityFunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class stepImplementation extends UtillityFunctions {

    RequestSpecification reqspec;
    RequestSpecification res;
    Response response;
    public static String placeID;
    TestDataBuild payload= new TestDataBuild();

    @Given("Add Place Payload with {string} {string} {string} {string} and {string}")
    public void addPlacePayloadWithAnd(String Name, String Address, String Language, String Website, String PhoneNumber) throws IOException {
      //  reqspec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").build();
        res = given().spec(RequestSpecifications()).body(payload.addPlacePayLoad(Name,Address,Language,Website,PhoneNumber));
    }

    @When("User calls {string} with {string} method")
    public void userCallsWithMethod(String resource, String Method) {
        ResourcesAPIsEnum resourceURI = ResourcesAPIsEnum.valueOf(resource);
        System.out.println("REsourceURI received is  " + resourceURI);
        if (Method.equalsIgnoreCase("POST"))
            response = res.when().post(resourceURI.getResource()).then().extract().response();
        else if (Method.equalsIgnoreCase("GET"))
            response = res.when().get(resourceURI.getResource()).then().extract().response();
        else if (Method.equalsIgnoreCase("DELETE"))
            response = res.when().post(resourceURI.getResource()).then().extract().response();
        else
            System.out.println("INcorrect Methods Type in feature file!! please revalidate its correctness!!");
    }

    @Then("Validating API successfully return {string} as {int}")
    public void validatingAPISuccessfullyReturnAs(String arg0, int arg1) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertEquals(200,response.getStatusCode());
    }

    @And("Response body returns {string} as {string}")
    public void responseBodyReturnsAs(String keyvalue, String ExpectedValue) {
       String valOfKey=getJsonPath(response,keyvalue);
        System.out.println("Value of "+keyvalue+" is "+valOfKey);
        Assert.assertEquals(ExpectedValue,valOfKey);
    }

    @And("Check {string} is hit with placeID queryparameter")
    public void checkIsHitWithPlaceIDQueryparameter(String Resource) throws IOException {
        placeID=getJsonPath(response,"place_id");
        res = given().spec(RequestSpecifications()).queryParam("place_id",placeID);
    }

    @And("Check {string} return the response with {string} provided")
    public void checkReturnTheResponseWithProvided(String Resource, String Name) {
        String fetchedName_FromAPIRes=getJsonPath(response,"name");
        System.out.println("Value of Name from API Response is --> " +fetchedName_FromAPIRes);
        Assert.assertEquals(Name,fetchedName_FromAPIRes);
    }

    @Given("User has access to Delete Place Payload")
    public void userHasAccessToDeletePlacePayload() throws IOException {
        res = given().spec(RequestSpecifications()).body(payload.deletePlacePayload(placeID));
    }
}
