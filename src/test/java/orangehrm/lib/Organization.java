package orangehrm.lib;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import orangehrm.data.OrganizationInfoResponse;
import orangehrm.environment.ConfVariables;
import orangehrm.headers.Headers;
import orangehrm.oauth2.OauthToken;
import org.apache.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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

    public void compareResponse(String name, String taxId, String email, String province, String note){

        OrganizationInfoResponse organization = response.getObject("", OrganizationInfoResponse.class);;

        assertThat(organization.getData().getName(), equalTo(name));
        assertThat(organization.getData().getTaxId(), equalTo(taxId));
        assertThat(organization.getData().getEmail(), equalTo(email));
        assertThat(organization.getData().getProvince(), equalTo(province));
        assertThat(organization.getData().getNote(), equalTo(note));

    }


}
