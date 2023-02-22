package com.gss.employeebatch.model;

import com.gss.employeebatch.model.dto.DepartmentDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="departments")
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="department_id")
    private Long id;
    @Column(name="dept_name")
    private String name;
    @Column(name="dept_description")
    private String description;
    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "department_id")
    private List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee){
        employees.add(employee);
    }
    public void removeEmployee(Employee employee){
        employees.remove(employee);
    }
    public static Department from(DepartmentDto departmentDto){
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());
        return department;
    }
}
