package QA;

import io.restassured.RestAssured;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;

public class JsonFileCase {
    public static void main(String[] args) throws IOException {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(generateString("C:\\Users\\Admin\\IdeaProjects\\RestAssured\\src\\main\\java\\QA\\Data.json"))
                .when().post("maps/api/place/add/json")
                .then().assertThat().log().all().statusCode(200);
    }

    public static String generateString(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
