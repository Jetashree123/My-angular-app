package com.SpringBootMVC.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.SpringBootMVC.Exception.ResourceNotFoundException;
import com.SpringBootMVC.Model.Employee;
import com.SpringBootMVC.Repository.EmployeeRopository;

@RequestMapping(value = "/api/v1/")
@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeRopository employeeRepository;
	
	//Get All employee details
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return employeeRepository.findAll();
	}
	
	//create employee rest API
	@RequestMapping(value="/employees", method=RequestMethod.GET)
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return employeeRepository.save(employee);
	}
	
	// get employee by id rest api
	@RequestMapping(value="/employees/{eid}", method=RequestMethod.GET)
		public ResponseEntity<Employee> getEmployeeById(@PathVariable int eid) {
			Employee employee = employeeRepository.findById(eid)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + eid));
			return ResponseEntity.ok(employee);
		}
		
		// update employee rest api
		@RequestMapping(value="/employees/{eid}", method=RequestMethod.GET)
		public ResponseEntity<Employee> updateEmployee(@PathVariable int eid, @RequestBody Employee employeeDetails){
			Employee employee = employeeRepository.findById(eid)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + eid));
			
			employee.setFirstname(employeeDetails.getFirstname());
			employee.setLastname(employeeDetails.getLastname());
			employee.setEmailid(employeeDetails.getEmailid());
			
			Employee updatedEmployee = employeeRepository.save(employee);
			return ResponseEntity.ok(updatedEmployee);
		}
		
		// delete employee rest api
		@DeleteMapping("/employees/{eid}")
		public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable int eid){
			Employee employee = employeeRepository.findById(eid)
					.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + eid));
			
			employeeRepository.delete(employee);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
		}
		
	
	
	
}
