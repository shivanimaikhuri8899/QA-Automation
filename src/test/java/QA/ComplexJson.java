package QA;

import io.restassured.path.json.JsonPath;

public class ComplexJson {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(Payload.coursePrice());
        int size = js.get("courses.size()");
        System.out.println(size);
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);
        String title = js.get("courses[0].title");
        System.out.println(title);
//4. Print All course titles and their respective Prices
        for (int i = 0; i < size; i++) {
            String title1 = js.get("courses[" + i + "].title");
            System.out.print(title1);
            System.out.println(js.get("courses[" + i + "].price").toString());
        }
        //5. Print no of copies sold by RPA Course

        int i = 0;
        while (i < size) {
            String title1 = js.get("courses[" + i + "].title");
            if (title1.equalsIgnoreCase("RPA")) {
                System.out.println(js.get("courses[" + i + "].copies").toString());
                break;
            }
            i++;
        }
    }
}
