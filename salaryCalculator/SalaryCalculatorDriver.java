/*
 * File: SalaryCalculatorDriver.java
 * Author: Alex Bailey
 * Date: 24 March 18
 * Purpose: Driver class calls FileData to read in employee data from file supplied by user.  Iterates over array
 * returned by Report class and creates Employee objects.  Calls Report class to print formatted output.
 */

import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class SalaryCalculatorDriver {
    public static void main(String[] args) {
        // vars
        String fileName;
        Map<String, Integer> nameEmployeeMap2014 = new HashMap<>();
        Map<String, Integer> nameEmployeeMap2015 = new HashMap<>();

        // create Scanner object to read file name from console
        Scanner scIn = new Scanner(System.in);
        System.out.println("What is the filename with the employee data?");
        fileName = scIn.next();

        // call FileData to read in data from fileName
        FileData fileData = new FileData(fileName);
        // iterate over array of String objects from read file
        for (String s : fileData.dataList) {
            String[] data = s.split("\\s+");  // split String object on space

            // parse data fields and create Employee objects
            if (data[1].equals("Employee")){
                if (data[0].equals("2014")) {
                    Employee temp = new Employee(data[2], Integer.parseInt(data[3]));
                    // add original String data as key, and annualSalary info to HashMap
                    nameEmployeeMap2014.put(s,temp.annualSalary());
                } else {
                    Employee temp = new Employee(data[2], Integer.parseInt(data[3]));
                    nameEmployeeMap2015.put(s,temp.annualSalary());
                }
            }
            else if (data[1].equals("Salesman")) {
                    if(data[0].equals("2014")) {
                        Salesman temp = new Salesman(data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]));
                        nameEmployeeMap2014.put(s,temp.annualSalary());
                    } else {
                        Salesman temp = new Salesman(data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]));
                        nameEmployeeMap2015.put(s,temp.annualSalary());
                    }
            }
            else {
                if (data[0].equals("2014")) {
                    Executive temp = new Executive(data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]));
                    nameEmployeeMap2014.put(s,temp.annualSalary());
                } else {
                    Executive temp = new Executive(data[2], Integer.parseInt(data[3]), Integer.parseInt(data[4]));
                    nameEmployeeMap2015.put(s,temp.annualSalary());
                }
            }
        }
        // call Report class method to print formatted output
        Report.GenerateReport(nameEmployeeMap2014, nameEmployeeMap2015);
    }
}
