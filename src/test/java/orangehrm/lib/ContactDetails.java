package orangehrm.lib;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import orangehrm.environment.ConfVariables;
import orangehrm.headers.Headers;
import orangehrm.oauth2.OauthToken;
import org.json.simple.JSONObject;

public class ContactDetails {

    static final String CONTACT_DETAIL = "contact-detail";
    static final String ACTION = "employee";
    Headers headers = new Headers();
    OauthToken oauthToken = new OauthToken();


    public void addContactDetails(String id, String address, String city, String country, String cp, String homePhone, String mobile){


        JSONObject requestParams = new JSONObject();

        requestParams.put("addressStreet1", Random.randomString(7)+ " " + address);
        requestParams.put("city", city);
        requestParams.put("country", country);
        requestParams.put("zip", Random.getRandomId()+ cp);
        requestParams.put("homeTelephone", homePhone + Random.randomInt(0, 10000));
        requestParams.put("mobile", mobile + Random.randomInt(0, 100000));

        SerenityRest.given()
                .header(headers.getAuthorizationHeader(oauthToken.getOauthToken()))
                .header(headers.getContentTypeHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .body(requestParams)
                .post(ConfVariables.getUrlBase() + ConfVariables.getPath() + ACTION + id + CONTACT_DETAIL);

    }

}
