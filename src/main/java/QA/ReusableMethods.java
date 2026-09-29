package QA;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
    public static JsonPath rawJson(String response){
        JsonPath json=new JsonPath(response);//for parsing Json
        return json;
    }
}
