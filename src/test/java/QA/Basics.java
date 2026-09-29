package QA;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Basics {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        //Add Place
        String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
                .body(Payload.addPlace()).when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
        JsonPath json=ReusableMethods.rawJson(response);
        String placeId=json.getString("place_id");
        System.out.println(placeId);
        //UpdatePlace
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(Payload.updatePlace(placeId))
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
        //Get Place
       // JsonPath json1=new JsonPath(Payload.updatePlace(placeId));
       // String expectedAddress=json1.getString("address");
        String expectedAddress="70 Summer Walk";

        String response1=given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json")
                .then().assertThat().log().all().statusCode(200).body("address",equalTo(expectedAddress))
                .extract().response().asString();
        JsonPath json2=ReusableMethods.rawJson(response1);
        String actualAddress=json2.getString("address");

        Assert.assertEquals(expectedAddress,actualAddress);
    }
}
