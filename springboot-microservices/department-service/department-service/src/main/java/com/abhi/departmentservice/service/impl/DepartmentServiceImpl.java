package com.abhi.departmentservice.service.impl;

import com.abhi.departmentservice.dto.DepartmentDto;
import com.abhi.departmentservice.entity.Department;
import com.abhi.departmentservice.mapper.DepartmentMapper;
import com.abhi.departmentservice.repository.DepartmentRepository;
import com.abhi.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

//    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
//        this.departmentRepository = departmentRepository;
//    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapToDepartment(departmentDto);

        Department savedDepartment = departmentRepository.save(department);

        DepartmentDto savedDepartmentDto = DepartmentMapper.mapToDepartmentDto(savedDepartment);

        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
       Department department = departmentRepository.findByDepartmentCode(departmentCode);

       DepartmentDto departmentDto = DepartmentMapper.mapToDepartmentDto(department);

       return departmentDto;
    }
}
