package com.gss.employeebatch.model.dto;

import com.gss.employeebatch.model.Department;
import lombok.Data;

@Data
public class PlainDepartmentDto {
    private Long id;
    private String deptName;
    private String deptDescription;

    public static PlainDepartmentDto from(Department department){
        PlainDepartmentDto plainCartDto = new PlainDepartmentDto();
        plainCartDto.setId(plainCartDto.getId());
        plainCartDto.setDeptName(plainCartDto.getDeptName());
        plainCartDto.setDeptDescription(plainCartDto.getDeptDescription());
        return plainCartDto;
    }
}
