package orangehrm.lib;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import orangehrm.environment.ConfVariables;
import orangehrm.headers.Headers;
import orangehrm.oauth2.OauthToken;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class Employee extends BaseTest {

    private String accessToken = "";

    static final String ACTION = "employee/";
    static final String ORGANIZATION = "organization";


    Headers headers = new Headers();
    JsonPath response;


    public Employee setAuthToken() {
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

        requestParams.put("firstName", name );
        requestParams.put("middleName", middlename);
        requestParams.put("lastName", lastname);
        requestParams.put("code", "ID"+ Random.randomInt(1, 10000000));


        response = SerenityRest.given(defaultRequestSpecification())
                .header(headers.getAuthorizationHeader(accessToken))
                .header(headers.getContentTypeHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .body(requestParams.toJSONString())
                .post(ACTION + ConfVariables.getEmployeeId())
                .then()
                .spec(defaultResponseSpecification())
                .extract().body().jsonPath();


    }

    public String employeeId(){
        return response.getString("id");
    }


    public void updateEmployee(String name, String middleName, String lastName, String otherId, String status,
                               String nationality, String dob, String driversLicenseNumber){

        JSONObject requestParams = new JSONObject();

        requestParams.put("firstName", name);
        requestParams.put("middleName", middleName);
        requestParams.put("lastName", lastName);
        requestParams.put("otherId", otherId);
        requestParams.put("status", status);
        requestParams.put("nationality",  nationality);
        requestParams.put("dob",  dob);
        requestParams.put("driversLicenseNumber", driversLicenseNumber);

        response = SerenityRest.given(defaultRequestSpecification())
                .header(headers.getAuthorizationHeader(accessToken))
                .header(headers.getContentTypeHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .body(requestParams.toJSONString())
                .put(ACTION + employeeId())
                .then()
                .spec(defaultResponseSpecification())
                .extract().body().jsonPath();


    }

    public JsonPath getEmployee(String employeeId){

        response = SerenityRest.given(defaultRequestSpecification())
                .header(headers.getAuthorizationHeader(accessToken))
                .header(headers.getContentTypeHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .get(ACTION + employeeId)
                .then()
                .spec(defaultResponseSpecification())
                .extract().body().jsonPath();

        return response;

    }





}
