package il.ac.hit.CashFlowManagement.model;

import il.ac.hit.CashFlowManagement.exception.InsertToDBException;
import il.ac.hit.CashFlowManagement.exception.JDBCDataBaseLogicException;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;

/**
 * this interface define the operations that the JDBCDataBaseLogic must implement
 */
public interface IJDBCDataBaseLogic<T>
{
    /**
     * @param nameOfTable name of the table
     * @param parameters parameter to Create
     * @return true if a new table created, false if the table is already exists
     * @throws JDBCDataBaseLogicException if failed to create table because params is not even or SQLException
     */
    boolean createTableIfNotExist(@NotNull String nameOfTable, @NotNull String... parameters) throws JDBCDataBaseLogicException;

    /**
     * Remove table from DB query (DROP TABLE SQL query)
     * @param nameOfTable table name to remove, can't be null
     * @throws JDBCDataBaseLogicException if failed to remove table because of SQLException
     */
    void removeTable(@NotNull String nameOfTable) throws JDBCDataBaseLogicException;

    /**
     * Insert a values into a table in the DB
     * @param nameOfTable name of the table, can't be null
     * @param parameters row parameters to insert, can't be null
     * @throws InsertToDBException if failed to insert value to table because of SQLException
     */
    void insertValue(@NotNull String nameOfTable, String parameters) throws InsertToDBException;

    /**
     * Execute DB Query
     * @param query query to be executed, can't be null
     * @return ResultSet object that contains the results of the SQL query executing
     * @throws JDBCDataBaseLogicException if failed to execute query because of SQLException
     */
    ResultSet query(@NotNull String query) throws JDBCDataBaseLogicException;
}


