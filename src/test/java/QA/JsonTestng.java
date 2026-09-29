package QA;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JsonTestng {
    @Test
    public void mm(){
        //6. Verify if Sum of all Course prices matches with Purchase Amount
        int total=0;
        JsonPath js=new JsonPath(Payload.coursePrice());
        int purchaseAmount=js.getInt("dashboard.purchaseAmount");
        int size = js.get("courses.size()");
        for(int j=0;j<size;j++){
            System.out.println(js.get("courses["+j+"].title").toString());
            int actualPrice=js.getInt("courses[" + j + "].price");
            int actualCopies=js.getInt("courses[" + j + "].copies");
            System.out.println(actualPrice+"  "+actualCopies);
            total+=actualPrice*actualCopies;
            System.out.println(total);
        }
        Assert.assertEquals(total,purchaseAmount
        );
    }
}
