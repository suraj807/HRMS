package com.HRMS;

import java.util.Scanner;

public class EMP {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		@SuppressWarnings("unused")
		int empId,empSalary,choice = 0;
		String empName, empDomain, empDesignation, empLocation, empPhone;

		// CREATING OBJECT OF EMPLOYEE DAO
		EmpDAO EDAO = new EmpDAO();
		
		// CALLING CONNECT METHOD TO ACCESS DATABASE
		EDAO.connect();

		while(choice<5) {
			System.out.println("\nPress 1 - Add Employee \nPress 2 - Hike Salary of an Employee \nPress 3 - Hike Salary of all Employee \nPress 4 - Remove Employee \nPress 5 - Exit");
			System.out.print("ENTER CHOICE : ");
			choice = scan.nextInt();
			
			switch(choice) {
			
			// First Case
			// To Add Employee
			case 1->{ 
				employee emp = new employee();
				System.out.print("\nEnter User Name : ");
				empName = scan.next();// ASKING USER TO EMPLOYEE NAME
				emp.empName = empName;
				System.out.print("Enter Domain : ");
				empDomain = scan.next();// ASKING USER TO INPUT DOMAIN
				emp.empDomain =empDomain;
				System.out.print("Enter Desiganation : ");
				empDesignation = scan.next();// ASKING USER TO INPUT DESIGNATION
				emp.empDesignation=empDesignation;
				System.out.print("Enter Location : ");
				empLocation = scan.next();// ASKING USER TO INPUT LOCATION
				emp.empLocation = empLocation;
				System.out.print("Enter Phone no : ");
				empPhone = scan.next();// ASKING USER TO INPUT PHONE NO
				emp.empPhone = empPhone;
				System.out.print("Enter Salary : ");
				empSalary = scan.nextInt();// ASKING USER FOR SALARY
				emp.empSalary = empSalary;
				int set = EDAO.addEmployee(emp);// CALLING ADD EMPPLOYEE METHOD TO CREATE AND ADD EMPLOYEE IN DATABASE
				if (set==1)
					System.out.println("\nEmployee Added Successfully");
				else
					System.out.println("\nEmployee Already Exist!!!");			
			}
			
			// SECOND CASE
			// TO HIKE SALARY OF ONE EMPLOYEE
			case 2 ->{
				int amt,id;
				System.out.print("\nEnter Employee Id  : ");
				id = scan.nextInt();
				System.out.print("Enter Percentage for Salary Hike : ");
				amt = scan.nextInt();
				
				// CALLING HIKE SALARY METHOD TO ADD HIKE AMOUNT IN SALARY
				int ret = EDAO.hikeSalary(id,amt);
				if(ret>0)
				System.out.println("\nUpdated Salary : "+ret);	
				else
					System.out.println("\nEmployee does not Exist!!!");
			}
			
			// THIRD CASE
			// TO HIKE SALARY OF EVERY EMPLOYEE
			case 3 ->{
				System.out.print("\nEnter Percentage for Salary Hike : ");
				int amt = scan.nextInt();
				
				// CALLING HIKE ALL METHOD TO ADD HIKE AMOUNT IN SALARY OF EVERY EMPLOYEE
				int ret = EDAO.hikeAll(amt);
				if(ret==1)
					System.out.println("\nSalary Updated SuccessFully ");	
				else
					System.out.println("\nSomething went Wrong!!!!");
			}
			
			// FOURTH CASE
			// TO REMOVE EMPLOYEE FROM DATABASE
			case 4->{
				int id;
				System.out.print("\nEnter Employee Id  : ");
				id = scan.nextInt();
				
				// CALLING REMOVE EMPLOYEE METHOD TO DELETE EMPLOYEE FROM DATABASE
				int ret = EDAO.removeEmployee(id);
				if (ret==1)
					System.out.println("Employee removed Successfully.");
				else
					System.out.println("Employee does not exist!!!");
			}
			}
		}
			
		// CLOSING SCANNER OBJECT
		scan.close();	
	}

}
