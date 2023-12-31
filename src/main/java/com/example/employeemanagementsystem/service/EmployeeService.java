package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.DTO.EmployeeDTO;
import com.example.employeemanagementsystem.entity.Employee;
import com.example.employeemanagementsystem.repo.EmployeeRepo;
import com.example.employeemanagementsystem.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ModelMapper modelMapper;
    public String saveEmployee(EmployeeDTO employeeDTO){
        if(employeeRepo.existsById(employeeDTO.getEmpID())){
            return VarList.RSP_DUPLICATED;
        }else{
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;
        }
    }
    public String updateEmployee(EmployeeDTO employeeDTO){
        if(employeeRepo.existsById(employeeDTO.getEmpID())){
            employeeRepo.save(modelMapper.map(employeeDTO, Employee.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public List<EmployeeDTO> getAllEmployee(){
        List<Employee> employeeList = employeeRepo.findAll();
        return modelMapper.map(employeeList, new TypeToken<ArrayList<EmployeeDTO>>(){}.getType());
    }
}

