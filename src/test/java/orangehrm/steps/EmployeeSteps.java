package orangehrm.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import net.thucydides.core.annotations.Steps;
import orangehrm.lib.DataGenerator;
import orangehrm.lib.Employee;
import orangehrm.lib.Random;
import org.apache.http.HttpStatus;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EmployeeSteps {

    String employeeId;
    String middleName;
    String lastName;
    String dob;
    String driversLicenseNumber;

    @Steps
    Employee employee;

    @Given("the application is running")
    public void theApplicationIsRunning(){
        assertThat(employee.currentStatus()).isEqualTo(HttpStatus.SC_OK);
    }


    @When("I want to create a new employee {string}, {string}, {string}")
    public void iWantToCreateANewEmployee(String name, String middlename, String lastname) {

        name = DataGenerator.getRandomName();
        middlename = DataGenerator.getRandomFirstName();
        lastname = DataGenerator.getRandomLastName();

        employee.createEmployee(name, middlename, lastname);

    }

    @When("I want to update data employee {string}, {string}, {string}, {string}")
    public void iWantToUpdateDataEmployee(String name, String otherId, String status, String nationality){

        name = DataGenerator.getRandomUsername();
        middleName = DataGenerator.getRandomFirstName();
        lastName = DataGenerator.getRandomLastName();
        otherId = otherId + Random.randomInt(1, 1000000);
        dob = DataGenerator.getDateBirthday();
        driversLicenseNumber = "0"+(Random.randomInt(10, 100000000));

        employee.updateEmployee(name, middleName, lastName, otherId, status, nationality, dob, driversLicenseNumber);

    }

    @Then("the API should return the status {string}")
    public void theAPIShouldReturn(String expectedStatus){

        restAssuredThat(lastResponse -> lastResponse.statusCode(Integer.parseInt(expectedStatus)));

    }

    @And("the employees data has been modified")
    public void theEmployeesDataHasBeenModified() {

        JsonPath response = employee.getEmployee(employeeId);
        assertThat(response.get("data.middleName"), equalTo(middleName));
        assertThat(response.get("data.lastName"), equalTo(lastName));
        assertThat(response.get("data.dob"), equalTo(dob));

    }


    @And("I create a new employee")
    public void iCreateANewEmployee() {
        employee.setAuthToken();
        employee.createEmployee("Ana", "Pia", "Bou");
        employeeId = employee.employeeId();
    }
}
