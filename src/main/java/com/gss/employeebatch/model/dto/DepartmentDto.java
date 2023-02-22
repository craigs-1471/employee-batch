package com.gss.employeebatch.model.dto;

import com.gss.employeebatch.model.Department;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DepartmentDto {
    private Long id;
    private String name;
    private String description;
    private List<EmployeeDto> employeesDto = new ArrayList<>();

    public static DepartmentDto from(Department department){
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());
        departmentDto.setDescription(department.getDescription());
        departmentDto.setEmployeesDto(department.getEmployees().stream().map(EmployeeDto::from).collect(Collectors.toList()));
        return departmentDto;
    }
}
