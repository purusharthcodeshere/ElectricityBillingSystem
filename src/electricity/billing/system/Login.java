package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    JButton loginButton;
    JButton cancelButton;
    JButton signupButton;
    JTextField username;
    JPasswordField password;
    Choice loggingInAs;

    Login() {
        super("Login Page");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        //Additional part added by me,
        //We are adding the font for the labels
        //Using the Font Class to make a new object of the Font Class
        //Then passing as an argument in the setFont function

        Font customFont = new Font("Book Antiqua", Font.BOLD, 14);

        JLabel labelusername = new JLabel("Username");
        labelusername.setBounds(300, 20, 100, 20);
        labelusername.setFont(customFont);
        add(labelusername);

        this.username = new JTextField();
        username.setBounds(400, 20, 150, 20);
        username.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(username);


        JLabel labelpassword = new JLabel("Password");
        labelpassword.setBounds(300, 60, 100, 20);
        labelpassword.setFont(customFont);
        add(labelpassword);

        this.password = new JPasswordField();
        password.setBounds(400, 60, 150, 20);
        password.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(password);

        JLabel labelloginAs = new JLabel("Login As");
        labelloginAs.setBounds(300, 100, 100, 20);
        labelloginAs.setFont(customFont);
        add(labelloginAs);

        this.loggingInAs = new Choice();
        loggingInAs.add("Admin");
        loggingInAs.add("Customer");
        loggingInAs.setBounds(400, 100, 150, 20);
        loggingInAs.setFont(new Font("Book Antiqua", Font.BOLD, 12));
        add(loggingInAs);

        ImageIcon loginIcon = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image scaleLoginIcon = loginIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        this.loginButton = new JButton("LOGIN", new ImageIcon(scaleLoginIcon));
        loginButton.setBounds(330, 160, 100, 22);
        loginButton.setFont(new Font("Book Antiqua", Font.BOLD, 13));
        loginButton.addActionListener(this);
        add(loginButton);

        ImageIcon cancelIcon = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image scaleCancelIcon = cancelIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        this.cancelButton = new JButton("CANCEL", new ImageIcon(scaleCancelIcon));
        cancelButton.setBounds(450, 160, 110, 22);
        cancelButton.setFont(new Font("Book Antiqua", Font.BOLD, 13));
        cancelButton.addActionListener(this);
        add(cancelButton);

        ImageIcon signUpIcon = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image scaleSignUpIcon = signUpIcon.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        this.signupButton = new JButton("SIGN UP", new ImageIcon(scaleSignUpIcon));
        signupButton.setBounds(390, 200, 110, 22);
        signupButton.setFont(new Font("Book Antiqua", Font.BOLD, 13));
        signupButton.addActionListener(this);
        add(signupButton);

        ImageIcon loginPageImage = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image scaleLoginPageImage = loginPageImage.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon loginPageIcon = new ImageIcon(scaleLoginPageImage);
        JLabel loginImage = new JLabel(loginPageIcon);
        loginImage.setBounds(0, 0, 250, 250);
        add(loginImage);

        setSize(640, 300);
        setLocation(400, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {
            String usernameValue = username.getText();

            //password variable has been declared using the JPasswordField Class type
            //When retrieving password from an object of JPasswordFiled class type
            //We CANNOT directly retrieve it is a String datatype
            //We will have to first retrieve it as a character array
            //This is in-built in the JPasswordField for security reasons
            //And then pass the Character Array to a String variable as a parameter
            //This is not taught in the video
            //We have done this on our own through the help of the internet and ChatGPT

            //Password being retrieved as character array
            char[] passwordChars = password.getPassword();
            //Passing character array as parameter in the String object
            String passwordValue = new String(passwordChars);

            String userValue = loggingInAs.getSelectedItem();

            try {
                Connect connect = new Connect();
                String query = "select * from login where username = '" + usernameValue +
                        "' and password = '" + passwordValue + "' and user = '" + userValue + "'";

                //Execute query through MySQL
                ResultSet resultSet = connect.statement.executeQuery(query);

                if (resultSet.next()) {
                    String meterNumberValue = resultSet.getString("meter_no");
                    setVisible(false);
                    new Project(userValue, meterNumberValue);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login Details");
                    username.setText("");
                    password.setText("");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == cancelButton) {
            setVisible(false);
        } else if (ae.getSource() == signupButton) {
            setVisible(false);
            new SignUp();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
