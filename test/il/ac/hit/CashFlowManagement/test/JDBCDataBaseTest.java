package il.ac.hit.CashFlowManagement.test;

import il.ac.hit.CashFlowManagement.exception.JDBCDataBaseException;
import il.ac.hit.CashFlowManagement.model.JDBCDataBase;
import org.apache.log4j.BasicConfigurator;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class JDBCDataBaseTest {
    public JDBCDataBaseTest(){
        BasicConfigurator.configure();
    }

    @Test
    public void getInstance() {
        try {
            assertNotNull(JDBCDataBase.getInstance());
        } catch (JDBCDataBaseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getStatement() {
        try {
            assertNotNull(JDBCDataBase.getInstance().getStatement());
        } catch (JDBCDataBaseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getConnection() {
        try {
            assertNotNull(JDBCDataBase.getInstance().getConnection());
        } catch (JDBCDataBaseException e) {
            e.printStackTrace();
        }
    }

}
