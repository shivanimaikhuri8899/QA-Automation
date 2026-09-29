package QA.pojo;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;

public class OAuth_pojo {
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
       /* GetCourse gc=given().log().all().queryParam("access_token",token)
                .when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
        System.out.println(gc.getLinkedIn());*/

        GetCourse gc=	given()
                .queryParams("access_token", token)
                .when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
                .then().log().all().extract().response().as(GetCourse.class);

        System.out.println(gc.getLinkedIn());
        System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
        int length=gc.getCourses().getApi().size();
        for(int i=0;i<length;i++){
            if(gc.getCourses().getApi().get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){
                System.out.println(gc.getCourses().getApi().get(i).getPrice());
            }
        }
        String[] expectedTitle={"Selenium Webdriver Java","Cypress","Protractor"};
        List<WebAutomation> web=gc.getCourses().getWebAutomation();
        ArrayList<String> a=new ArrayList<String>();
        for(int m=0;m<web.size();m++){
            a.add(web.get(m).getCourseTitle());
        }
        List<String> b=Arrays.asList(expectedTitle);
        for(int i=0;i<a.size();i++){
            Assert.assertTrue(a.equals(b));
           // Assert.assertEquals(a.get(i),b.get(i));
        }
    }
}
