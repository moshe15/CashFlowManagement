package il.ac.hit.CashFlowManagement.model;

import org.jetbrains.annotations.NotNull;

/**
 * this interface define the operations that the UserLogic must implement
 */
public interface IUserLogic {
    /**
     * check if user exist in the Registered_Users table
     * @param iUserName the new user username, can't be null
     * @return true either false if the username exists in the DB
     */
    boolean checkIfExist(@NotNull String iUserName);

    /** validate user login information (username and password match) in the Registered_Users table
     * @param iUserName the username of the user who want to login, can't be null
     * @param iPassword the password of the user who want to login, can't be null
     * @return true either false if the login information is correct
     */
    boolean checkUserPassword(@NotNull String iUserName, @NotNull String iPassword);

    /** add user to the Registered_Users table in the DB
     * @param iUserName the new user username, can't be null
     * @param iPassword the new user password, can't be null
     * @param iCountry the new user country, can't be null
     * @param iGander the new user gander, can't be null
     */
    void addUser(@NotNull String iUserName, @NotNull String iPassword, @NotNull String iCountry, @NotNull String iGander);
}
