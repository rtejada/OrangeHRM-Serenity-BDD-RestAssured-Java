package orangehrm.oauth2;

import orangehrm.environment.ConfVariables;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OauthToken {

    public String getOauthToken(){

        Response resp = RestAssured
                .given().relaxedHTTPSValidation()
                .auth()
                .preemptive()
                .basic(ConfVariables.getClientId(), ConfVariables.getClientSecret())
                .contentType(ContentType.URLENC)
                .when()
                .param("grant_type",ConfVariables.getGrantType())
                .post(ConfVariables.getUrlToken()).then().extract().response();


        return resp.jsonPath().getString("access_token");

    }

}
