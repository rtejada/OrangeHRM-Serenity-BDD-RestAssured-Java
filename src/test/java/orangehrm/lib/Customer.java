package orangehrm.lib;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.rest.SerenityRest;
import orangehrm.environment.ConfVariables;
import orangehrm.headers.Headers;
import orangehrm.oauth2.OauthToken;
import org.json.simple.JSONObject;

import java.util.HashMap;

public class Customer extends BaseTest{


    static final String ACTION = "customer";

    Headers headers = new Headers();
    OauthToken oauth = new OauthToken();

    JsonPath response;
    String customerId;



    public void createCustomer(String name, String description){


        JSONObject requestParams = new JSONObject();

        requestParams.put("name", name );
        requestParams.put("description", description);

        response = SerenityRest.given(defaultRequestSpecification())
                .header(headers.getAuthorizationHeader(oauth.getOauthToken()))
                .header(headers.getContentTypeHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .body(requestParams.toJSONString())
                .post(ACTION)
                .then()
                .spec(defaultResponseSpecification())
                .extract().body().jsonPath();

        customerId =  response.get("customerId");


    }

    public HashMap getCustomer(){

        HashMap custom;

        response = SerenityRest.given(defaultRequestSpecification())
                .header(headers.getAuthorizationHeader(oauth.getOauthToken()))
                .header(headers.getContentTypeHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .get(ACTION)
                .then()
                .spec(defaultResponseSpecification())
                .extract().body().jsonPath();

        Integer size = response.get("data.size() - 1");

        int i;

        for (i=0; i<size; i++){

           if ( response.get("data[" + i + "].customerId").equals(customerId) ){
               break;
           }

        }
        return response.get("data[" + i + "]");

    }

    public void updateCustomerData(String name, String description){

        JSONObject requestParams = new JSONObject();

        requestParams.put("customerId", customerId );
        requestParams.put("name", name);
        requestParams.put("description", description);


        SerenityRest.given(defaultRequestSpecification())
                .header(headers.getAuthorizationHeader(oauth.getOauthToken()))
                .header(headers.getContentTypeHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .body(requestParams.toJSONString())
                .put(ACTION)
                .then()
                .spec(defaultResponseSpecification());


    }

    public void deleteCustomerData(){

        JSONObject requestParams = new JSONObject();

        requestParams.put("customerId", customerId );

        SerenityRest.given(defaultRequestSpecification())
                .header(headers.getAuthorizationHeader(oauth.getOauthToken()))
                .header(headers.getContentTypeHeader())
                .contentType(ContentType.JSON)
                .relaxedHTTPSValidation()
                .when()
                .body(requestParams.toJSONString())
                .delete(ACTION)
                .then()
                .spec(defaultResponseSpecification());
    }
}
