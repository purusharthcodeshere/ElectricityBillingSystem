package electricity.billing.system;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class SignUp extends JFrame implements ActionListener {

    JButton createButton;
    JButton backButton;
    JComboBox <String> accountTypeChoice;
    JTextField meterNumberField;
    JTextField username;
    JTextField nameField;
    JPasswordField password;

    SignUp() {
//        setSize(700, 400);
//        setLocation(400, 150);

        setBounds(400, 150, 700, 400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        Font customFont = new Font("Book Antiqua", Font.BOLD, 14);

        JPanel panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2),
                "Create-Account", TitledBorder.LEADING, TitledBorder.TOP,
                    new Font("Book Antiqua", Font.BOLD, 14),
                    new Color(0, 177, 218)));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(139, 22, 103));
//        panel.setForeground(new Color(34, 139, 34));
        panel.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        add(panel);

        JLabel heading = new JLabel("Create Account As:");
        heading.setBounds(100, 50, 140, 20);
        heading.setForeground(Color.DARK_GRAY);
        heading.setFont(customFont);
        panel.add(heading);

        this.accountTypeChoice = new JComboBox <String> ();
        accountTypeChoice.addItem("Admin");
        accountTypeChoice.addItem("Customer");
        accountTypeChoice.setBounds(260, 50, 150, 20);
        accountTypeChoice.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(accountTypeChoice);

        JLabel labelMeterNumber = new JLabel("Meter Number");
        labelMeterNumber.setBounds(100, 90, 140, 20);
        labelMeterNumber.setForeground(Color.DARK_GRAY);
        labelMeterNumber.setFont(customFont);
        labelMeterNumber.setVisible(false);
        panel.add(labelMeterNumber);

        this.meterNumberField = new JTextField();
        meterNumberField.setBounds(260, 90, 150, 20);
        meterNumberField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        meterNumberField.setVisible(false);
        panel.add(meterNumberField);

        JLabel labelUsername = new JLabel("Username");
        labelUsername.setBounds(100, 130, 140, 20);
        labelUsername.setForeground(Color.DARK_GRAY);
        labelUsername.setFont(customFont);
        panel.add(labelUsername);

        this.username = new JTextField();
        username.setBounds(260, 130, 150, 20);
        username.setFont(customFont);
        username.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(username);

        JLabel labelName = new JLabel("Name");
        labelName.setBounds(100, 170, 140, 20);
        labelName.setForeground(Color.DARK_GRAY);
        labelName.setFont(customFont);
        panel.add(labelName);

        this.nameField = new JTextField();
        nameField.setBounds(260, 170, 150, 20);
        nameField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(nameField);

        meterNumberField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {

            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                try {
                    Connect connect = new Connect();
                    ResultSet resultSet = connect.statement.executeQuery("select * from login where meter_no = '"
                            + meterNumberField.getText() + "'");
                    while (resultSet.next()) {
                        nameField.setText(resultSet.getString("name"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JLabel labelPassword = new JLabel("Password");
        labelPassword.setBounds(100, 210, 140, 20);
        labelPassword.setForeground(Color.DARK_GRAY);
        labelPassword.setFont(customFont);
        panel.add(labelPassword);

        this.password = new JPasswordField();
        password.setBounds(260, 210, 150, 20);
        password.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(password);

        accountTypeChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                String userSelected = (String) accountTypeChoice.getSelectedItem();
                assert userSelected != null;
                if (userSelected.equals("Customer")) {
                    labelMeterNumber.setVisible(true);
                    meterNumberField.setVisible(true);
                    nameField.setEditable(false);
                } else if (userSelected.equals("Admin")) {
                    labelMeterNumber.setVisible(false);
                    meterNumberField.setVisible(false);
                    nameField.setEditable(true);
                }
            }
        });

        this.createButton = new JButton("Create ");
        createButton.setBackground(new Color(24, 129, 251));
        createButton.setForeground(Color.WHITE);
        createButton.setBounds(140, 255, 110, 23);
        createButton.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        createButton.addActionListener(this);
        panel.add(createButton);

        this.backButton = new JButton("Back ");
        backButton.setBackground(new Color(24, 129, 251));
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(300, 255, 110, 23);
        backButton.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        backButton.addActionListener(this);
        panel.add(backButton);

        ImageIcon signUpPageImage = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image scaleSignUp = signUpPageImage.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon signUpIcon = new ImageIcon(scaleSignUp);
        JLabel signUpImage = new JLabel(signUpIcon);
        signUpImage.setBounds(420, 30, 250, 250);
        panel.add(signUpImage);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == createButton) {
            String accountTypeValue = (String) accountTypeChoice.getSelectedItem();
            String usernameValue = username.getText();
            String nameValue = nameField.getText();

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

            String meterValue = meterNumberField.getText();

            try {
                Connect connect = new Connect();

                String signUpQuery = null;
                if (accountTypeValue.equals("Admin")) {
                    signUpQuery = "insert into login values('" + meterValue + "', '" + usernameValue + "', '"
                            + nameValue + "', '" + passwordValue + "', '" + accountTypeValue + "')";
                } else {
                    signUpQuery = "update login set username = '" + usernameValue + "', password = '" +
                            passwordValue + "', user = '"+ accountTypeValue + "' where meter_no = '" +
                            meterValue + "'";
                }

                //Execute query through MySQL
                connect.statement.executeUpdate(signUpQuery);

                JOptionPane.showMessageDialog(null, "Account Created Successfully");

                setVisible(false);

                new Login();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == backButton) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new SignUp();
    }
}
