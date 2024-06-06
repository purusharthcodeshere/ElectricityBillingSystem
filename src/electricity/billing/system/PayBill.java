package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class PayBill extends JFrame implements ActionListener {

    JComboBox <String> monthChoice;
    String meterNumberValue;
    JButton payButton, backButton;
    //As we need to set the value for "status" as paid after the customer pays the bill
    //That's why we need global variable
    //As the "status" will be changed in the actionPerformed function
    //Which is a different function after the constructor
    //And the scope of "status" field was ending with the constructor
    //This is not taught in the course.
    //We used our logic and gained knowledge until now to make this change.
    JLabel statusField;

    PayBill(String meterNumberValue) {
        this.meterNumberValue = meterNumberValue;

        setLayout(null);
        setBounds(300, 150, 900, 600);

        JLabel heading = new JLabel("Pay Electricity Bill");
        heading.setBounds(120, 5, 400, 30);
        heading.setFont(new Font("Book Antiqua", Font.BOLD, 24));
        add(heading);

        JLabel labelMeterNumber = new JLabel("Meter Number");
        labelMeterNumber.setBounds(35, 80, 200, 20);
        labelMeterNumber.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelMeterNumber);

        JLabel meterNumberField = new JLabel("");
        meterNumberField.setBounds(300, 80, 200, 20);
        meterNumberField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(meterNumberField);

        JLabel labelName = new JLabel("Name");
        labelName.setBounds(35, 140, 200, 20);
        labelName.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelName);

        JLabel nameField = new JLabel("");
        nameField.setBounds(300, 140, 200, 20);
        nameField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(nameField);

        JLabel labelMonth = new JLabel("Month");
        labelMonth.setBounds(35, 200, 200, 20);
        labelMonth.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelMonth);

        this.monthChoice = new JComboBox <String> ();
        monthChoice.setBounds(300, 200, 200, 20);
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
        add(monthChoice);

        JLabel labelUnitsConsumed = new JLabel("Units Consumed");
        labelUnitsConsumed.setBounds(35, 260, 200, 20);
        labelUnitsConsumed.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelUnitsConsumed);

        JLabel unitsConsumedField = new JLabel("");
        unitsConsumedField.setBounds(300, 260, 200, 20);
        unitsConsumedField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(unitsConsumedField);

        JLabel labelTotalBill = new JLabel("Total Bill");
        labelTotalBill.setBounds(35, 320, 200, 20);
        labelTotalBill.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelTotalBill);

        JLabel totalBillField = new JLabel("");
        totalBillField.setBounds(300, 320, 200, 20);
        totalBillField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(totalBillField);

        JLabel labelStatus = new JLabel("Status");
        labelStatus.setBounds(35, 380, 200, 20);
        labelStatus.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelStatus);

        this.statusField = new JLabel("");
        statusField.setBounds(300, 380, 200, 20);
        statusField.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        statusField.setForeground(Color.RED);
        add(statusField);

        try {
            Connect connect = new Connect();
            ResultSet customerResultSet = connect.statement.executeQuery("select * from customer where meter_no = '" +
                    this.meterNumberValue + "'");

            while (customerResultSet.next()) {
                meterNumberField.setText(this.meterNumberValue);
                nameField.setText(customerResultSet.getString("name"));
            }

            ResultSet billResultSet = connect.statement.executeQuery("select * from bill where meterNumber = '" +
                    this.meterNumberValue + "' AND month = 'January'");

            while (billResultSet.next()) {
                unitsConsumedField.setText(billResultSet.getString("unitsConsumed"));
                totalBillField.setText(billResultSet.getString("totalBill"));
                statusField.setText(billResultSet.getString("status"));
//                statusField.setForeground(Color.RED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        monthChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                try {
                    Connect connect = new Connect();
                    ResultSet billResultSet = connect.statement.executeQuery("select * from bill where meterNumber = '" +
                            meterNumberValue + "' AND month = '" + monthChoice.getSelectedItem() + "'");
                    //Can't use "this" keyword above here as it is an override function
                    //for a different class

                    while (billResultSet.next()) {
                        unitsConsumedField.setText(billResultSet.getString("unitsConsumed"));
                        totalBillField.setText(billResultSet.getString("totalBill"));
                        statusField.setText(billResultSet.getString("status"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        this.payButton = new JButton("Pay Bill");
        payButton.setBounds(100, 460, 100, 25);
        payButton.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        payButton.setBackground(new Color(24, 129, 251));
        payButton.setForeground(Color.WHITE);
        payButton.addActionListener(this);
        add(payButton);

        this.backButton = new JButton("Back");
        backButton.setBounds(230, 460, 100, 25);
        backButton.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        backButton.setBackground(new Color(230, 10, 25));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        add(backButton);

        getContentPane().setBackground(Color.WHITE);


        ImageIcon payBillPageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image scalePayBillImage = payBillPageIcon.getImage().getScaledInstance(600,
                300, Image.SCALE_DEFAULT);
        ImageIcon payBillIcon = new ImageIcon(scalePayBillImage);
        JLabel payBillImage = new JLabel(payBillIcon);
        payBillImage.setBounds(420, 120, 600, 300);
        add(payBillImage);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == payButton) {
                statusField.setForeground(new Color(2, 189, 2));
            try {
                Connect connect = new Connect();
                connect.statement.executeUpdate("update bill set status = 'Paid' where meterNumber = '" +
                        meterNumberValue + "' AND month = '" + monthChoice.getSelectedItem() + "'");


            } catch (Exception e) {
                e.printStackTrace();
            }

            setVisible(false);
            //New Class object of the name "PayTM"
            new PayTM(meterNumberValue);

        } else if (actionEvent.getSource() == backButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new PayBill("");
    }
}
