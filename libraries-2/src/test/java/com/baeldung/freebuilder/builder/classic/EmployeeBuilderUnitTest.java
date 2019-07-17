package com.baeldung.freebuilder.builder.classic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class EmployeeBuilderUnitTest {

    public static final String NAME = "baeldung";

    @Test
    public void whenBuildEmployee_thenReturnValidEmployee() {

        // when
        Employee.Builder emplBuilder = new Employee.Builder();

        Employee employee = emplBuilder
          .setName(NAME)
          .setAge(12)
          .setDepartment("Builder Pattern")
          .setDesignation("Author")
          .setEmail("abc@xyz.com")
          .setPermanent(true)
          .setSupervisorName("Admin")
          .setPhoneNumber(4445566)
          .build();

        //then
        Assertions.assertTrue(employee.getName().equalsIgnoreCase(NAME));
    }

}