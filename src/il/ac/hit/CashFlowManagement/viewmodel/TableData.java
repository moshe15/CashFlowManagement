package il.ac.hit.CashFlowManagement.viewmodel;
import il.ac.hit.CashFlowManagement.exception.GetAllUserExpensesException;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Locale;
/**
 *
 *  The Table Data class
 */

public class TableData
{
    private static Logger logger;
    private IViewModel viewModel;
    private final String[] columnNames = { "Date", "Classification", "Details", "Cost"};

    //creating gui components
    private JTable dataTable;
    private JScrollPane tableJScrollPane;
    private DefaultTableModel model;
    private JButton back = new JButton("Back");

    /**
     * TableData C'tor
     */
    public TableData() {
        init();
    }

    /**
     * initialize table view and sorters logic
     */
    private void init() {
        setLogger();
        setViewModel();
        dataTable = new JTable(new DefaultTableModel(null, columnNames));
        dataTable.setAutoCreateRowSorter(true);
        dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        dataTable.setAutoscrolls(true);
        dataTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableJScrollPane = new JScrollPane(dataTable);
        model = (DefaultTableModel) dataTable.getModel();
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(dataTable.getModel());
        sorter.setComparator(dataTable.getColumnCount() -1 , new Comparator<String>() {
            @Override
            public int compare(String cost1, String cost2) {
                Double cost1d, cost2d;

                cost1d = Double.parseDouble(cost1);
                cost2d = Double.parseDouble(cost2);

                return cost1d.compareTo(cost2d);
            }
        });
        sorter.setComparator(0 , new Comparator<String>() {
            @Override
            public int compare(String date1, String date2) {
                LocalDate date1LD, date2LD;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault());

                date1LD = LocalDate.parse(date1, formatter);
                date2LD = LocalDate.parse(date2, formatter);

                return date1LD.compareTo(date2LD);
            }
        });
        dataTable.setRowSorter(sorter);
        dataTable.getRowSorter().toggleSortOrder(0);
        dataTable.getRowSorter().toggleSortOrder(0);
    }

    /**
     * get all expenses data from DB
     */
    private void getData() {
        Runnable run = () -> {
            // variables to contain all the information the SQL query return from DB
            ResultSet rs;
            String date;
            String cost;
            String classification;
            String details;

            logger.info("TableData.getData started");
            model.setRowCount(0);
            try
            {
                rs = viewModel.getAllExpenses();
                while (rs.next()) {
                    // final variables which would be used in the GUI creation task (GUI thread) who updates the table information
                    String finalClassification, finalDetails, finalDate, finalCost;

                    date = rs.getString("Date");
                    LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault()));
                    cost = rs.getString("Cost");
                    classification = rs.getString("Classification");
                    details = rs.getString("Details");
                    finalDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    finalCost = cost;
                    finalClassification = classification;
                    finalDetails = details;

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            Object[] row = new Object[]{finalDate, finalClassification, finalDetails, finalCost};
                            model.insertRow(dataTable.getRowCount(), row);
                        }
                    });
                }
                logger.info("TableData.getData ended successfully");
            } catch (SQLException | GetAllUserExpensesException e)
            {
                logger.info("TableData.getData failed because" + e.getMessage());
                e.printStackTrace();
            }
        };

        SwingUtilities.invokeLater(run);
    }

    /**
     * refresh table data from DB
     */
    private void refreshTable() {
        getData();
    }

    public JScrollPane getUpdatedTableJScrollPane() {
        refreshTable();
        return tableJScrollPane;
    }

    private static void setLogger() {
        TableData.logger = Logger.getLogger(TableData.class);
    }

    private void setViewModel(){
        viewModel = ManagementViewModel.getInstance();
    }
}
