package orangehrm.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import orangehrm.lib.ContactDetails;
import orangehrm.lib.Employees;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactDetailsSteps {


    String employeeId;



    @Steps
    ContactDetails contactDetails;

    @And("I want to create a new employee")
    public void iWantToCreateANewEmployee() {

        Employees employee = new Employees();
        employee
                .setAuthToken()
                .createEmployee("A", "B", "C");

        employeeId = employee.employeeId();

    }


    @When("I add contact detail to employee {string}, {string}, {string}, {string}, {string}, {string}, {string}, {string}")
    public void iAddContactDetailToEmployee(String address, String city, String country, String cp, String homePhone, String mobile, String workEmail, String otherEmail) {

        contactDetails.addContactDetails(employeeId, address, city, country, cp, homePhone, mobile, workEmail, otherEmail );

    }

    @Then("the response status should be {string}")
    public void theResponseStatusShouldBe(String expectedStatus) {

        restAssuredThat(lastResponse -> lastResponse.statusCode(Integer.parseInt(expectedStatus)));

    }

    @And("the response body should be {string}")
    public void theResponseBodyShouldBe(String bodyResponse) {

        assertThat(contactDetails.bodyResponse(), equalTo(bodyResponse));

    }



}
