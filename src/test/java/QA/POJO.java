package QA;

public class POJO {
    private String message;
    private String greet;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setGreet(String greet) {
        this.greet = greet;
    }

    public String getGreet() {
        return greet;
    }

    /*{
    "message"="hello";
    "greet"="hi";}*/
    public static void main(String[] args){
        POJO pj = new POJO();
        pj.setMessage("hello");
        pj.getMessage();
        pj.setGreet("hi");
        pj.getGreet();
    }

}

