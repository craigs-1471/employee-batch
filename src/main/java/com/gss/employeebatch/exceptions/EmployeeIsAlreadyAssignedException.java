package com.gss.employeebatch.exceptions;

import java.text.MessageFormat;

public class EmployeeIsAlreadyAssignedException extends RuntimeException {
    public EmployeeIsAlreadyAssignedException(final Long employeeId, final Long deptId){
        super(MessageFormat.format("Employee: {0} is already assigned to dept.: {1}", employeeId, deptId));
    }
}
