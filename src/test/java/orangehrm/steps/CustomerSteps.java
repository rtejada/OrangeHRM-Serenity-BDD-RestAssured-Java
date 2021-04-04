package orangehrm.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import orangehrm.lib.Customer;
import orangehrm.lib.DataGenerator;

import java.util.HashMap;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CustomerSteps {

    String name;
    String description;
    String customerId;

    @Steps
    Customer customer;

    @When("I want to create a new customer")
    public void iWantToCreateANewCustomer() {
        name = DataGenerator.getRandomFirstName();
        description = DataGenerator.getRandomDescription();

        customer.createCustomer(name, description);

    }

    @Then("confirm what the status is {string}")
    public void confirmWhatTheStatusIs(String expectedStatus) {

        restAssuredThat(lastResponse -> lastResponse.statusCode(Integer.parseInt(expectedStatus)));

    }

    @And("confirm that the data have been inserted")
    public void confirmThatTheDataHaveBeenInserted() {

        HashMap response = customer.getCustomer();

        assertThat(response.get("name"), equalTo(name));
        assertThat(response.get("description"), equalTo(description));
        assertThat(response.get("isDeleted"), equalTo("0"));

    }

    @When("I want to update data customer")
    public void iWantToUpdateDataCustomer() {
        name = DataGenerator.getRandomFirstName();
        description = DataGenerator.getRandomDescription();

        customer.createCustomer(name, description);

        name = DataGenerator.getRandomFirstName();
        description = DataGenerator.getRandomDescription();

        customer.updateCustomerData(name, description);

    }

    @And("confirm that the data have been updated")
    public void confirmThatTheDataHaveBeenUpdated() {

        HashMap response = customer.getCustomer();

        assertThat(response.get("name"), equalTo(name));
        assertThat(response.get("description"), equalTo(description));
        assertThat(response.get("isDeleted"), equalTo("0"));



    }

    @When("I want to delete data customer")
    public void iWantToDeleteDataCustomer() {

        name = DataGenerator.getRandomFirstName();
        description = DataGenerator.getRandomDescription();

        customer.createCustomer(name, description);

    }

    @And("confirm that the data have been deleted")
    public void confirmThatTheDataHaveBeenDeleted() {
    }
}
