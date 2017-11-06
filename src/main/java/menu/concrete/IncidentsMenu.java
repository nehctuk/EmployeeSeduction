package menu.concrete;

import menu.Menu;
import utilities.Console;
import workerRelatedClasses.Employee.Employee;
import workerRelatedClasses.Employee.EmployeeWareHouse;
import workerRelatedClasses.incidents.Incident;
import workerRelatedClasses.incidents.IncidentWareHouse;

import java.util.ArrayList;

public class IncidentsMenu extends Menu {
    public static final IncidentsMenu INSTANCE = new IncidentsMenu();

    public enum employeeUpdateEnums {CREATE, FIND, BACK, QUIT};

    public IncidentsMenu() {
        super(employeeUpdateEnums.values());
    }

    @Override
    public void menuTitle() {
        Console.print(
                "  _____            _     _            _       \n" +
                " |_   _|          (_)   | |          | |      \n" +
                "   | |  _ __   ___ _  __| | ___ _ __ | |_ ___ \n" +
                "   | | | '_ \\ / __| |/ _` |/ _ \\ '_ \\| __/ __|\n" +
                "  _| |_| | | | (__| | (_| |  __/ | | | |_\\__ \\\n" +
                " |_____|_| |_|\\___|_|\\__,_|\\___|_| |_|\\__|___/\n" +
                "                                              \n" +
                "                                              ");
        Console.print("" +
                "======================================================");
    }

    @Override
    public void userMenuSelection(String input) {

        switch (employeeUpdateEnums.valueOf(input)){
            case CREATE:
                createIncident();
                break;
            case FIND:
                findIncident();
                break;
            case BACK:
                Console.goBack(MainMenu.INSTANCE);
                break;
            case QUIT:
                Console.quitHRApp();
                break;
        }
    }

    private void createIncident() {
        String menuChoice = Console.getString("Would you like to add an employee?: 'Yes' or 'No'");
        ArrayList<Employee> employeesInvolved = new ArrayList <>();

        while("yes".equalsIgnoreCase(menuChoice)){
            int idInvolved = Console.getInt("Enter ID of employee involved: ");
            Employee employeeInvolved = EmployeeWareHouse.getEmployeeById(idInvolved);
            if(employeeInvolved == null){
                System.out.println("Employee does not exist.");
                continue;
            }
            employeesInvolved.add(employeeInvolved);
            menuChoice = Console.getString("Would you like to add another employee?: 'Yes' or 'No'");
        }

        String description = Console.getString("Please enter a description: ");
        String date = Console.getString("Please enter a date: ");

        Incident newIncident = new Incident(employeesInvolved, description, date);
        IncidentWareHouse.addIncident(newIncident);
        System.out.println("New incident ID: " + newIncident.getId());
        IncidentsMenu.INSTANCE.display();
    }

    private void findIncident(){
        int ID = Console.getInt("Enter incident ID number: ");
        Incident aIncident = IncidentWareHouse.getIncidentById(ID);
        if(aIncident == null){
            Console.print("workerRelatedClasses.incidents.Incident does not exist.\n");
            IncidentsMenu.INSTANCE.display();
        }
        IncidentActionMenu.INSTANCE.setIncident(aIncident);
        IncidentActionMenu.INSTANCE.display();
    }

    @Override
    public String toString() {
        String output =
                "1) Create\n" +
                "2) Find\n" +
                "3) Back\n" +
                "4) Quit\n";
        return output;
    }
}

