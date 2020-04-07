package il.ac.hit.CashFlowManagement.model;

import il.ac.hit.CashFlowManagement.exception.InsertToDBException;
import il.ac.hit.CashFlowManagement.exception.JDBCDataBaseException;
import il.ac.hit.CashFlowManagement.exception.JDBCDataBaseLogicException;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The  Data Base Logic class
 */
public class JDBCDataBaseLogic implements IJDBCDataBaseLogic {
    private static JDBCDataBaseLogic instance = null;
    private static Object lock = new Object();
    private static Logger logger;
    private JDBCDataBase dataBase = null;
    private Statement statement = null;
    private DatabaseMetaData metaData = null;

    private JDBCDataBaseLogic() {
        try {
            setLogger(Logger.getLogger(JDBCDataBaseLogic.class));
            setDataBase(JDBCDataBase.getInstance());
            setMetaData(dataBase.getConnection().getMetaData());
            setStatement(dataBase.getStatement());
            logger.info("DataBaseLogic was create successfully!!!");
        } catch (SQLException | JDBCDataBaseException e) {
            e.printStackTrace();
        }
    }

    /**
     * check if there is a singleton instance of DataBaseLogic and create it's if needed
     * @return instance of DataBaseLogic
     */
    public static JDBCDataBaseLogic getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new JDBCDataBaseLogic();
                }
            }
        }

        return instance;
    }

    /**
     * if the table is not exists in the DB the table would be created
     * @see IJDBCDataBaseLogic {@link #createTableIfNotExist(String, String...)}
     */
    public boolean createTableIfNotExist(@NotNull String iNameOfTable, @NotNull String... iParameters) throws JDBCDataBaseLogicException {
        boolean newTableCreated = false;

        if (iParameters.length % 2 != 0) {
            logger.info("Create Table was failed, params is not even");
            throw new JDBCDataBaseLogicException("Please enter the parameters as follows: Param1Name ,Param1Type, Param2Name ,Param2Type .... , ParamName ,ParamType");
        }

        iNameOfTable = iNameOfTable.toUpperCase();
        String str = stringsConcat(iParameters);
        try {
            ResultSet rs = metaData.getTables(null, "APP", iNameOfTable, null);
            if (!rs.next()) {
                String sqlStatement = "create table " + iNameOfTable + str;
                statement.execute(sqlStatement);
                newTableCreated = true;
                logger.info("New table created successfully, new table name is " + iNameOfTable);
            }
            else{
                newTableCreated = false;
                logger.info(iNameOfTable + " table already exist and therefor new table wasn't created");
            }
        } catch (SQLException e) {
            logger.info("New table creation failed");
            throw new JDBCDataBaseLogicException(e.getMessage());
        }
        finally {

            return newTableCreated;
        }
    }

    /**
     * @see IJDBCDataBaseLogic {@link #insertValue(String, String)}
     */
    public void insertValue(@NotNull String iNameOfTable, @NotNull String iParameters) throws InsertToDBException {
        String sqlStatement = "insert into " + iNameOfTable.toUpperCase() + " values (" + iParameters + ")";

        try {
            statement.executeUpdate(sqlStatement);
            logger.info("Parameters has been successfully inserted to " + iNameOfTable);
         }catch (SQLException e)
        {
            logger.info("Parameters insertion to " + iNameOfTable + " failed");
            throw new InsertToDBException("Parameters insertion to " + iNameOfTable + " failed", e);
        }
    }

    /**
     * @see IJDBCDataBaseLogic {@link #query(String)}
     */
    @NotNull
    public ResultSet query(@NotNull String iQuery) throws JDBCDataBaseLogicException{
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(iQuery);
        } catch (SQLException e) {
            throw new JDBCDataBaseLogicException("query execution failed " + e.getMessage(), e);
        }

        return rs;
    }

    /**
     * @see IJDBCDataBaseLogic {@link #removeTable(String)}
     */
    @NotNull
    public void removeTable(@NotNull String iNameOfTable) throws JDBCDataBaseLogicException {
        try {
            statement.execute("DROP TABLE "+iNameOfTable);
            logger.info(iNameOfTable + " successfully removed from DB");
        } catch (SQLException e) {
            logger.info("failed to remove" + iNameOfTable + " from DB");
            throw new JDBCDataBaseLogicException("failed to remove " + iNameOfTable + " from DB");
        }
    }

    /**
     * get an array or a sequence of arguments and build a string for the SQL query from it
     * @param iString an array or a sequence of arguments, can't be null
     * @return string for the SQL query
     */
    @NotNull
    private String stringsConcat(@NotNull String... iString) {
        StringBuffer stringBuffer = new StringBuffer();
        int length = iString.length - 1;

        stringBuffer.append("(");
        for (int i = 0; i < length; i += 2) {
            stringBuffer.append(iString[i]);
            stringBuffer.append(" ");
            stringBuffer.append(iString[i + 1]);
            stringBuffer.append(", ");
        }
        stringBuffer.setLength(stringBuffer.length() - 2);
        stringBuffer.append(")");

        return stringBuffer.toString();
    }

    /**
     * setter of a locking mechanism for a singleton instance of DataBaseLogic
     * @param lock static created object
     */
    public static void setLock(Object lock) {
        JDBCDataBaseLogic.lock = lock;
    }

    public void setDataBase(JDBCDataBase dataBase) {
        this.dataBase = dataBase;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public void setMetaData(DatabaseMetaData metaData) {
        this.metaData = metaData;
    }

    public static void setLogger(Logger logger) {
        JDBCDataBaseLogic.logger = logger;
    }
}