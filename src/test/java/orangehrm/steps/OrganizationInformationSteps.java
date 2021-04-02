package orangehrm.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Steps;
import orangehrm.data.OrganizationInfoResponse;
import orangehrm.lib.Organization;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OrganizationInformationSteps {

    @Steps
    Organization organization;


    @When("I get all organization information")
    public void iGetAllOrganizationInformation() {

        organization.getOrganizationInfo();


    }

    @Then("the response status should be  {string}")
    public void theResponseStatusShouldBe(String expectedStatus) {

        restAssuredThat(lastResponse -> lastResponse.statusCode(Integer.parseInt(expectedStatus)));

    }


    @And("the answer obtained should be these data {string}, {string}, {string}, {string}, {string}")
    public void TheAnswerObtainedShouldBeTheseData(String name, String taxId, String email, String province, String note) {

        JsonPath response = organization.getResponse();

        OrganizationInfoResponse organization = response.getObject("", OrganizationInfoResponse.class);;

        assertThat(organization.getData().getName(), equalTo(name));
        assertThat(organization.getData().getTaxId(), equalTo(taxId));
        assertThat(organization.getData().getEmail(), equalTo(email));
        assertThat(organization.getData().getProvince(), equalTo(province));
        assertThat(organization.getData().getNote(), equalTo(note));

    }


}
