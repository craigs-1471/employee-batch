package com.gss.employeebatch.config;

import com.gss.employeebatch.model.Employee;
import com.gss.employeebatch.model.dto.PlainDepartmentDto;
import org.springframework.batch.item.ItemProcessor;

import java.util.Objects;

public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {
    @Override
    public Employee process(Employee employee) throws Exception {
        return employee;
    }
}
