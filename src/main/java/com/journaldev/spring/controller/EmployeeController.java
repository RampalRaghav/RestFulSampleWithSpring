package com.journaldev.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.journaldev.spring.model.Employee;

@RestController
public class EmployeeController {
	
	Map<Integer, Employee> employeeMap = new HashMap<Integer, Employee>();
	
	@RequestMapping(value = Constants.DUMMY_EMP, method = RequestMethod.GET)
	@ResponseBody 
	public Employee getTestEmployee() {
		System.out.println("-----------------Inside getDummyEmployee");
		
		Employee emp = new Employee();
		emp.setId(0);
		emp.setName("Test");
		employeeMap.put(emp.getId(), emp);
		
		return emp;
	}
	
	@RequestMapping(value = Constants.GET_EMP, method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable("id") int empId) throws Exception {
		System.out.println("--------------Inside getEmployee for Id: " + empId);
		/*if(empId == 00){
			throw new SQLException("SQLException, id= " +empId);
		} */
		return employeeMap.get(empId);
	}
	
	@RequestMapping(value = Constants.GET_ALL_EMP, method = RequestMethod.GET)
	public List<Employee> getAllEmployees() {
		System.out.println("--------------Inside getAllEmployees");
		List<Employee> emps = new ArrayList<Employee>();
		
		for(Integer i : employeeMap.keySet()){
			emps.add(employeeMap.get(i));
		}
		
		return emps;
	}
	
	@RequestMapping(value = Constants.CREATE_EMP, method = RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee emp) {
		System.out.println("---------------Inside createEmployee for Id: " + emp.getId());
		
		//to check if duplicate entry is there
		if(employeeMap.containsKey(emp.getId())) {
			System.out.println("Employee already exists with Id: " + emp.getId());
		}else {
			employeeMap.put(emp.getId(), emp);
		}
		
		return emp;
	}
	
	@RequestMapping(value = Constants.CREATE_MULT_EMP, method = RequestMethod.POST)
	public List<Employee> createEmployees(@RequestBody List<Employee> empList) {
		System.out.println("---------------Inside createEmployee list");
		
		for(Employee emp : empList){
			//to check if duplicate entry is there
			if(employeeMap.containsKey(emp.getId())) {
				System.out.println("Employee already exists with Id: " + emp.getId());
			}else {
				employeeMap.put(emp.getId(), emp);
			}
		}
		
		return empList;
	}
	
	@RequestMapping(value = Constants.DELETE_EMP, method = RequestMethod.DELETE)
	public Employee deleteEmployee(@PathVariable("id") int empId) {
		System.out.println("--------------Inside deleteEmployee for Id: " + empId);
		
		Employee emp = employeeMap.get(empId);
		employeeMap.remove(empId);
		
		return emp;
	}
	
}
