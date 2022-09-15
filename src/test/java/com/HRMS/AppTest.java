package com.HRMS;

import static org.junit.Assert.assertEquals;

//import junit.framework.Test;
import org.junit.jupiter.api.Test;

//import junit.framework.TestCase;
//import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest {
	// CREATING OBJECT OF  Employee DAO
	EmpDAO EDAO = new EmpDAO();

	// CREATING OBJECT OF EMPLOYEE
	employee emp = new employee();
	
	@Test
	void testAddEmployee() throws Exception {
		EDAO.connect();
		emp.empName="ketan";
		emp.empDomain="Java";
		emp.empDesignation="Developer";
		emp.empLocation="Delhi";
		emp.empPhone ="654321987";
		emp.empSalary=450000;
		
		//Testing for existed employee in data base
		assertEquals(-1,EDAO.addEmployee(emp));	
		
		emp.empName="Shubham";
		emp.empDomain="AWS";
		emp.empDesignation="Programmer";
		emp.empLocation="Delhi";
		emp.empPhone ="654321987";
		emp.empSalary=45000;
		
		//Testing for new employee in data base
		assertEquals(1,EDAO.addEmployee(emp));	
	}
	
	@Test
	void testHikeSalary() throws Exception{
		EDAO.connect();
		
		// Testing for new employee in data base
		assertEquals(49500,EDAO.hikeSalary(103,10));

		// Testing for non existing employee in data base
		assertEquals(0,EDAO.hikeSalary(111, 20));
		
	}
	
	@Test
	void testRemoveEmployee() throws Exception{

		EDAO.connect();
		
		// Testing to delete non existing employee
		assertEquals(0,EDAO.removeEmployee(122));
		
		// Testing to delete existing employee
		assertEquals(1,EDAO.removeEmployee(106));
	}

}

