package il.ac.hit.CashFlowManagement.test;

import il.ac.hit.CashFlowManagement.exception.JDBCDataBaseException;
import il.ac.hit.CashFlowManagement.model.JDBCDataBase;
import il.ac.hit.CashFlowManagement.model.UserLogic;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserLogicTest {
    UserLogic userLogic = new UserLogic();
    String userName = "testUserName", password = "testPassword", country = "testCountry", gander = "testGander";

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
    public void checkIfUserExist() throws JDBCDataBaseException, SQLException {

        assertEquals(false, userLogic.checkIfExist("userName"));
        assertTrue(userLogic.checkIfExist(userName));
    }

    @Test
    public void addUser() {
        assertEquals(false, userLogic.checkIfExist("userName"));
        userLogic.addUser("userName", password, country, gander);
        assertTrue(userLogic.checkIfExist("userName"));
    }

    @Test
    public void checkUserPassword() {
        assertEquals(true, userLogic.checkUserPassword(userName,password));
        assertEquals(false, userLogic.checkUserPassword(userName,password + "1"));
    }
}
