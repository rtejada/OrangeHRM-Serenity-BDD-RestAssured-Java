package orangehrm.lib;

import io.restassured.http.Header;
import orangehrm.environment.ConfVariables;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import orangehrm.oauth2.oauthToken;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class Employees {

    public String accessToken = "";

    static final String action = "employee/";
    static final String organization = "organization";


    public String getRandomId(){
         return String.valueOf(Math.random()*1000000);
    }


    public int currentStatus() {

        oauthToken oauth = new oauthToken();

        accessToken = oauth.getOauth_token();

        return given()
                .header(getAuthorizationHeader(accessToken))
                .header(getContentTypeHeader())
                .relaxedHTTPSValidation()
                .when()
                .get(ConfVariables.getUrlBase()+ConfVariables.getPath()+organization)
                .statusCode();

    }

    public void getEmployee(){

        SerenityRest.given()
                .header(getAuthorizationHeader(accessToken))
                .header(getContentTypeHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .get(ConfVariables.getUrlBase() + ConfVariables.getPath() + action + ConfVariables.getEmployeeId());

    }

    public void createEmployee(String name, String middlename, String lastname){

        JSONObject requestParams = new JSONObject();

        requestParams.put("firstName", name);
        requestParams.put("middleName", middlename);
        requestParams.put("lastName", lastname);
        requestParams.put("code", "ID"+ getRandomId());


        SerenityRest.given()
                .header(getAuthorizationHeader(accessToken))
                .header(getContentTypeHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .body(requestParams.toJSONString())
                .post(ConfVariables.getUrlBase() + ConfVariables.getPath() + action + ConfVariables.getEmployeeId());

    }

    public void updateEmployee(){

        JSONObject requestParams = new JSONObject();

        requestParams.put("firstName", "Khotlin");
        requestParams.put("middleName", "Pretty");
        requestParams.put("lastName", "Woman");
        requestParams.put("otherId", "ID" + getRandomId());
        requestParams.put("status", "Activo");
        requestParams.put("nationality",  "Spanish");
        requestParams.put("dob",  "1988-05-13");

        SerenityRest.given()
                .header(getAuthorizationHeader(accessToken))
                .header(getContentTypeHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .body(requestParams.toJSONString())
                .put(ConfVariables.getUrlBase() + ConfVariables.getPath() + action + ConfVariables.getEmployeeId());
    }

    @NotNull
    @Contract("_ -> new")
    private Header getAuthorizationHeader(String accessToken) {
        return new Header("Authorization", "Bearer " + accessToken);
    }

    @NotNull
    @Contract(" -> new")
    private Header getContentTypeHeader() {
        return new Header("Accept", ContentType.JSON.toString());
    }

}
