package com.kainos.ea.controller;

import com.kainos.ea.dao.EmployeeDao;
import com.kainos.ea.exception.BankNumberLengthException;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.SalaryTooLowException;
import com.kainos.ea.model.EmployeeRequest;
import com.kainos.ea.model.SalesEmployee;
import com.kainos.ea.service.EmployeeService;
import com.kainos.ea.service.SalesEmployeeService;
import com.kainos.ea.validator.EmployeeValidator;
import io.swagger.annotations.Api;
import org.eclipse.jetty.http.HttpStatus;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("API for HR app")
@Path("/hr")
public class HR {
    private static EmployeeService employeeService;
    private static SalesEmployeeService salesEmployeeService;
    private static EmployeeValidator employeeValidator;

    public HR() {
        employeeService = new EmployeeService(new EmployeeDao());
        salesEmployeeService = new SalesEmployeeService();
        employeeValidator = new EmployeeValidator();
    }

    @GET
    @Path("/employee")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees() {
        try {
            return Response.ok(employeeService.getEmployees()).build();
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }

    @GET
    @Path("/employee/{employeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeById(@PathParam("employeeId") int employeeId) {
        try {
            return Response.status(HttpStatus.OK_200).entity(employeeService.getEmployee(employeeId)).build();
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }

    @GET
    @Path("/salesEmployee/{salesEmployeeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSalesEmployeeById(@PathParam("salesEmployeeId") int salesEmployeeId){
        try {
            return Response.status(HttpStatus.OK_200).entity(salesEmployeeService.getSalesEmployee(salesEmployeeId)).build();
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }

    @POST
    @Path("/employee")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEmployee(EmployeeRequest employee) throws DatabaseConnectionException, SalaryTooLowException, BankNumberLengthException {
        if (employeeValidator.isValidEmployee(employee)) {
            try {
                int id = employeeService.insertEmployee(employee);
                return Response.status(HttpStatus.CREATED_201).entity(id).build();
            } catch (Exception e) {
                System.out.println(e);
                return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
            }
        } else {
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        }
    }

    @POST
    @Path("/salesEmployee")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSalesEmployee(SalesEmployee salesEmployee) {
        try {
            salesEmployeeService.insertSalesEmployee(salesEmployee);
            return Response.status(HttpStatus.CREATED_201).build();
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }
}
