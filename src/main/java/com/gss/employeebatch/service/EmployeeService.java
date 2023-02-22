package com.gss.employeebatch.service;

import com.gss.employeebatch.exceptions.EmployeeNotFoundException;
import com.gss.employeebatch.model.Employee;
import com.gss.employeebatch.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees(){
        return StreamSupport
                .stream(employeeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Employee getEmployee(Long id){
        return employeeRepository.findById(id).orElseThrow(() ->
                new EmployeeNotFoundException(id));
    }

    public Employee deleteEmployee(Long id){
        Employee employee = getEmployee(id);
        employeeRepository.delete(employee);
        return employee;
    }

    @Transactional
    public Employee editEmployee(Long id, Employee employee){
        Employee employeeToEdit = getEmployee(id);

        employeeToEdit.setFirstName(employee.getFirstName());
        employeeToEdit.setLastName(employee.getLastName());
        employeeToEdit.setEmail(employee.getEmail());
        employeeToEdit.setDepartment(employee.getDepartment()); ////////NOT SURE ABOUT THIS

        return employeeToEdit;
    }
}
