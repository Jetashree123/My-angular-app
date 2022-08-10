package com.SpringBootMVC.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringBootMVC.Model.Employee;

@Repository
public interface EmployeeRopository extends JpaRepository<Employee, Integer>{

}
