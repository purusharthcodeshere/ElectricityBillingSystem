package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NewCustomer extends JFrame implements ActionListener {

    Font customFont = new Font("Book Antiqua", Font.BOLD, 14);
    JTextField nameField, addressField, cityField, stateField, emailField, phoneNumberField;
    JLabel labelMeterGenerate;
    JButton nextButton, cancelButton;

    NewCustomer() {
        setSize(700, 500);
        setLocation(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173, 216, 230));
        add(panel);

        JLabel heading = new JLabel("New Customer");
        heading.setBounds(180, 12, 200, 25);
        heading.setFont(new Font("Book Antiqua", Font.BOLD, 24));
        panel.add(heading);

        JLabel labelName = new JLabel("Customer Name");
        labelName.setBounds(100, 80, 120, 20);
        labelName.setFont(customFont);
        panel.add(labelName);

        this.nameField = new JTextField();
        nameField.setBounds(250, 80, 200, 20);
        nameField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(nameField);

        JLabel labelMeterNumber = new JLabel("Meter Number");
        labelMeterNumber.setBounds(100, 120, 120, 20);
        labelMeterNumber.setFont(customFont);
        panel.add(labelMeterNumber);

        this.labelMeterGenerate = new JLabel("");
        labelMeterGenerate.setBounds(250, 120, 100, 20);
        labelMeterGenerate.setFont(customFont);
        panel.add(labelMeterGenerate);

        Random random = new Random();
        long number = random.nextLong() % 10_000_000;
        labelMeterGenerate.setText("" + Math.abs(number));

        JLabel labelAddress = new JLabel("Address");
        labelAddress.setBounds(100, 160, 120, 20);
        labelAddress.setFont(customFont);
        panel.add(labelAddress);

        this.addressField = new JTextField();
        addressField.setBounds(250, 160, 200, 20);
        addressField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(addressField);

        JLabel labelCity = new JLabel("City");
        labelCity.setBounds(100, 200, 120, 20);
        labelCity.setFont(customFont);
        panel.add(labelCity);

        this.cityField = new JTextField();
        cityField.setBounds(250, 200, 200, 20);
        cityField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(cityField);

        JLabel labelState = new JLabel("State");
        labelState.setBounds(100, 240, 120, 20);
        labelState.setFont(customFont);
        panel.add(labelState);

        this.stateField = new JTextField();
        stateField.setBounds(250, 240, 200, 20);
        stateField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(stateField);

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(100, 280, 120, 20);
        labelEmail.setFont(customFont);
        panel.add(labelEmail);

        this.emailField = new JTextField();
        emailField.setBounds(250, 280, 200, 20);
        emailField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(emailField);

        JLabel labelPhoneNumber = new JLabel("Phone Number");
        labelPhoneNumber.setBounds(100, 320, 120, 20);
        labelPhoneNumber.setFont(customFont);
        panel.add(labelPhoneNumber);

        this.phoneNumberField = new JTextField();
        phoneNumberField.setBounds(250, 320, 200, 20);
        phoneNumberField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(phoneNumberField);

        this.nextButton = new JButton("Next");
        nextButton.setBounds(120, 390, 100, 25);
        nextButton.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        nextButton.setBackground(Color.BLACK);
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(this);
        panel.add(nextButton);

        this.cancelButton = new JButton("Cancel");
        cancelButton.setBounds(260, 390, 100, 25);
        cancelButton.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(this);
        panel.add(cancelButton);

        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon newCustomerPageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image scaleNewCustomerImage = newCustomerPageIcon.getImage().getScaledInstance(160,
                        300, Image.SCALE_DEFAULT);
        ImageIcon newCustomerIcon = new ImageIcon(scaleNewCustomerImage);
        JLabel newCustomerImage = new JLabel(newCustomerIcon);
        add(newCustomerImage, "West");

        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == nextButton) {
            String name = nameField.getText();
            String meterNumber = labelMeterGenerate.getText();
            String address = addressField.getText();
            String city = cityField.getText();
            String state = stateField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneNumberField.getText();

            String newCustomerQuery = "insert into customer values('" + name + "', '" + meterNumber +
                    "', '" + address + "', '" + city + "', '" + state + "', '" +
                    email + "', '" + phoneNumber + "')";
            String loginQuery = "insert into login values('" + meterNumber + "', '', '" + name + "', '', '')";

            try {

                Connect connect = new Connect();
                connect.statement.executeUpdate(newCustomerQuery);
                connect.statement.executeUpdate(loginQuery);

                //This is not taught in the video
                //I want all the text being displayed on the project
                //In the "Book Antiqua" font and thus use ChatGPT to do the same.

                JLabel messageLabel = new JLabel("Customer Details Added Successfully");
                // Set the font and size of the JLabel
                messageLabel.setFont(new Font("Book Antiqua", Font.BOLD, 14));
                // Show the JOptionPane with the customized JLabel
                JOptionPane.showMessageDialog(null, messageLabel);

                setVisible(false);

                //new Frame

                new MeterInformation(meterNumber);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new NewCustomer();
    }
}
