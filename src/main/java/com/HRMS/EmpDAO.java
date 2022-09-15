package com.HRMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class EmpDAO {
Connection con = null;
	
	//Method 1
	// METHOD TO SET CONNECTION WITH DATABASE
	public void connect()throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hrms","root","root");
	}
	
	//Method 2
	// METHOD TO ADD NEW EMPLOYEE IN DATABASE
	public int addEmployee(employee emp) throws Exception {
		 
		String query = "select * from employee where empName = '"+emp.empName+"'";
		Statement stm = con.createStatement();
		ResultSet set = stm.executeQuery(query);
		if(set.next()) {
			return -1;
		}
		else {
		String query2 = ("insert into employee(empName, empDomain, empDesignation, empLocation, empSalary, empPhone) values(?,?,?,?,?,?)");
		PreparedStatement prepstm = con.prepareStatement(query2);

		//Putting value on the index of question mark
		prepstm.setString(1,emp.empName); // PUTTING NAME ON FIRST MARK
		prepstm.setString(2,emp.empDomain);// PUTTING DOMAIN ON SECOND MARK
		prepstm.setString(3,emp.empDesignation); // PUTTING DESIGNATION ON THIRD MARK
		prepstm.setString(4,emp.empLocation); // PUTTING LOCATION NO ON FOURTH MARK
		prepstm.setInt(5,emp.empSalary); // PUTTING SALARY ON FIFTH MARKS
		prepstm.setString(6,emp.empPhone); // PUTTING PHONE NO ON SIXTH MARK
		int count = prepstm.executeUpdate();// ADDING THE EMPLOYEE DATA IN DATABASE
		
		return count;
		}
	}
	
	// METHOD 3
	// PERFORM EMPLOYEE DELETION 
	public int removeEmployee(int id) throws Exception {
		
		Statement stm = con.createStatement();
		ResultSet set = stm.executeQuery("select * from employee where empId="+id);
		
			// CHECKING IF TABLE HAS EMPLOYEE WITH GIVEN ID NO
			if(set.next()) {
				set.close();	
					Statement Deletestm = con.createStatement();
					Deletestm.executeUpdate("DELETE FROM employee WHERE empId="+id);
					return 1;
			}
			else {
				return 0;
			}
	
	}
	
	// METHOD 4
	// METHOD TO HIKE SALARY OF AN EMPLOYEE
	public int hikeSalary(int Id,int hike) throws Exception {
		Statement stm = con.createStatement();
		
		// GETTING USER DETAIL THROUGH EMPLOYEE ID
		ResultSet set = stm.executeQuery("select * from employee where empId ="+Id);
		// IF EMPLOYEE ID EXIST
		if(set.next()) {
			Statement hikeStm = con.createStatement();
			int salary = set.getInt(6);
			salary = salary+((salary*hike)/100);
			
			// UPDATING EMPLOYEE SALARY
			hikeStm.executeUpdate("update employee set empSalary = "+salary+" where empId ="+Id);
			return salary;
		}
		// IF ID NOT PRESENT IN DATABASE
		else 
			return 0;
	}
	
	// METHOD 5
	// METHOD TO HIKE SALARY OF AN EMPLOYEE
	public int hikeAll(int hike) throws Exception {
		Statement stm = con.createStatement();
		
		
		 ResultSet set = stm.executeQuery("select * from employee ");
		 set.next();
			Statement wdrawStm = con.createStatement();
			
			// UPDATING EMPLOYEE SALARY
			wdrawStm.executeUpdate("update employee set empSalary = empSalary+(empSalary*"+hike+")/100 ");
			return 1;
	}

}
