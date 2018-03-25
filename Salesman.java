/*
 * File: Salesman.java
 * Author: Alex Bailey
 * Date: 24 March 18
 * Purpose: Subclass of Employee
 */

public class Salesman extends Employee {
    // instance variables
    private int annualSales;

    // constructors
    public Salesman(){}

    public Salesman(String name, int monthlySalary, int annualSales) {
        super(name, monthlySalary);
        this.annualSales = annualSales;
    }

    // getters and setters
    public int getAnnualSales() {
        return annualSales;
    }

    public void setAnnualSales(int annualSales) {
        this.annualSales = annualSales;
    }

    // methods
    protected int annualSalary(){
        int annualSalary = getMonthlySalary() * 12;
        double commission = .02 * annualSales;
        if(commission > 20000){
            return annualSalary + 20000;
        }
        return annualSalary + (int) commission;
    }

    // returns objects' fields as a String
    public String toString() {
        String str = "\n" + super.toString() +
                "\nAnnual Sales: %d";
        return String.format(str, annualSales);
    }
}
