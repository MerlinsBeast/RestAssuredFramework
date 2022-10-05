package cucumberOptions;

import io.restassured.RestAssured;
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
import static io.restassured.RestAssured.*;

public class sample {

    public static void main(){
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String res= given().log().all().
                queryParam("key","qaclick123")
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"vijays house\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://google.com\",\n" +
                        "  \"language\": \"French-IN\"\n" +
                        "}\n")
                .when()
                .log().all()
                .post("/maps/api/place/add/json")
                .then()
                .log().all()
                .extract().response().asString();

        System.out.println("Response collected as String is "+ "\n"+res);
        JsonPath js2= new JsonPath(res);
        String scope =js2.get("scope");
        System.out.println(scope);
//        Assert.assertEquals(200,response.getStatusCode());

    }
}
