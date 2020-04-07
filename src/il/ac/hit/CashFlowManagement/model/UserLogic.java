package il.ac.hit.CashFlowManagement.model;

import il.ac.hit.CashFlowManagement.exception.InsertToDBException;
import il.ac.hit.CashFlowManagement.exception.JDBCDataBaseLogicException;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;


/**
 * The  User Logic class
 */
public class UserLogic implements IUserLogic
{
    private static Logger logger;
    private JDBCDataBaseLogic dataBaseLogic;
    private final String nameOfTable = "Registered_Users";

    /**
     * UserLogic C'tor
     */
    public UserLogic()
    {
        setLogger(Logger.getLogger(UserLogic.class));
        setDataBaseLogic(JDBCDataBaseLogic.getInstance());
        try {
            dataBaseLogic.createTableIfNotExist(
                    nameOfTable,
                    "UserName",
                    "varchar(300)",
                    "Password",
                    "varchar(300)",
                    "Country",
                    "varchar(300)",
                    "Gander",
                    "varchar(300)"
            );
            logger.info("UserLogic was create successfully!!");
        } catch (JDBCDataBaseLogicException e) {
            logger.info("failed to create UserLogic");
            e.printStackTrace();
        }
    }

    /**
     * @see IUserLogic {@link #addUser(String iUserName, String iPassword, String iCountry, String iGander)}
     */
    public void addUser(@NotNull String iUserName, @NotNull String iPassword, @NotNull String iCountry, @NotNull String iGander) {
        String parameters =
//                "(" +
                "'" + iUserName + "'"
                + "," + "'" + iPassword + "'"
                + "," + "'" + iCountry + "'"
                + "," + "'" + iGander + "'"
//                + ")"
                ;

        try {
            dataBaseLogic.insertValue(
                    nameOfTable,
                    parameters);
            logger.info("successfully registered new user in DB");
        } catch (InsertToDBException e) {
            logger.info("failed to register new user in DB");
            e.printStackTrace();
        }
    }

    /**
     * @see IUserLogic {@link #checkIfExist(String)}
     */
    public boolean checkIfExist(@NotNull String iUserName) {
        boolean valToReturn = false;

        try {
            ResultSet rs = dataBaseLogic.query("select * from " + nameOfTable + " where " + nameOfTable + ".USERNAME ='" + iUserName + "'");

            if (rs.next()) {
                valToReturn = true;
                logger.info("user exists in DB");
            }
            else{
                logger.info("user doesn't exists in DB");
            }
        } catch (JDBCDataBaseLogicException e) {
            logger.info("checkIfExist method failed");
            e.printStackTrace();
        } finally {
            return valToReturn;
        }
    }

    /**
     * @see IUserLogic {@link #checkUserPassword(String iUserName, String iPassword)}
     */
    public boolean checkUserPassword(@NotNull String iUserName, @NotNull String iPassword) {
        boolean valToReturn = false;

        try {
            ResultSet rs = dataBaseLogic.query("select * from "+ nameOfTable +" where "+ nameOfTable +".USERNAME ='"+iUserName+"' and "+
                    nameOfTable +".PASSWORD ='"+iPassword+"'");

            if(rs.next())
            {
                valToReturn = true;
                logger.info("login information correct");
            }
            else{
                logger.info("login information is not correct or the user don't exist");
            }
        } catch (JDBCDataBaseLogicException e)
        {
            logger.info("failed to validate user login information with UserLogic.checkUserPassword method");
            e.printStackTrace();
        }
        finally {
            return valToReturn;
        }
    }

    public static void setLogger(Logger logger) {
        UserLogic.logger = logger;
    }

    public void setDataBaseLogic(JDBCDataBaseLogic dataBaseLogic) {
        this.dataBaseLogic = dataBaseLogic;
    }
}