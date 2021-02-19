package orangehrm.lib;

import orangehrm.environment.ConfVariables;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import orangehrm.oauth2.oauthToken;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class Employees {

    public String accessToken = "";
    final String ACTION = "employee/";
    final String ORGANIZATION = "organization";


    public String getRandomId(){
         return String.valueOf(Math.random()*1000000);
    }


    public int currentStatus() {

        oauthToken oauth = new oauthToken();

        accessToken = oauth.getOauth_token();

        int statusCode = given()
                .headers(
                        "Authorization",
                        "Bearer " + accessToken)
                .relaxedHTTPSValidation()
                .when()
                .get(ConfVariables.getUrlBase()+ConfVariables.getPath()+ORGANIZATION)
                .statusCode();

        return statusCode;

    }

    public void getEmployee(){

        SerenityRest.given()
                .headers(
                        "Authorization",
                        "Bearer " + accessToken,
                        "Accept", ContentType.JSON)
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .get(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION + ConfVariables.getEmployeeId());



    }

    public void createEmployee(String name, String middlename, String lastname){


        JSONObject requestParams = new JSONObject();

        requestParams.put("firstName", name);
        requestParams.put("middleName", middlename);
        requestParams.put("lastName", lastname);
        requestParams.put("code", "ID"+ getRandomId());

        SerenityRest.given()
                .headers(
                        "Authorization",
                        "Bearer " + accessToken,
                        "Accept", ContentType.JSON)
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .body(requestParams.toJSONString())
                .post(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION + ConfVariables.getEmployeeId());

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
                .headers(
                        "Authorization",
                        "Bearer " + accessToken,
                        "Accept", ContentType.JSON)
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .body(requestParams.toJSONString())
                .put(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION + ConfVariables.getEmployeeId());


    }

}
