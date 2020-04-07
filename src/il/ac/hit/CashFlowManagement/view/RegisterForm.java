package il.ac.hit.CashFlowManagement.view;

import il.ac.hit.CashFlowManagement.viewmodel.IViewModel;
import il.ac.hit.CashFlowManagement.viewmodel.ManagementViewModel;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 * The register form class
 */

public class RegisterForm extends JFrame implements ActionListener, IView
{
    private static Logger logger = Logger.getLogger(RegisterForm.class);
    private IViewModel viewModel;
    //creating gui components
    private Container container = getContentPane();
    private String[] country={"","INDIA","AMERICA","AUSTRALIA","PHILIPPINES","SPAIN","ISRAEL" };
    private DesignedJLable titleLabel = new  DesignedJLable("Registration Form");
    private DesignedJLable userLabel=new  DesignedJLable("USERNAME");
    private DesignedJLable passwordLabel=new  DesignedJLable("PASSWORD");
    private DesignedJLable confirmPasswordLabel=new DesignedJLable("CONFIRM PASSWORD");
    private DesignedJLable countryLabel=new  DesignedJLable("SELECT COUNTRY");
    private DesignedJLable genderLabel=new  DesignedJLable("GENDER");
    private JCheckBox maleGender=new JCheckBox("MALE");
    private JCheckBox femaleGender=new JCheckBox("FEMALE");
    private ButtonGroup bg=new ButtonGroup();
    private JComboBox countryComboBox=new JComboBox(country);
    private DesignedJButton submitButton=new DesignedJButton("SUBMIT");
    private DesignedJButton resetButton=new DesignedJButton("RESET");
    private JTextField userTextField = new JTextField();
    private JPasswordField passwordField=new JPasswordField();
    private JPasswordField confirmPasswordField=new JPasswordField();

    /**
     * RegisterForm C'tor
     */
    public RegisterForm() {
        //Calling setLayoutManger() method inside the constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        logger.info("Register form was create successfully");
    }

    /**
     * @see IView {@link #showForm()}
     */
    public void showForm()
    {
        setTitle("Register Form");
        setVisible(true);
        getContentPane().setLayout(null);
        setBounds(40, 40, 500, 600);
        // getContentPane().setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        logger.info(RegisterForm.class+" was shown..");

        //URL image
        JLabel ImageLogo;
        ImageLogo = new JLabel(new ImageIcon("cash.jpg"));
        ImageLogo.setBounds(0, 0, 500, 600);
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

    public void setLayoutManager() {
        //Setting layout manager of Container to null
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        //set positioning, fonts and colors of gui components
        titleLabel.setBounds(170, 30, 200, 40);
        titleLabel.setFont(new Font(" SERIF", Font.BOLD, 20));
        userLabel.setBounds(100, 150, 200, 40);
        passwordLabel.setBounds(100, 200, 200, 40);
        confirmPasswordLabel.setBounds(100, 250, 200, 40);
        countryLabel.setBounds(100, 300, 200, 40);
        genderLabel.setBounds(100, 400, 200, 40);
        userTextField.setBounds(250,150,150,30);
        passwordField.setBounds(250,200,150,30);
        confirmPasswordField.setBounds(250,250,150,30);
        maleGender.setBounds(250,400,100,40);
        maleGender.setBackground(Color.LIGHT_GRAY);
        femaleGender.setBounds(350,400,100,40);
        countryComboBox.setBounds(250,300,120,40);
        countryComboBox.setBackground(Color.WHITE);
        submitButton.setBounds(100,500,120,40);
        resetButton.setBounds(250,500,120,40);
    }


       public void addComponentsToContainer() {
           //fill form container with gui components
           container.add(titleLabel);
           container.add(userLabel);
           container.add(passwordLabel);
           container.add(confirmPasswordLabel);
           container.add(countryLabel);
           container.add(genderLabel);
           container.add(userTextField);
           container.add(passwordField);
           container.add(confirmPasswordField);
           container.add(maleGender);
           container.add(femaleGender);
           bg.add(maleGender);
           bg.add(femaleGender);
           container.add(countryComboBox);
           container.add(submitButton);
           container.add(resetButton);
    }

    public void addActionEvent() {
        //adding events listeners
        submitButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //handling events listeners
        if (e.getSource() == submitButton) {
            String userName, pwd, cPwd, country, gender;
            boolean correctInformation;

            userName = userTextField.getText();
            pwd = passwordField.getText();
            cPwd = confirmPasswordField.getText();
            country = countryComboBox.getSelectedItem().toString();
            gender = maleGender.isSelected()? "Male": "Female";
            correctInformation = pwd.length() >= 4 && pwd.equalsIgnoreCase(cPwd) && !userName.equalsIgnoreCase("") && userName.chars().allMatch(Character::isLetter) && (maleGender.isSelected() || femaleGender.isSelected()) && country != "";
            if (correctInformation) {
                if(ManagementViewModel.getInstance().register(userName, pwd, country, gender)){
                    if (e.getSource() == submitButton) {
                        JOptionPane.showMessageDialog(null, "Data Registered Successfully");
                        viewModel.showLoginForm();
                        closeForm(this);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "This username is already taken");
                }
            }
            else if(userName.equalsIgnoreCase("") || !userName.chars().allMatch(Character::isLetter)){
                if(userName.equalsIgnoreCase("")){
                    JOptionPane.showMessageDialog(null, "Username can't be empty");
                }
                else{
                    JOptionPane.showMessageDialog(null, "UserName must contain only letters");
                }
            }
            else if(!(maleGender.isSelected() || femaleGender.isSelected())){
                JOptionPane.showMessageDialog(null, "Please select gender");
            }
            else if(!(country != "")){
                JOptionPane.showMessageDialog(null, "Please select country");
            }
            else {
                if(pwd.length() < 4) {
                    JOptionPane.showMessageDialog(null, "Password must include minimum 4 characters");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Password did not match");
                }
            }
        }
        else if (e.getSource() == resetButton) {
            userTextField.setText("");
            countryComboBox.setSelectedItem("");
            passwordField.setText("");
            confirmPasswordField.setText("");
        }
    }
}



