package orangehrm.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ContactDetailsSteps {
    @When("I add contact detail to employee {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void iAddContactDetailToEmployee(String id, String address, String city, String country, String cp, String homePhone, String mobile) {

    }

    @Then("the response status should be {string}")
    public void theResponseStatusShouldBe(String arg0) {
    }

    @And("the response body should be {string}")
    public void theResponseBodyShouldBe(String arg0) {
    }
}
