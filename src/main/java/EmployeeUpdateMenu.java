public class EmployeeUpdateMenu extends Menu {
    public static final EmployeeUpdateMenu INSTANCE = new EmployeeUpdateMenu();

    enum employeeUpdateEnums {FIRST_NAME, LAST_NAME, ADDRESS, PHONE_NUMBER, SALARY,
        SALARY_OR_HOURLY, BENEFITS, DEPARTMENT, POSITION, BACK, QUIT};

    private Employee selectedEmployee;

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public EmployeeUpdateMenu() {
        super(employeeUpdateEnums.values());
    }

    @Override
    public void menuTitle() {
        Console.print(
                "  ______                 _                         _    _           _       _       \n" +
                " |  ____|               | |                       | |  | |         | |     | |      \n" +
                " | |__   _ __ ___  _ __ | | ___  _   _  ___  ___  | |  | |_ __   __| | __ _| |_ ___ \n" +
                " |  __| | '_ ` _ \\| '_ \\| |/ _ \\| | | |/ _ \\/ _ \\ | |  | | '_ \\ / _` |/ _` | __/ _ \\\n" +
                " | |____| | | | | | |_) | | (_) | |_| |  __/  __/ | |__| | |_) | (_| | (_| | ||  __/\n" +
                " |______|_| |_| |_| .__/|_|\\___/ \\__, |\\___|\\___|  \\____/| .__/ \\__,_|\\__,_|\\__\\___|\n" +
                "                  | |             __/ |                  | |                        \n" +
                "                  |_|            |___/                   |_|                        ");
        Console.print("==============================================================================================");

    }

    @Override
    public void userMenuSelection(String input) {

        switch (employeeUpdateEnums.valueOf(input)){
            case FIRST_NAME:
                updateFirstName();
                break;
            case LAST_NAME:
                updateLastName();
                break;
            case ADDRESS:
                updateAddress();
                break;
            case PHONE_NUMBER:
                updatePhoneNumber();
                break;
            case SALARY:
                updateSalary();
                break;
            case SALARY_OR_HOURLY:
                updateSalaryOrHourly();
                break;
            case BENEFITS:
                updateBenefits();
                break;
            case DEPARTMENT:
                updateDepartment();
                break;
            case POSITION:
                updatePosition();
                break;
            case BACK:
                Console.goBack(EmployeeActionMenu.INSTANCE);
                break;
            case QUIT:
                Console.quitHRApp();
                break;

        }

    }

    public void updateAddress() {
         this.selectedEmployee.setAddress(Console.getString("Enter new address: "));
    }

    public void updateLastName() {
        this.selectedEmployee.setLastName(Console.getString("Enter new last name: "));
    }

    public void updateFirstName() {
        this.selectedEmployee.setFirstName(Console.getString("Enter new first name: "));
    }

    private void updatePhoneNumber(){
        this.selectedEmployee.setPhoneNumber(Console.getString("Enter new phone number: "));
    }

    private void updateSalary(){
        this.selectedEmployee.setSalary(Console.getDouble("Enter a new salary amount: "));
    }

    private void updateSalaryOrHourly(){
        String input = Console.getString("Salary or Hourly? ");
        //needs if statement to set true or false
        if("Salary".equalsIgnoreCase(input)){
            this.selectedEmployee.setIsSalary(true);
        }
        else if("Hourly".equalsIgnoreCase(input)){
            this.selectedEmployee.setIsSalary(false);
        }
        else{
            System.out.println("Error!");
        }
    }

    private void updateBenefits(){
        String input = Console.getString("Enter new benefits package(SILVER/GOLD/PLATINUM): ");

        if("silver".equalsIgnoreCase(input)){
            BenefitSilver silver = new BenefitSilver();
            this.selectedEmployee.setBenefitPackage(silver);
        }
        else if("gold".equalsIgnoreCase(input)){
            BenefitGold gold = new BenefitGold();
            this.selectedEmployee.setBenefitPackage(gold);
        }
        else if("platinum".equalsIgnoreCase(input)){
            BenefitPlat platinum = new BenefitPlat();
            this.selectedEmployee.setBenefitPackage(platinum);
        }
    }

    private void updateDepartment(){
        String input = Console.getString("Enter new department: ");

        switch (Department.valueOf(input)){
            case MANAGEMENT:
                this.selectedEmployee.setDepartment(Department.MANAGEMENT);
                break;
            case FIANCE:
                this.selectedEmployee.setDepartment(Department.FIANCE);
                break;
            case LOGISTICS:
                this.selectedEmployee.setDepartment(Department.FIANCE);
                break;
            default:
                System.out.println("Error!");
        }
    }

    private void updatePosition(){
        this.selectedEmployee.setPosition(Console.getString("Enter a new position: "));
    }

    @Override
    public String toString() {
        String output =
                "1) First Name\n" +
                "2) Last Name\n" +
                "3) Address\n" +
                "4) Phone Number\n" +
                "5) Salary\n" +
                "6) Salary or Hourly\n" +
                "7) Benefits\n" +
                "8) Department\n" +
                "9) Position\n" +
                "10) Back";
        return output;
    }
}
