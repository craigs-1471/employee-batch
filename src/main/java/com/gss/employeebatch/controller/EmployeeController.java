package com.gss.employeebatch.controller;

import com.gss.employeebatch.model.Employee;
import com.gss.employeebatch.model.dto.EmployeeDto;
import com.gss.employeebatch.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class EmployeeController {

    private JobLauncher jobLauncher;
    private Job job;
    private final EmployeeService employeeService;

    @PostMapping("/importemployees")
    public void importCsvToDb() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException |
                 JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody final EmployeeDto employeeDto){
        Employee employee = employeeService.addEmployee(Employee.from(employeeDto));
        return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getEmployee(){
        List<Employee> items = employeeService.getEmployees();
        List<EmployeeDto> itemsDto = items.stream().map(EmployeeDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(itemsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable final Long id){
        Employee employee = employeeService.getEmployee(id);
        return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable final Long id){
        Employee employee = employeeService.deleteEmployee(id);
        return new ResponseEntity<>(EmployeeDto.from(employee), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<EmployeeDto> editEmployee(@PathVariable final Long id,
                                            @RequestBody final EmployeeDto employeeDto){
        Employee editedEmployee = employeeService.editEmployee(id, Employee.from(employeeDto));
        return new ResponseEntity<>(EmployeeDto.from(editedEmployee), HttpStatus.OK);
    }

}
