package il.ac.hit.CashFlowManagement.model;

import il.ac.hit.CashFlowManagement.exception.GetAllUserExpensesException;
import il.ac.hit.CashFlowManagement.exception.InsertToDBException;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;

/**
 * this interface define the operations each ExpensesLogic must implement
 */
public interface IExpenseHandle {
    /**
     * add an expense to the user expenses in the DB
     * @param expense full expense information, can't be null
     * @throws InsertToDBException throws exception if failed to insert the new expenses to the DB
     */
    void addExpense(@NotNull Expense expense) throws InsertToDBException;

    /**
     * get all user expenses from the the DB
     * @return ResultSet object that contains full information about all user expenses from DB
     * @throws GetAllUserExpensesException throws exception if failed to get all users expenses
     */
    ResultSet getAllUserExpenses() throws GetAllUserExpensesException;
}