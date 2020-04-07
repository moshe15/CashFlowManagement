package il.ac.hit.CashFlowManagement.test;

import il.ac.hit.CashFlowManagement.model.Expense;
import il.ac.hit.CashFlowManagement.model.ExpensesLogic;
import il.ac.hit.CashFlowManagement.model.JDBCDataBaseLogic;
import il.ac.hit.CashFlowManagement.view.LoginForm;
import il.ac.hit.CashFlowManagement.viewmodel.IViewModel;
import il.ac.hit.CashFlowManagement.viewmodel.ManagementViewModel;
import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Test;

import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;

public class ExpensesLogicTest {
    private JDBCDataBaseLogic dataBaseLogic = JDBCDataBaseLogic.getInstance();
    private String expenseDate = "01/01/2020", expenseClassification = "testClassification", expenseDetails = "testDetails";
    private double expenseCost = 5.55;
    private IViewModel viewModel;
    private String nameOfTable;
    private ExpensesLogic expensesLogic;
    private Expense expense = new Expense(expenseDate, expenseClassification, expenseDetails, expenseCost);

    public ExpensesLogicTest(){
        BasicConfigurator.configure();
        LoginForm.username = "testUserName";
        viewModel = ManagementViewModel.getInstance();
        nameOfTable = LoginForm.username.toUpperCase() + "_Expenses".toUpperCase();
        expensesLogic = new ExpensesLogic();
    }

    @After
    public void tearDown() throws Exception {
        dataBaseLogic.removeTable(nameOfTable);
    }

    @Test
    public void addExpense() throws Exception {
        expensesLogic.addExpense(expense);
        ResultSet rs = expensesLogic.getAllUserExpenses();
        rs.next();
        String date = rs.getString("Date");
        String cost = rs.getString("Cost");
        String classification = rs.getString("Classification");
        String details = rs.getString("Details");
        double costDouble = Double.parseDouble(cost);

        assertEquals(expenseDate, date);
        assertEquals((long)expenseCost, (long)costDouble);
        assertEquals(expenseClassification, classification);
        assertEquals(expenseDetails, details);
    }

    @Test
    public void getAllUserExpenses() throws Exception {
        int expensesCounter = 0;
        expensesLogic.addExpense(expense);
        ResultSet rs = expensesLogic.getAllUserExpenses();
        while (rs.next()){
            expensesCounter++;
        }
        assertEquals(1, expensesCounter);
    }
}
