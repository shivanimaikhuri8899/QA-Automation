package QA;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class OAuth {
    public static void main(String[] args){
        String response=given().log().all()
                .formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type", "client_credentials")
                .formParams("scope", "trust")
                .when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
                .then().log().all().statusCode(200).extract().response().asString();
        JsonPath js=new JsonPath(response);
        String token=js.getString("access_token");
        System.out.println(token);
        String response1=given().log().all().queryParam("access_token",token)
                .when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
        System.out.println(response1);
    }
}
