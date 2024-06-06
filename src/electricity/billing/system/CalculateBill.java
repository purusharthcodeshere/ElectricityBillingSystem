package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class CalculateBill extends JFrame implements ActionListener {

    Font customFont = new Font("Book Antiqua", Font.BOLD, 14);
    JTextField unitsConsumedField;
    JLabel labelNameField, labelAddressField;
    JButton submitButton, cancelButton;
    JComboBox <String> meterNumberField, monthChoice;

    CalculateBill() {
        setSize(700, 500);
        setLocation(400, 160);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173, 216, 230));
        add(panel);

        JLabel heading = new JLabel("Calculate Electricity Bill");
        heading.setBounds(140, 12, 400, 30);
        heading.setFont(new Font("Book Antiqua", Font.BOLD, 24));
        panel.add(heading);

        JLabel labelMeterNumber = new JLabel("Meter Number");
        labelMeterNumber.setBounds(100, 80, 120, 22);
        labelMeterNumber.setFont(customFont);
        panel.add(labelMeterNumber);

        this.meterNumberField = new JComboBox <> ();

        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.statement.executeQuery("select * from customer");
            while (resultSet.next()) {
                meterNumberField.addItem(resultSet.getString("meter_no"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        meterNumberField.setBounds(250, 80, 200, 22);
        meterNumberField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(meterNumberField);

        JLabel labelName = new JLabel("Customer Name");
        labelName.setBounds(100, 120, 120, 22);
        labelName.setFont(customFont);
        panel.add(labelName);

        this.labelNameField = new JLabel("");
        labelNameField.setBounds(250, 120, 200, 22);
        labelNameField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(labelNameField);

        JLabel labelAddress = new JLabel("Address");
        labelAddress.setBounds(100, 160, 120, 22);
        labelAddress.setFont(customFont);
        panel.add(labelAddress);

        this.labelAddressField = new JLabel();
        labelAddressField.setBounds(250, 160, 200, 22);
        labelAddressField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(labelAddressField);

        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.statement.executeQuery("select * from customer where meter_no = '"
                    + meterNumberField.getSelectedItem() + "'");
            while (resultSet.next()) {
                labelNameField.setText(resultSet.getString("name"));
                labelAddressField.setText(resultSet.getString("address"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        meterNumberField.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                try {
                    Connect connect = new Connect();
                    ResultSet resultSet = connect.statement.executeQuery("select * from customer where meter_no = '"
                            + meterNumberField.getSelectedItem() + "'");
                    while (resultSet.next()) {
                        labelNameField.setText(resultSet.getString("name"));
                        labelAddressField.setText(resultSet.getString("address"));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        JLabel labelUnitsConsumed = new JLabel("Units Consumed");
        labelUnitsConsumed.setBounds(100, 200, 120, 22);
        labelUnitsConsumed.setFont(customFont);
        panel.add(labelUnitsConsumed);

        this.unitsConsumedField = new JTextField();
        unitsConsumedField.setBounds(250, 200, 200, 22);
        unitsConsumedField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(unitsConsumedField);

        JLabel labelMonth = new JLabel("Month");
        labelMonth.setBounds(100, 240, 120, 22);
        labelMonth.setFont(customFont);
        panel.add(labelMonth);

        this.monthChoice = new JComboBox <String> ();
        monthChoice.setBounds(250, 240, 200, 22);
        monthChoice.addItem("January");
        monthChoice.addItem("February");
        monthChoice.addItem("March");
        monthChoice.addItem("April");
        monthChoice.addItem("May");
        monthChoice.addItem("June");
        monthChoice.addItem("July");
        monthChoice.addItem("August");
        monthChoice.addItem("September");
        monthChoice.addItem("October");
        monthChoice.addItem("November");
        monthChoice.addItem("December");
        monthChoice.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(monthChoice);

        this.submitButton = new JButton("Submit");
        submitButton.setBounds(120, 350, 100, 22);
        submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        panel.add(submitButton);

        this.cancelButton = new JButton("Cancel");
        cancelButton.setBounds(260, 350, 100, 22);
        cancelButton.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(this);
        panel.add(cancelButton);

        setLayout(new BorderLayout());
        add(panel, "Center");

        ImageIcon newCustomerPageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image scaleNewCustomerImage = newCustomerPageIcon.getImage().getScaledInstance(160,
                300, Image.SCALE_DEFAULT);
        ImageIcon newCustomerIcon = new ImageIcon(scaleNewCustomerImage);
        JLabel newCustomerImage = new JLabel(newCustomerIcon);
        add(newCustomerImage, "West");

        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitButton) {
            String meterNumber = (String) meterNumberField.getSelectedItem();
            String unitsConsumed = unitsConsumedField.getText();
            String month = (String) monthChoice.getSelectedItem();

            int totalBill = 0;

            String calculateTaxQuery = "select * from tax";

            try {

                Connect connect = new Connect();
                ResultSet resultSet = connect.statement.executeQuery(calculateTaxQuery);

                while (resultSet.next()) {
                    //Multiply the number of units consumed with the cost per unit.
                    totalBill += Integer.parseInt(unitsConsumed) *
                            Integer.parseInt(resultSet.getString("costPerUnit"));
                    totalBill += Integer.parseInt(resultSet.getString("meterRent"));
                    totalBill += Integer.parseInt(resultSet.getString("serviceCharge"));
                    totalBill += Integer.parseInt(resultSet.getString("serviceTax"));
                    totalBill += Integer.parseInt(resultSet.getString("swacchBharatCess"));
                    totalBill += Integer.parseInt(resultSet.getString("fixedTax"));

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String calculateBillQuery = "insert into bill values('" + meterNumber + "', '" + month + "', '" +
                    unitsConsumed + "', '" + totalBill + "', 'Not Paid')";
            try {
                Connect connect = new Connect();
                connect.statement.executeUpdate(calculateBillQuery);

                //This is not taught in the video
                //I want all the text being displayed on the project
                //In the "Book Antiqua" font and thus used ChatGPT to do the same.

                JLabel messageLabel = new JLabel("Customer Bill Updated Successfully");
//                // Set the font and size of the JLabel
                messageLabel.setFont(new Font("Book Antiqua", Font.BOLD, 14));
//                // Show the JOptionPane with the customized JLabel
                JOptionPane.showMessageDialog(null, messageLabel);

                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new CalculateBill();
    }
}
