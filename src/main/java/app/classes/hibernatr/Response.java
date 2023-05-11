package app.classes.hibernatr;

public class Response {

    Integer id;
    String methodname;
    String abonent;
    String response;


    public String getAbonent() {
        return abonent;
    }
    public void setAbonent(String abonent) {
        this.abonent = abonent;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getMethodname() {
        return methodname;
    }
    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }
    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }

}
