package com.gss.employeebatch.exceptions;

import java.text.MessageFormat;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(Long id) {
        super(MessageFormat.format("Department {0} not found!", id));
    }
}
