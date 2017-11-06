package menu.concrete;

import benefits.concrete.BenefitSilver;
import com.sun.tools.javac.code.Attribute;
import menu.Menu;
import utilities.Console;
import utilities.YesNoException;
import workerRelatedClasses.Employee.Employee;
import workerRelatedClasses.Employee.EmployeeWareHouse;
import workerRelatedClasses.department.Department;

import java.util.InputMismatchException;


public class EmployeeDirectoryMenu extends Menu {

    public static final EmployeeDirectoryMenu INSTANCE = new EmployeeDirectoryMenu();

    enum employeeDirectoryMenu {
        CREATE_EMPLOYEE,
        FIND_EMPLOYEE,
        PRINT_EMPLOYEES_BY_DEPARTMENT,
        BACK,
        QUIT
    }

    public EmployeeDirectoryMenu() {
        super(employeeDirectoryMenu.values());
    }

    @Override
    public void menuTitle() {
        Console.print(
                "  ______                 _                         _____  _               _                   \n" +
                " |  ____|               | |                       |  __ \\(_)             | |                  \n" +
                " | |__   _ __ ___  _ __ | | ___  _   _  ___  ___  | |  | |_ _ __ ___  ___| |_ ___  _ __ _   _ \n" +
                " |  __| | '_ ` _ \\| '_ \\| |/ _ \\| | | |/ _ \\/ _ \\ | |  | | | '__/ _ \\/ __| __/ _ \\| '__| | | |\n" +
                " | |____| | | | | | |_) | | (_) | |_| |  __/  __/ | |__| | | | |  __/ (__| || (_) | |  | |_| |\n" +
                " |______|_| |_| |_| .__/|_|\\___/ \\__, |\\___|\\___| |_____/|_|_|  \\___|\\___|\\__\\___/|_|   \\__, |\n" +
                "                  | |             __/ |                                                  __/ |\n" +
                "                  |_|            |___/                                                  |___/ ");
        Console.print(
                "========================================================================================================");
    }

    @Override
    public void userMenuSelection(String input) {
        switch (employeeDirectoryMenu.valueOf(input)) {
            case CREATE_EMPLOYEE:
                createEmployee();
                break;
            case FIND_EMPLOYEE:
                employeeSelection();
                break;
            case PRINT_EMPLOYEES_BY_DEPARTMENT:
                printEmployeesByDepartment();
                break;
            case BACK:
                Console.goBack(MainMenu.INSTANCE);
                break;
            case QUIT:
                Console.quitHRApp();
                break;
        }
    }

    private void printEmployeesByDepartment(){
        String output = "";
        String input = Console.getString("What department? (Management, Finance, Logistics)");
        for(Employee employee : EmployeeWareHouse.getEmployeeList()){
            if(input.equalsIgnoreCase((""
                    +employee.getDepartment()))){
                output+= "Name : " + employee.getFirstName() +" "+ employee.getLastName()+" ID: " + employee.getId()+"\n";
            }
        }
        Console.print(output);

    }

    private void askForSalary(Employee aNewEmployee) {
        double newEmployeeSalary = 0;

        do {
            try {
                newEmployeeSalary = Console.getDouble(
                        "Please enter the salary of the new employee: ");
                 break;
            } catch (InputMismatchException e) {

                Console.print("INVALID INPUT ( ° ͜ʖ͡°)╭∩╮");
                continue;
            }
        } while(true);

        aNewEmployee.setSalary(newEmployeeSalary);
    }

    private void askForSalaryOrHourly(Employee aNewEmployee) {
        String newIsEmployeeSalaryOrWage = Console.getString(
                "Is the employee Salary or Hourly? 'Salary' or 'Hourly'");
        if ("Salary".equalsIgnoreCase(newIsEmployeeSalaryOrWage)) {
            aNewEmployee.setIsSalary(true);
        }
        else if ("Hourly".equalsIgnoreCase(newIsEmployeeSalaryOrWage)) {
            aNewEmployee.setIsSalary(false);
        }
        else {
            Console.print("INVALID INPUT ( ° ͜ʖ͡°)╭∩╮");
            askForSalaryOrHourly(aNewEmployee);
        }
    }

    private void askForBenefits(Employee aNewEmployee) {
        EmployeeUpdateMenu.INSTANCE.setSelectedEmployee(aNewEmployee);
        EmployeeUpdateMenu.INSTANCE.updateBenefits();
        EmployeeUpdateMenu.INSTANCE.updateDepartment();
    }

    private void askForPosition(Employee aNewEmployee) {
        String newEmployeePosition = Console.getString("Please enter the position of the new employee.");
        aNewEmployee.setPosition(newEmployeePosition);
    }

    private Employee askForMoreEmployeeDetails(Employee aNewEmployee) {
        String addMoreInfo = Console.checkYesOrNo();

        if ("Yes".equalsIgnoreCase(addMoreInfo)) {
            askForSalary(aNewEmployee);
            askForSalaryOrHourly(aNewEmployee);
            askForBenefits(aNewEmployee);
            askForPosition(aNewEmployee);
        }
        return aNewEmployee;
    }


    private void createEmployee() {
        String newEmployeeFirstName = Console.getString("Please enter the first name of the new employee.");
        String newEmployeeLastName = Console.getString("Please enter the last name of the new employee.");
        String newEmployeePhoneNumber = Console.getString("Please enter the phone number of the new employee.");
        String newEmployeeAddress = Console.getString("Please enter the address of the new employee.");

        Employee aNewEmployee = new Employee(newEmployeeFirstName,
                newEmployeeLastName, newEmployeePhoneNumber, newEmployeeAddress);

        aNewEmployee = askForMoreEmployeeDetails(aNewEmployee);

        Console.print(aNewEmployee.toString());

        EmployeeWareHouse.addEmployee(aNewEmployee);
        EmployeeDirectoryMenu.INSTANCE.display();

    }

    private void employeeSelection() {
        EmployeeSelectionMenu.INSTANCE.display();
    }

    @Override
    public String toString() {
        String enumStrings =
                "1) Create Employee\n" +
                "2) Find Employee\n" +
                "3) Print Employees in Department\n"+
                "3) Back\n" +
                "4) Quit";
        return enumStrings;
    }

}

