/*
 * File: Employee.java
 * Author: Alex Bailey
 * Date: 24 March 18
 * Purpose: Superclass of Executive and Salesman subclasses
 */

public class Employee {
    // instance variables
    private String name;
    private int monthlySalary;

    // constructors
    public Employee(){}

    public Employee(String name, int monthlySalary) {
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(int monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    // methods
    protected int annualSalary(){
        return monthlySalary * 12;
    }

    // returns objects' fields as a String
    public String toString(){
       String str = "Name: %s" +
               "\nMonthly Salary: %d";
       return String.format(str, name, monthlySalary);
    }
}
