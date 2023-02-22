package com.gss.employeebatch.service;

import com.gss.employeebatch.exceptions.DepartmentNotFoundException;
import com.gss.employeebatch.exceptions.EmployeeIsAlreadyAssignedException;
import com.gss.employeebatch.model.Department;
import com.gss.employeebatch.model.Employee;
import com.gss.employeebatch.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class DepartmentService {
    private DepartmentRepository departmentRepository;
    private EmployeeService employeeService;

    public Department addDepartment(Department department){
        return departmentRepository.save(department);
    }

    public List<Department> getDepartments(){
        return StreamSupport
                .stream(departmentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Department getDepartment(Long id){
        return departmentRepository.findById(id).orElseThrow(() ->
                new DepartmentNotFoundException(id));
    }

    public Department deleteDepartment(Long id){
        Department department = getDepartment(id);
        departmentRepository.delete(department);
        return department;
    }

    @Transactional
    public Department editDepartment(Long id, Department department){
        Department deptToEdit = getDepartment(id);
        deptToEdit.setName(department.getName());
        deptToEdit.setDescription(department.getDescription());
        return deptToEdit;
    }

    @Transactional
    public Department addEmployeeToDepartment(Long deptId, Long employeeId){
        Department department = getDepartment(deptId);
        Employee employee = employeeService.getEmployee(employeeId);
        if(Objects.nonNull(employee.getDepartment())){
            throw new EmployeeIsAlreadyAssignedException(employeeId,
                    employee.getDepartment().getId());
        }
        department.addEmployee(employee);
        employee.setDepartment(department);
        return department;
    }

    @Transactional
    public Department removeEmployeeFromDepartment(Long deptId, Long employeeId){
        Department department = getDepartment(deptId);
        Employee employee = employeeService.getEmployee(employeeId);
        department.removeEmployee(employee);
        return department;
    }

}
