package com.kainos.ea.integration;

import com.kainos.ea.WebServiceApplication;
import com.kainos.ea.WebServiceConfiguration;
import com.kainos.ea.model.Employee;
import com.kainos.ea.model.EmployeeRequest;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class HRIntegrationTest {
    private static final DropwizardAppExtension<WebServiceConfiguration> APP =
            new DropwizardAppExtension<>(WebServiceApplication.class);

    @Test
    void getEmployees_shouldReturnListOfEmployees() {
        Client client = APP.client();

        List<Employee> response = client
                .target("http://localhost:8080/hr/employee")
                .request()
                .get(List.class);

        Assertions.assertFalse(response.isEmpty());
    }

    @Test
    void getEmployee_shouldReturnEmployee() {
        Client client = APP.client();

        Response response = client
                .target("http://localhost:8080/hr/employee/1")
                .request()
                .get();

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(1, response.readEntity(Employee.class).getEmployeeId());
    }

    @Test
    void postEmployee_shouldReturnIdOfEmployee() {
        EmployeeRequest employeeRequest = new EmployeeRequest(
                30000,
                "Integration",
                "Test",
                "tbloggs@email.com",
                "1 Main Street",
                "Main Road",
                "Belfast",
                "Antrim",
                "BT99BT",
                "Northern Ireland",
                "12345678901",
                "12345678",
                "AA1A11A1A"
        );

        Client client = APP.client();

        int id = client
                .target("http://localhost:8080/hr/employee")
                .request()
                .post(Entity.json(employeeRequest))
                .readEntity(Integer.class);

        Assertions.assertTrue(id > 0);
    }

    /*
    Integration Test Exercise 1

    Write an integration test for the GET /hr/employee/{id} endpoint

    Create an employee by calling the POST /hr/employee endpoint

    Get the ID from the response of the above call

    Call the GET /hr/employee/{id} endpoint

    Expect the response values to be the same and the employee created above

    This should pass without code changes
     */
    @Test
    void getEmployeeByID_shouldReturnEmployeeMatchingProvidedID() {
        EmployeeRequest employeeRequest = new EmployeeRequest(
                30000,
                "Integration",
                "Test",
                "tbloggs@email.com",
                "1 Main Street",
                "Main Road",
                "Belfast",
                "Antrim",
                "BT99BT",
                "Northern Ireland",
                "12345678901",
                "12345678",
                "AA1A11A1A"
        );

        Client client = APP.client();

        int id = client
                .target("http://localhost:8080/hr/employee")
                .request()
                .post(Entity.json(employeeRequest))
                .readEntity(Integer.class);

        Assertions.assertTrue(id > 0);

        Response response = client
                .target("http://localhost:8080/hr/employee/" + id)
                .request()
                .get();

        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals(id, response.readEntity(Employee.class).getEmployeeId());
    }
    /*
    Integration Test Exercise 2

    Write an integration test for the POST /hr/employee method

    Call the POST /hr/employee endpoint with an employee with salary of 10000

    Expect a response with error code 400 to be returned

    This should fail, make code changes to make this test pass
     */
    @Test
    void postEmployee_shouldReturn400_whenSalaryTooLow() {
        EmployeeRequest employeeRequest = new EmployeeRequest(
                10000,
                "Integration",
                "Test",
                "tbloggs@email.com",
                "1 Main Street",
                "Main Road",
                "Belfast",
                "Antrim",
                "BT99BT",
                "Northern Ireland",
                "12345678901",
                "12345678",
                "AA1A11A1A"
        );
        Client client = APP.client();

        Response response = client
                .target("http://localhost:8080/hr/employee")
                .request()
                .post(Entity.json(employeeRequest));

        Assertions.assertEquals(400, response.getStatus());
    }
    /*
    Integration Test Exercise 3

    Write an integration test for the POST /hr/employee method

    Call the POST /hr/employee endpoint with an employee with bank number of 123

    Expect a response with error code 400 to be returned

    This should fail, make code changes to make this test pass
     */
    @Test
    void postEmployee_shouldReturn400_whenBankNumTooShort() {
        EmployeeRequest employeeRequest = new EmployeeRequest(
                25000,
                "Integration",
                "Test",
                "tbloggs@email.com",
                "1 Main Street",
                "Main Road",
                "Belfast",
                "Antrim",
                "BT99BT",
                "Northern Ireland",
                "12345678901",
                "123",
                "AA1A11A1A"
        );
        Client client = APP.client();

        Response response = client
                .target("http://localhost:8080/hr/employee")
                .request()
                .post(Entity.json(employeeRequest));

        Assertions.assertEquals(400, response.getStatus());
    }
    /*
    Integration Test Exercise 4

    Write an integration test for the GET /hr/employee/{id} endpoint

    Call the GET /hr/employee/{id} endpoint will an id of 123456

    Expect a response with error code 404 to be returned

    This should fail, make code changes to make this test pass
     */
    @Test
    void getEmployeeByID_shouldReturn404_whenEmployeeWithIDCantBeFound() {
        Client client = APP.client();

        int id = 1234567;

        Response response = client
                .target("http://localhost:8080/hr/employee/" + id)
                .request()
                .get();

        Assertions.assertEquals(404, response.getStatus());
    }
}
