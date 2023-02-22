package com.gss.employeebatch.controller;

import com.gss.employeebatch.model.Department;
import com.gss.employeebatch.model.dto.DepartmentDto;
import com.gss.employeebatch.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/departments")
@AllArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> addDepartment(@RequestBody final DepartmentDto departmentDto){
        Department department = departmentService.addDepartment(Department.from(departmentDto));
        return new ResponseEntity<>(DepartmentDto.from(department), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getDepartments(){
        List<Department> departments = departmentService.getDepartments();
        List<DepartmentDto> departmentsDto = departments.stream().map(DepartmentDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(departmentsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable final Long id){
        Department department  = departmentService.getDepartment(id);
        return new ResponseEntity<>(DepartmentDto.from(department), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<DepartmentDto> deleteDepartment(@PathVariable final Long id){
        Department department = departmentService.deleteDepartment(id);
        return new ResponseEntity<>(DepartmentDto.from(department), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<DepartmentDto> editDepartment(@PathVariable final Long id,
                                            @RequestBody final DepartmentDto departmentDto){
        Department department = departmentService.editDepartment(id, Department.from(departmentDto));
        return new ResponseEntity<>(DepartmentDto.from(department), HttpStatus.OK);
    }

    @PostMapping(value = "{departmentId}/items/{employeeId}/add")
    public ResponseEntity<DepartmentDto> addEmployeeToDepartment(@PathVariable final Long departmentId,
                                                 @PathVariable final Long employeeId){
        Department department = departmentService.addEmployeeToDepartment(departmentId, employeeId);
        return new ResponseEntity<>(DepartmentDto.from(department), HttpStatus.OK);
    }

    @DeleteMapping(value = "{departmentId}/items/{employeeId}/remove")
    public ResponseEntity<DepartmentDto> removeItemFromCart(@PathVariable final Long departmentId,
                                                      @PathVariable final Long employeeId){
        Department department = departmentService.removeEmployeeFromDepartment(departmentId, employeeId);
        return new ResponseEntity<>(DepartmentDto.from(department), HttpStatus.OK);
    }
}
