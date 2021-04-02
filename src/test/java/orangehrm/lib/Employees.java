package orangehrm.lib;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import orangehrm.environment.ConfVariables;
import orangehrm.headers.Headers;
import orangehrm.oauth2.OauthToken;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static io.restassured.RestAssured.given;

public class Employees {

    private String accessToken = "";

    static final String ACTION = "employee/";
    static final String ORGANIZATION = "organization";


    Headers headers = new Headers();
    JsonPath response;




    public Employees setAuthToken() {
        OauthToken oauth = new OauthToken();
        accessToken = oauth.getOauthToken();
        return this;
    }

    public int currentStatus() {

        setAuthToken();

        return given()
                .header(headers.getAuthorizationHeader(accessToken))
                .header(headers.getContentTypeHeader())
                .relaxedHTTPSValidation()
                .when()
                .get(ConfVariables.getUrlBase()+ConfVariables.getPath()+ORGANIZATION)
                .statusCode();

    }

    public void createEmployee(String name, String middlename, String lastname){

        JSONObject requestParams = new JSONObject();

        requestParams.put("firstName", name + Random.randomString(8));
        requestParams.put("middleName", middlename + Random.randomString(5));
        requestParams.put("lastName", lastname+ Random.randomString(7));
        requestParams.put("code", "ID"+ Random.getRandomId());


        response = SerenityRest.given()
                .header(headers.getAuthorizationHeader(accessToken))
                .header(headers.getContentTypeHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .body(requestParams.toJSONString())
                .post(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION + ConfVariables.getEmployeeId())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().jsonPath();


    }

    public String employeeId(){
        return response.getString("id");
    }


    public void updateEmployee(String otherId, String status, String nationality){

        JSONObject requestParams = new JSONObject();

        requestParams.put("firstName", DataGenerator.getRandomName());
        requestParams.put("middleName", DataGenerator.getRandomFirstName());
        requestParams.put("lastName", DataGenerator.getRandomLastName());
        requestParams.put("otherId", otherId + Random.getRandomId());
        requestParams.put("status", status);
        requestParams.put("nationality",  nationality);
        requestParams.put("dob",  "1988-05-13");
        requestParams.put("driversLicenseNumber", Random.randomInt(10, 100000000));

        SerenityRest.given()
                .header(headers.getAuthorizationHeader(accessToken))
                .header(headers.getContentTypeHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .body(requestParams.toJSONString())
                .put(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION + employeeId())
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

    public void getEmployee(){

        response = SerenityRest.given()
                .header(headers.getAuthorizationHeader(accessToken))
                .header(headers.getContentTypeHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .get(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION + employeeId())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().jsonPath();

    }





}
