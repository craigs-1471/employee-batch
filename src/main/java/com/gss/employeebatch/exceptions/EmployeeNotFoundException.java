package com.gss.employeebatch.exceptions;

import java.text.MessageFormat;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super(MessageFormat.format("Employee {0} not found!", id));
    }
}
