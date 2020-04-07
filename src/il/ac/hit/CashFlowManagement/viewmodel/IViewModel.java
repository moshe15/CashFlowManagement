package il.ac.hit.CashFlowManagement.viewmodel;

import il.ac.hit.CashFlowManagement.exception.FormCastingException;
import il.ac.hit.CashFlowManagement.exception.GetAllUserExpensesException;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;

/**
 * this interface define the operations that each Form/Frame must implement
 */
public interface IViewModel {

    /**
     * add viewModel to all forms and Show the dialog of the LoginForm
     */
    void startProgram();

    /**
     * The method verifies that the login information (username and password) is correct
     * @param iUserName the username of the user who want to login, can't be null
     * @param iPassword the password of the user who want to login, can't be null
     * @return true either false if the login information correct
     */
    boolean verifyUser(@NotNull String iUserName, @NotNull String iPassword);

    /**
     * The method check if the user exist and if not created a new user
     * @param iUserName the new user username, can't be null
     * @param iPassword the new user password, can't be null
     * @param iCountry the new user country, can't be null
     * @param iGander the new user gander, can't be null
     * @return true if new user registered or false if the user already exists
     */
    boolean register(@NotNull String iUserName, @NotNull String iPassword, @NotNull String iCountry, @NotNull String iGander);

    /**
     * Add a new expense to the data base
     * @throws FormCastingException if failed to add new expense because of IForm to MainForm casting problem or parse from cost which is not double
     */
    void addNewExpense() throws FormCastingException;

    /**
     * Show the dialog of the MainForm
     */
    void showMainForm();

    /**
     * Show the dialog of the RegisterForm
     */
    void showRegisterForm();

    /**
     * Show the dialog of the LoginForm
     */
    void showLoginForm();

    /**
     * get all rhe expenses of a specific user
     * @return Result set that represent the table with all the expenses of a specific user
     * @throws GetAllUserExpensesException if there is a problem in getting the information
     */
    ResultSet getAllExpenses() throws GetAllUserExpensesException;
}
