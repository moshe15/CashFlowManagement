package il.ac.hit.CashFlowManagement.test;


import il.ac.hit.CashFlowManagement.exception.JDBCDataBaseLogicException;
import il.ac.hit.CashFlowManagement.model.JDBCDataBaseLogic;
import org.apache.log4j.BasicConfigurator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class JDBCDataBaseLogicTest {
    private JDBCDataBaseLogic dataBaseLogic = JDBCDataBaseLogic.getInstance();
    private final String iNameOfTable = "Test";

    public JDBCDataBaseLogicTest(){
        BasicConfigurator.configure();
    }

    @org.junit.Before
    public void setUp() throws Exception {
        dataBaseLogic.createTableIfNotExist(iNameOfTable,"Password", "varchar(300)");
    }


    @org.junit.After
    public void tearDown() throws Exception {
        dataBaseLogic.removeTable(iNameOfTable);
    }

    @org.junit.Test
    public void createTableIfNotExist() throws Exception {
        String tableName = "CreateTableTest";

        assertEquals(true,dataBaseLogic.createTableIfNotExist(tableName,"Password", "varchar(300)"));
        assertEquals(false,dataBaseLogic.createTableIfNotExist(tableName,"Password", "varchar(300)"));
        dataBaseLogic.removeTable(tableName);
        try{
            dataBaseLogic.createTableIfNotExist(iNameOfTable,"Password");
            fail("Expected exception has not been thrown");
        } catch (Exception e) {
            assertThat(e.getMessage(), is("Please enter the parameters as follows: Param1Name ,Param1Type, Param2Name ,Param2Type .... , ParamName ,ParamType"));
        }
    }

    @org.junit.Test
    public void insertValue() throws Exception {
        String params = "'" + "134" + "'";

        dataBaseLogic.insertValue(iNameOfTable,params);
        try {
            dataBaseLogic.insertValue(iNameOfTable, null);
            // If the exception is not thrown, the test will fail
            fail("Expected exception has not been thrown");
        } catch (Exception e) {
        assertThat(e.getMessage(), is("Argument for @NotNull parameter 'iParameters' of il/ac/hit/CashFlowManagement/model/JDBCDataBaseLogic.insertValue must not be null"));
        }
    }

    @org.junit.Test
    public void query() throws JDBCDataBaseLogicException {
        assertNotNull(dataBaseLogic.query("select * from " + iNameOfTable));

        try {
            assertNotNull(dataBaseLogic.query("select * from " + iNameOfTable + "1"));
            fail("Expected exception has not been thrown");
        } catch (Exception e) {
            assertThat(e.getMessage(), is("query execution failed Table/View 'TEST1' does not exist."));
        }

        try {
            dataBaseLogic.query("create table TEST (Password varchar(300))");
            fail("Expected exception has not been thrown");
        } catch (Exception e) {
            assertThat(e.getMessage(), is("query execution failed Table/View 'TEST' already exists in Schema 'APP'."));
        }
    }

    @org.junit.Test
    public void removeTable() throws JDBCDataBaseLogicException {
        String tableName = "RemoveTestTable";

        dataBaseLogic.createTableIfNotExist(tableName,"Password", "varchar(300)");
        dataBaseLogic.removeTable(tableName);

        try {
            dataBaseLogic.removeTable(tableName+"1");
            fail("Expected exception has not been thrown");
        } catch (Exception e) {
            assertThat(e.getMessage(), is("failed to remove RemoveTestTable1 from DB"));
        }
    }

    @org.junit.Test
    public void getInstance() throws JDBCDataBaseLogicException {
        assertNotNull(JDBCDataBaseLogic.getInstance());
    }

}