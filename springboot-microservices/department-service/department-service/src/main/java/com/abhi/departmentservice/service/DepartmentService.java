package com.abhi.departmentservice.service;

import com.abhi.departmentservice.dto.DepartmentDto;

public interface DepartmentService {
     DepartmentDto saveDepartment(DepartmentDto departmentDto);
     DepartmentDto getDepartmentByCode(String code);
}
