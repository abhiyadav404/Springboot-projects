package com.abhi.employeeservice.service;

import com.abhi.employeeservice.dto.APIResponseDto;
import com.abhi.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponseDto getEmployeeById(Long employeeId);
}
