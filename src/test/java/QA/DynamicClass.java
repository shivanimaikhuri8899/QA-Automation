package QA;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DynamicClass {
    @Test(dataProvider="getData")
    public void addBook(String isbn,String aisle){
        RestAssured.baseURI="http://216.10.245.166";
        String response=given().header("Content-Type","application/json")
                .body(Payload.addBook(isbn,aisle))
                .when().post("" +
                        "Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js=ReusableMethods.rawJson(response);
        String id=js.get("ID");
        System.out.println(id);



        given().log().all().header("Content-Type","application/json")
                .body(Payload.deleteBook(id))
                .when().post("/Library/DeleteBook.php")
                .then().assertThat().log().all().statusCode(200);
    }

    @DataProvider
    public Object[][] getData(){
        return new Object[][] {{"adqfs","3164"},{"acsfs","3464"},{"rdsfs","6264"}};
    }
}
