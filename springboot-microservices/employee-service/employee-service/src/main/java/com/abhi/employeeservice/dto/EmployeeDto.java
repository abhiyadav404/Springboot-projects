package com.abhi.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EmployeeDto {
    private Long id;
    private  String firstName;
    private String lastName;
    private String email;
    private String departmentCode;
    private String organizationCode;
}
