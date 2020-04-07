package il.ac.hit.CashFlowManagement.view;

import il.ac.hit.CashFlowManagement.exception.FormCastingException;
import il.ac.hit.CashFlowManagement.viewmodel.IViewModel;
import il.ac.hit.CashFlowManagement.viewmodel.ManagementViewModel;
import il.ac.hit.CashFlowManagement.viewmodel.TableData;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The main form class
 */
public class MainForm extends JFrame implements ActionListener, IView {
    private static Logger logger = Logger.getLogger(MainForm.class);
    private IViewModel viewModel;

    //creating dates scrollable strings
    private String[] months = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
    private String[] days = {"", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31"};
    private String years[]
            = {"", "1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019", "2020"};
    private String classification[] = {"", "Assets", "Clothing", "Culture", "Education", "Environment", "Entertainment", "Food", "Footwear", "Health, Absorb.& Religion"};

    //creating gui components
    private Container container = getContentPane();
    private JComboBox monthsComboBox = new JComboBox(months);
    private JComboBox daysComboBox = new JComboBox(days);
    private JComboBox yearsComboBox = new JComboBox(years);
    private JComboBox classificationComboBox = new JComboBox(classification);
    private DesignedJLable titleLabel = new DesignedJLable("Main Form");
    private DesignedJLable newExpenseLabel = new DesignedJLable("New Expense:".toUpperCase());
    private DesignedJLable dateLabel = new DesignedJLable("DATE");
    private DesignedJLable classificationLabel = new DesignedJLable("CLASSIFICATION");
    private DesignedJLable detailLabel = new DesignedJLable("DETAIL");
    private DesignedJLable costLabel = new DesignedJLable("COST");
    private DesignedJButton addBtn = new DesignedJButton("Add");
    private JTextField detailTextField = new JTextField();
    private JTextField costTextField = new JTextField();
    private DesignedJButton refreshTable = new DesignedJButton("Refresh Table");
    private JScrollPane tableJScrollPane = new JScrollPane();
    private TableData tableDataForm;

    /**
     * MainForm C'tor
     */
    public MainForm() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

        logger.info("MainForm was create successfully!!");
    }

    public JComboBox getMonthsComboBox() {
        return monthsComboBox;
    }

    public JComboBox getDaysComboBox() {
        return daysComboBox;
    }

    public JComboBox getYearsComboBox() {
        return yearsComboBox;
    }

    public JComboBox getClassificationComboBox() {
        return classificationComboBox;
    }

    public JTextField getDetailTextField() {
        return detailTextField;
    }

    public JTextField getCostTextField() {
        return costTextField;
    }

    /**
     * @see IView {@link #showForm()}
     */
    public void showForm()
    {
        tableDataForm = new TableData();
        setTitle("Main Form");
        setVisible(true);
        setBounds(300, 90, 1100, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Logged in as " + LoginForm.username);
        refreshTable();
        logger.info(MainForm.class+" was shown..");

        //URL image
        JLabel ImageLogo;
        ImageLogo = new JLabel(new ImageIcon("Cash_Management.png"));
        ImageLogo.setBounds(0, 0, 1100, 800);
        container.add(ImageLogo);
        logger.info("Create Image was Successes!!");

    }

    /**
     * @see IView {@link #setViewModel()}
     */
    @Override
    public void setViewModel(){
        viewModel = ManagementViewModel.getInstance();
    }

    public String[] getMonths(){
        return months;
    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize()
    {
        //set positioning, fonts and colors of gui components
        titleLabel.setBounds(500, 30, 200, 40);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        newExpenseLabel.setBounds(50,120, 200, 40);
        dateLabel.setBounds(50, 150, 200, 40);
        daysComboBox.setBounds(100, 160, 60, 20);
        monthsComboBox.setBounds(170, 160, 60, 20);
        yearsComboBox.setBounds(240, 160, 60, 20);
        classificationLabel.setBounds(310, 150, 200, 40);
        classificationComboBox.setBounds(410, 160, 100, 20);
        detailLabel.setBounds(520, 150, 200, 40);
        detailTextField.setBounds(570, 160, 200, 20);
        costLabel.setBounds(780, 150, 200, 40);
        costTextField.setBounds(830, 160, 100, 20);
        addBtn.setBounds(950,160, 60, 20);
        refreshTable.setBounds(450, 250, 200, 20);
    }

    public void addComponentsToContainer()
    {
        //fill form container with gui components
        container.add(titleLabel);
        container.add(newExpenseLabel);
        container.add(dateLabel);
        container.add(classificationLabel);
        container.add(detailLabel);
        container.add(costLabel);
        container.add(monthsComboBox);
        container.add(daysComboBox);
        container.add(yearsComboBox);
        container.add(classificationComboBox);
        container.add(detailTextField);
        container.add(costTextField);
        container.add(addBtn);
        container.add(refreshTable);
    }

    public void addActionEvent() {
        //adding events listeners
        addBtn.addActionListener(this);
        refreshTable.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //handling events listeners
        if (e.getSource() == addBtn) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        viewModel.addNewExpense();
                        refreshTable();
                    } catch (FormCastingException ex) {
                        ex.printStackTrace();
                    }
                }
            });
        }
        else if (e.getSource() == refreshTable) {
            refreshTable();
        }
    }

    private void refreshTable(){
        container.remove(tableJScrollPane);
        tableJScrollPane = tableDataForm.getUpdatedTableJScrollPane();
        tableJScrollPane.setBounds(50, 320, 900, 350);
        container.add(tableJScrollPane, SwingConstants.CENTER);
    }
}


