package orangehrm.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import orangehrm.lib.Employees;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.assertj.core.api.Assertions.assertThat;

public class CheckEmployeeSteps {

    @Steps
    Employees employees;

    @Given("the application is running")
    public void the_application_is_running(){
        assertThat(employees.currentStatus()).isEqualTo(HttpStatus.SC_OK);
    }

    @When("I want to get a specific employee")
    public void i_want_to_get_a_specific_employee(){
        employees.getEmployee();
    }

    @When("I want to create a new employee {string}, {string}, {string}")
    public void i_want_to_create_a_new_employee(String name, String middlename, String lastname) {
        employees.createEmployee(name, middlename, lastname);

    }

    @When("I want to update data employee")
    public void i_want_to_update_data_employee(){
        employees.updateEmployee();

    }

    @Then("the API should return the status {string}")
    public void the_API_should_return(String expectedStatus){

        restAssuredThat(lastResponse -> lastResponse.statusCode(Integer.parseInt(expectedStatus)));

    }

}