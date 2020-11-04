/*
 * Workshop # 3
 * Course: JAC444 - Fall 2020
 * Last Name: Stinziani
 * First Name: Adam
 * ID: 124521188
 * Section: NDD
 * This assignment represents my own work in accordance with Seneca Academic Policy.
 * Signature: Adam Stinziani
 * Date: 2020-10-07
 */

package ca.sict.adamstinziani;

// contains logic to complete this workshop.
public class PayrollSystemTest {

    // main entry point of this workshop.
    public static void main(String[] args) {
        // exception handling
        try {
            CommissionEmployee commissionEmployee = new CommissionEmployee("John", "Doe", "111-111-111", 1000.0, 0.2);
            HourlyEmployee hourlyEmployee = new HourlyEmployee("Jane", "Smith", "222-222-222", 15.0, 35.0);
            SalariedEmployee salariedEmployee = new SalariedEmployee("Ned", "Stark", "333-333-333", 1000.0);
            BasePlusCommissionEmployee basePlusCommissionEmployee = new BasePlusCommissionEmployee("Spongebob",
                    "Square-pants",
                    "444-444-444",
                    2000,
                    0.25,
                    750.0);

            System.out.println("Employees processed individually:\n");
            System.out.printf("%s\n%s: $%,.2f\n\n", commissionEmployee, "earned", commissionEmployee.getPayableAmount());
            System.out.printf("%s\n%s: $%,.2f\n\n", hourlyEmployee, "earned", hourlyEmployee.getPayableAmount());
            System.out.printf("%s\n%s: $%,.2f\n\n", salariedEmployee, "earned", salariedEmployee.getPayableAmount());
            System.out.printf("%s\n%s: $%,.2f\n\n", basePlusCommissionEmployee, "earned", basePlusCommissionEmployee.getBaseSalary() + basePlusCommissionEmployee.getPayableAmount());

            // polymorphic array
            Employee[] employees = new Employee[]{commissionEmployee, hourlyEmployee, salariedEmployee, basePlusCommissionEmployee};

            System.out.println("Employees processed polymorphically:\n");
            for (Employee employee : employees) {
                // polymorphic check
                if (employee instanceof BasePlusCommissionEmployee)
                    System.out.printf("%s\n%s: $%,.2f\n\n", employee, "earned", ((BasePlusCommissionEmployee) employee).getBaseSalary() + employee.getPayableAmount());
                else
                    System.out.printf("%s\n%s: $%,.2f\n\n", employee, "earned", employee.getPayableAmount());
            }

            for (Employee employee : employees) {
                // Object representation of instance of class
                Object employeeClass = employee.getClass();
                System.out.printf("%s %s works as %s\n", employee.getFirstName(), employee.getLastName(), employeeClass.toString().substring(employeeClass.toString().indexOf("i.") + 2));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal argument exception: " + e);
        } catch (Exception e) {
            System.out.println("Unknown error: " + e);
        }
    }
}
