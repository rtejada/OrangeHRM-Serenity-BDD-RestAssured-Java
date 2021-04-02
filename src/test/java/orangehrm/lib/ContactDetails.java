package orangehrm.lib;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import orangehrm.environment.ConfVariables;
import orangehrm.headers.Headers;
import orangehrm.oauth2.OauthToken;
import org.apache.http.HttpStatus;
import org.json.simple.JSONObject;

public class ContactDetails {

    static final String CONTACT_DETAIL = "/contact-detail";
    static final String ACTION = "employee/";
    private JsonPath response;
    Headers headers = new Headers();
    OauthToken oauthToken = new OauthToken();


    public void addContactDetails(String employeeId, String address, String city, String country, String cp, String homePhone,
                                  String mobile, String worEmail, String otherEmail){


        JSONObject requestParams = new JSONObject();

        requestParams.put("addressStreet1", Random.randomString(7)+ " " + address);
        requestParams.put("city", city);
        requestParams.put("country", country);
        requestParams.put("zip", Random.getRandomId()+ cp);
        requestParams.put("homeTelephone", homePhone + Random.randomInt(0, 100000000));
        requestParams.put("mobile", mobile + Random.randomInt(0, 100000000));
        requestParams.put("workEmail", Random.randomString(7) + worEmail);
        requestParams.put("otherEmail", Random.randomString(7) + otherEmail);

        response = SerenityRest.given()
                .header(headers.getAuthorizationHeader(oauthToken.getOauthToken()))
                .header(headers.getContentTypeHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .body(requestParams.toJSONString())
                .post(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION + employeeId + CONTACT_DETAIL)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().jsonPath();
    }

    public String bodyResponse(){

        return response.getString("success");

    }

}
