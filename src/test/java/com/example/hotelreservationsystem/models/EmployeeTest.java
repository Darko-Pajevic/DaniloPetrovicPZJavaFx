package com.example.hotelreservationsystem.models;

import com.example.hotelreservationsystem.models.Employee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    @Test
    public void testEmployee() {
        Employee employee = new Employee(1, "Test Employee", "Test Position", 5000.0);

        assertEquals(1, employee.getId());
        assertEquals("Test Employee", employee.getName());
        assertEquals("Test Position", employee.getPosition());
        assertEquals(5000.0, employee.getSalary());

        employee.setId(2);
        employee.setName("New Test Employee");
        employee.setPosition("New Test Position");
        employee.setSalary(6000.0);

        assertEquals(2, employee.getId());
        assertEquals("New Test Employee", employee.getName());
        assertEquals("New Test Position", employee.getPosition());
        assertEquals(6000.0, employee.getSalary());

        String expectedToString = "Employee{id=2, name='New Test Employee', position='New Test Position', salary=6000.0}";
        assertEquals(expectedToString, employee.toString());
    }
}