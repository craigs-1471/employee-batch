package com.gss.employeebatch.model.dto;

import com.gss.employeebatch.model.Employee;
import lombok.Data;

import java.util.Objects;

@Data
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private PlainDepartmentDto plainDepartmentDto;

    public static EmployeeDto from(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        if(Objects.nonNull(employee.getDepartment())){
            employeeDto.setPlainDepartmentDto(PlainDepartmentDto.from(employee.getDepartment()));
        }
        return employeeDto;
    }
}
