package workerRelatedClasses.Employee;


public class Person {
    private int id =  Integer.parseInt(String.format("%04d",(int)Math.floor(Math.random()*1000)));
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    public Person(String firstName,String lastName, String phoneNumber, String address){
        if (!newEmployeeIdCheck(id)) {
            this.id =  Integer.parseInt(String.format("%04d",(int)Math.floor(Math.random()*1000)));
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public boolean newEmployeeIdCheck(int id) {
        boolean isIdOriginal = true;
        for (Employee curemployee : EmployeeWareHouse.getEmployeeList()) {
            if (id == curemployee.getId()) {
                isIdOriginal = false;
            }
        }
        return isIdOriginal;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
