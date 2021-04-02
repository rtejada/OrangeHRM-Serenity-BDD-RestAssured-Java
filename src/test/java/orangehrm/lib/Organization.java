package orangehrm.lib;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import orangehrm.environment.ConfVariables;
import orangehrm.headers.Headers;
import orangehrm.oauth2.OauthToken;
import org.apache.http.HttpStatus;

public class Organization {


    static final String ACTION = "organization";
    private JsonPath response;


    OauthToken oauthToken = new OauthToken();
    Headers headers = new Headers();



    public void getOrganizationInfo(){

        response = SerenityRest
                .given()
                .header(headers.getAuthorizationHeader(oauthToken.getOauthToken()))
                .header(headers.getContentTypeHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .get(ConfVariables.getUrlBase()+ ConfVariables.getPath() + ACTION )
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().jsonPath();
    }


    public JsonPath getResponse(){
        return response;
    }


}
