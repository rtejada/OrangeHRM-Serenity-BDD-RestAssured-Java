package orangehrm.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import orangehrm.lib.Organization;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

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

        organization.compareResponse(name, taxId, email, province, note);
    }


}
