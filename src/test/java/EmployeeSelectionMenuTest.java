import menu.concrete.EmployeeSelectionMenu;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import workerRelatedClasses.Employee.Employee;
import workerRelatedClasses.Employee.EmployeeWareHouse;

public class EmployeeSelectionMenuTest {
    Employee expected;

    @Before
    public void setUp() throws Exception {
        expected = new Employee("Andrew", "Kutchen", "123", "Home");
        EmployeeWareHouse.addEmployee(expected);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void checkEmployeeExistence_NotNull_ById() throws Exception {
        Employee actual = EmployeeSelectionMenu.INSTANCE.checkEmployeeExistence(expected, "Id number");
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void checkEmployeeExistence_NotNull_ByLastName() throws Exception {
        Employee actual = EmployeeSelectionMenu.INSTANCE.checkEmployeeExistence(expected, "last name");
        Assert.assertEquals(expected,actual);
    }
}