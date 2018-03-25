/*
 * File: Report.java
 * Author: Alex Bailey
 * Date: 24 March 18
 * Purpose: Called by Driver class. Iterates over HashMap parameter and prints formatted output to the console.
 */

import java.util.Map;

public class Report {
    public static void GenerateReport(Map<String, Integer> nameEmployeeMap2014, Map<String, Integer> nameEmployeeMap2015) {
        // vars
        int avgSalary2014 = 0;
        int avgSalary2015 = 0;
        String format = "%-40s%s%n";  // formats text with 40 spaces after first string

        System.out.println("***** 2014 Salary Report *****");
        System.out.printf(format, "Original Data:", "Annual Salary:");
        // iterate over Hashmap and print key, value (original String data, annual salary)
        for (Map.Entry<String, Integer> entry : nameEmployeeMap2014.entrySet()) {
            String employeeData = entry.getKey();
            String employeeSalary = entry.getValue().toString();
            avgSalary2014 += Integer.parseInt(employeeSalary);
            printOutput(employeeData, employeeSalary);

        }

        System.out.println("\n***** 2015 Salary Report *****");
        System.out.printf(format, "Original Data:", "Annual Salary:");
        for (Map.Entry<String, Integer> entry : nameEmployeeMap2015.entrySet()) {
            String employeeData = entry.getKey();
            String employeeSalary = entry.getValue().toString();
            avgSalary2015 += Integer.parseInt(employeeSalary);
            printOutput(employeeData, employeeSalary);
        }

        // print yearly salary averages
        System.out.print("\n***** Yearly Salary Averages *****");
        System.out.println("\nAverage 2014 salary: " + avgSalary2014/nameEmployeeMap2014.size());
        System.out.println("Average 2015 salary: " + avgSalary2015/nameEmployeeMap2015.size());
    }

    public static void printOutput(String empData, String empSalary) {
        String format = "%-40s%s%n";
        System.out.printf(format, empData, empSalary);
    }
}
