package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateInformation extends JFrame implements ActionListener {

    //Name Field and meterField are declared as JLabel here
    //As we do not want to give the access to change them
    //Rather they will simply be displayed on the pane
    //And the customer can change all the other fields

    JLabel nameField, meterNumberField;
    JTextField addressField, cityField, stateField, emailField, phoneField;
    JButton updateButton, cancelButton;
    //Global variable to get the meterNumber from the login class
    //When the customer logs in and selects update information
    //The value will already be passed through the constructor
    String meterNumberValue;

    UpdateInformation(String meterNumberValue) {
        this.meterNumberValue = meterNumberValue;

        setBounds(300, 150, 1050, 450);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Update Customer Information");
        heading.setBounds(110, 0, 400, 30);
        heading.setFont(new Font("Book Antiqua", Font.BOLD, 20));
        add(heading);

        JLabel labelName = new JLabel("Name");
        labelName.setBounds(30, 70, 100, 20);
        labelName.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelName);

        this.nameField = new JLabel("");
        nameField.setBounds(230, 70, 200, 20);
        nameField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(nameField);

        JLabel labelMeterNumber = new JLabel("Meter Number");
        labelMeterNumber.setBounds(30, 110, 100, 20);
        labelMeterNumber.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelMeterNumber);

        this.meterNumberField = new JLabel("");
        meterNumberField.setBounds(230, 110, 200, 20);
        meterNumberField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(meterNumberField);

        JLabel labelAddress = new JLabel("Address");
        labelAddress.setBounds(30, 150, 100, 20);
        labelAddress.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelAddress);

        this.addressField = new JTextField();
        addressField.setBounds(230, 150, 200, 20);
        addressField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(addressField);

        JLabel labelCity = new JLabel("City");
        labelCity.setBounds(30, 190, 100, 20);
        labelCity.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelCity);

        this.cityField = new JTextField();
        cityField.setBounds(230, 190, 200, 20);
        cityField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(cityField);

        JLabel labelState = new JLabel("State");
        labelState.setBounds(30, 230, 100, 20);
        labelState.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelState);

        this.stateField = new JTextField();
        stateField.setBounds(230, 230, 200, 20);
        stateField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(stateField);

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(30, 270, 100, 20);
        labelEmail.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelEmail);

        this.emailField = new JTextField();
        emailField.setBounds(230, 270, 200, 20);
        emailField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(emailField);

        JLabel labelPhone = new JLabel("Phone");
        labelPhone.setBounds(30, 310, 100, 20);
        labelPhone.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelPhone);

        this.phoneField = new JTextField();
        phoneField.setBounds(230, 310, 200, 20);
        phoneField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(phoneField);

        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.statement.executeQuery("select * from customer where meter_no = '" +
                    this.meterNumberValue + "'");
            while (resultSet.next()) {
                nameField.setText(resultSet.getString("name"));
                meterNumberField.setText(resultSet.getString("meter_no"));
                addressField.setText(resultSet.getString("address"));
                cityField.setText(resultSet.getString("city"));
                stateField.setText(resultSet.getString("state"));
                emailField.setText(resultSet.getString("email"));
                phoneField.setText(resultSet.getString("phone"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.updateButton = new JButton("Update");
        updateButton.setBounds(70, 360, 100, 25);
        updateButton.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        updateButton.setBackground(new Color(24, 129, 251));
        updateButton.setForeground(Color.WHITE);
        updateButton.addActionListener(this);
        add(updateButton);

        this.cancelButton = new JButton("Cancel");
        cancelButton.setBounds(230, 360, 100, 25);
        cancelButton.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        cancelButton.setBackground(new Color(230, 10, 25));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(this);
        add(cancelButton);

        ImageIcon viewInformationPageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
        Image scaleViewInformationImage = viewInformationPageIcon.getImage().getScaledInstance(400,
                300, Image.SCALE_DEFAULT);
        ImageIcon viewInformationIcon = new ImageIcon(scaleViewInformationImage);
        JLabel viewInformationImage = new JLabel(viewInformationIcon);
        viewInformationImage.setBounds(550, 50, 400, 300);
        add(viewInformationImage);


        setVisible(true);

    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == updateButton) {
            String nameValue = nameField.getText();
            String updatedAddress = addressField.getText();
            String updatedCity = cityField.getText();
            String updatedState = stateField.getText();
            String updatedEmail = emailField.getText();
            String updatedPhone = phoneField.getText();

            try {
                Connect connect = new Connect();
                //The instructor had forgotten the "where" command in the sql query.
                //Due to this all the rows got affected

                connect.statement.executeUpdate("update customer set address = '" + updatedAddress +
                        "', city = '" + updatedCity + "', state = '" + updatedState + "', email = '" +
                        updatedEmail + "', phone = '" + updatedPhone + "' where meter_no = '" + meterNumberValue + "'");

                //This is not taught in the video
                //I want all the text being displayed on the project
                //In the "Book Antiqua" font and thus used ChatGPT to do the same.

                //Store the message as a Label
                JLabel messageLabel = new JLabel("User Information Updated Successfully");
                // Set the font and size of the JLabel
                messageLabel.setFont(new Font("Book Antiqua", Font.BOLD, 14));
                // Show the JOptionPane with the customized JLabel
                JOptionPane.showMessageDialog(null, messageLabel);

                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (actionEvent.getSource() == cancelButton) {
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new UpdateInformation("");
    }
}
