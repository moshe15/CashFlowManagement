package il.ac.hit.CashFlowManagement.view;

import il.ac.hit.CashFlowManagement.viewmodel.IViewModel;
import il.ac.hit.CashFlowManagement.viewmodel.ManagementViewModel;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Login form class
 */
public class LoginForm extends JFrame implements ActionListener, IView {
    private static Logger logger = Logger.getLogger(LoginForm.class);
    public static String username;
    private IViewModel viewModel;
    //creating gui components
    private Container container = getContentPane();
    private DesignedJLable titleLabel = new  DesignedJLable("Login");
    private DesignedJLable userLabel = new DesignedJLable("USERNAME");
    private DesignedJLable passwordLabel = new DesignedJLable("PASSWORD");
    private JTextField userTextField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private DesignedJButton loginButton = new DesignedJButton("LOGIN");
    private DesignedJButton resetButton = new DesignedJButton("RESET");
    private DesignedJButton registerButton = new DesignedJButton("REGISTER");
    private JCheckBox showPassword = new JCheckBox("Show Password");
    private JLabel lblBackground;

    /**
     * LoginForm C'tor
     */
    public LoginForm()
    {
        //Calling setLayoutManger() method inside the constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        logger.info("Login form was create successfully!!");
    }

    /**
     * @see IView {@link #showForm()}
     */
    public void showForm()
    {
        setTitle("Login Form");
        setVisible(true);
        setBounds(10, 10, 400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        logger.info(LoginForm.class+" was shown..");

        //URL image
        JLabel ImageLogo;
        ImageLogo = new JLabel(new ImageIcon("cash15.png"));
        ImageLogo.setBounds(10, 10, 400, 600);
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
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        showPassword.setBackground(Color.white);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);
        registerButton.setBounds(130, 350, 100, 30);
    }

    public void addComponentsToContainer() {
        //fill form container with gui components
        container.add(titleLabel);
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        container.add(registerButton);
    }

    public void addActionEvent() {
        //adding events listeners
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        registerButton.addActionListener(this);
        showPassword.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //handling events listeners
        if (e.getSource() == loginButton)
        {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            if (viewModel.verifyUser(userText, pwdText)) {
                username = userText;
                viewModel.showMainForm();
                closeForm(this);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        }
        if (e.getSource() == registerButton)
        {
            viewModel.showRegisterForm();
            closeForm(this);
        }
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        }
    }
}
