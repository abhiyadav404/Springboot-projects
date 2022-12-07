package com.abhi.employeeservice.service.impl;

import com.abhi.employeeservice.dto.APIResponseDto;
import com.abhi.employeeservice.dto.DepartmentDto;
import com.abhi.employeeservice.dto.EmployeeDto;
import com.abhi.employeeservice.dto.OrganizationDto;
import com.abhi.employeeservice.entity.Employee;
import com.abhi.employeeservice.mapper.EmployeeMapper;
import com.abhi.employeeservice.repository.EmployeeRepository;
import com.abhi.employeeservice.service.APIClient;
import com.abhi.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private EmployeeRepository employeeRepository;
    private WebClient webClient;
    private APIClient apiClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto =EmployeeMapper.mapToEmployeeDto(savedEmployee);
        return  savedEmployeeDto;
    }

    @Override
  //  @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    public APIResponseDto getEmployeeById(Long employeeId) {
        LOGGER.info("inside getEmployeeById() method");
       Employee employee = employeeRepository.findById(employeeId).get();

//      ResponseEntity<DepartmentDto>responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/"+employee.getDepartmentCode(),
//               DepartmentDto.class);

      DepartmentDto departmentDto  = webClient.get()
              .uri("http://localhost:8080/api/departments/"+employee.getDepartmentCode())
              .retrieve()
              .bodyToMono(DepartmentDto.class)
              .block();

        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8083/api/organizations/"+employee.getOrganizationCode())
                .retrieve().bodyToMono(OrganizationDto.class).block();


//        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

       EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganizationDto(organizationDto);
       return apiResponseDto;
    }

    public APIResponseDto getDefaultDepartment(Long employeeId,Exception exception) {
        LOGGER.info("inside getDefaultDepartment() method");

        Employee employee = employeeRepository.findById(employeeId).get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Developement Department");

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);

        return apiResponseDto;
    }
}
