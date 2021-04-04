package orangehrm.lib;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import orangehrm.environment.ConfVariables;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTest {

    //private static final Logger logger = LogManager.getLogger(ReqRestTests.class);

    @BeforeClass
    public static void setup(){

        //logger.info("Starting the configuration");

        RestAssured.requestSpecification = defaultRequestSpecification();
        //logger.info("successful configuration");

    }

    private static RequestSpecification defaultRequestSpecification() {

        List<Filter> filters = new ArrayList<>();
        filters.add(new RequestLoggingFilter());
        filters.add(new ResponseLoggingFilter());

        return new RequestSpecBuilder()
                .setBaseUri(ConfVariables.getUrlBase())
                .setBasePath(ConfVariables.getPath())
                .addFilters(filters)
                .build();
    }

    public ResponseSpecification defaultResponseSpecification() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .expectContentType(ContentType.JSON)
                .build();
    }

}
