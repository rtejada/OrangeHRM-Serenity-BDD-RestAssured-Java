package orangehrm.lib;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import orangehrm.headers.Headers;
import orangehrm.oauth2.OauthToken;

public class Organization extends BaseTest{


    static final String ACTION = "organization";
    private JsonPath response;


    OauthToken oauthToken = new OauthToken();
    Headers headers = new Headers();



    public void getOrganizationInfo(){

        response = SerenityRest
                .given(defaultRequestSpecification())
                .header(headers.getAuthorizationHeader(oauthToken.getOauthToken()))
                .header(headers.getContentTypeHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .get( ACTION )
                .then()
                .spec(defaultResponseSpecification())
                .extract().body().jsonPath();
    }


    public JsonPath getResponse(){
        return response;
    }


}
