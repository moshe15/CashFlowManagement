package il.ac.hit.CashFlowManagement.test;

import il.ac.hit.CashFlowManagement.exception.FormCastingException;
import il.ac.hit.CashFlowManagement.exception.GetAllUserExpensesException;
import il.ac.hit.CashFlowManagement.exception.JDBCDataBaseException;
import il.ac.hit.CashFlowManagement.model.Expense;
import il.ac.hit.CashFlowManagement.model.ExpensesLogic;
import il.ac.hit.CashFlowManagement.model.JDBCDataBase;
import il.ac.hit.CashFlowManagement.model.UserLogic;
import il.ac.hit.CashFlowManagement.view.LoginForm;
import il.ac.hit.CashFlowManagement.viewmodel.ManagementViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ManagementViewModelTest {
    private ManagementViewModel viewModel;
    private UserLogic userLogic = new UserLogic();
    private String userName = "testUserName", password = "testPassword", country = "testCountry", gander = "testGander", nameOfTable;
    private String expenseDate = "01/01/2020", expenseClassification = "testClassification", expenseDetails = "testDetails";
    private double expenseCost = 5.55;
    private Expense expense = new Expense(expenseDate, expenseClassification, expenseDetails, expenseCost);


    public ManagementViewModelTest(){
        LoginForm.username = "testUserName";
        viewModel = ManagementViewModel.getInstance();
        viewModel.setExpensesLogic(new ExpensesLogic());
        nameOfTable = LoginForm.username.toUpperCase() + "_Expenses".toUpperCase();
    }

    @Before
    public void setUp() {
        userLogic.addUser(userName, password, country, gander);
    }

    @After
    public void tearDown() throws JDBCDataBaseException, SQLException {
        while(userLogic.checkIfExist("userName") || userLogic.checkIfExist(userName)) {
            JDBCDataBase.getInstance().getStatement().executeUpdate("DELETE FROM Registered_Users WHERE UserName = 'testUserName'");
            JDBCDataBase.getInstance().getStatement().executeUpdate("DELETE FROM Registered_Users WHERE UserName = 'userName'");
        }
    }

    @Test
    public void getInstance() {
        assertNotNull(ManagementViewModel.getInstance());
    }

    @Test
    public void verifyUser() {
        assertEquals(true, viewModel.verifyUser(userName, password));
    }

    @Test
    public void register() {
        assertEquals(true, viewModel.register("userName", password, country, gander));
        assertEquals(false, viewModel.register(userName, password, country, gander));
    }

    @Test
    public void getAllExpenses() throws GetAllUserExpensesException, SQLException, FormCastingException {
        ResultSet rs = viewModel.getAllExpenses();
        int counter = 0;

        while (rs.next()){
            counter++;
        }
        assertEquals(0, counter);
    }
}
