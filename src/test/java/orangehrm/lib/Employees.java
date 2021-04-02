package orangehrm.lib;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import orangehrm.environment.ConfVariables;
import orangehrm.headers.Headers;
import orangehrm.oauth2.OauthToken;
import org.json.simple.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static io.restassured.RestAssured.given;

public class Employees extends Headers {

    private String accessToken = "";

    static final String ACTION = "employee/";
    static final String ORGANIZATION = "organization";


    public String getRandomId(){

        try {

            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");
            return String.valueOf(number.nextInt(1000000));

        } catch (NoSuchAlgorithmException e) {

            return "";
        }

    }


    public int currentStatus() {

        OauthToken oauth = new OauthToken();

        accessToken = oauth.getOauthToken();

        return given()
                .header(getAuthorizationHeader(accessToken))
                .header(getContentTypeHeader())
                .relaxedHTTPSValidation()
                .when()
                .get(ConfVariables.getUrlBase()+ConfVariables.getPath()+ORGANIZATION)
                .statusCode();

    }

    public void getEmployee(){

        SerenityRest.given()
                .header(getAuthorizationHeader(accessToken))
                .header(getContentTypeHeader())
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
                .header(getAuthorizationHeader(accessToken))
                .header(getContentTypeHeader())
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
                .header(getAuthorizationHeader(accessToken))
                .header(getContentTypeHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .body(requestParams.toJSONString())
                .put(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION + ConfVariables.getEmployeeId());
    }

}
