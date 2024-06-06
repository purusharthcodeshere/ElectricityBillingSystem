package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class DepositDetails extends JFrame implements ActionListener {

    JComboBox <String> meterNumberChoice, monthChoice;
    JTable depositDetailsTable;
    JButton searchButton, printButton;

    DepositDetails() {
        super("Deposit Details");

        setSize(800, 700);
        setLocation(400, 80);

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel labelMeterNumber = new JLabel("Search by Meter Number");
        labelMeterNumber.setBounds(20, 20, 180, 22);
        labelMeterNumber.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelMeterNumber);

        this.meterNumberChoice = new JComboBox <String> ();
        meterNumberChoice.setBounds(210, 20, 150, 22);
        meterNumberChoice.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(meterNumberChoice);

        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.statement.executeQuery("select * from customer");

            while (resultSet.next()) {
                meterNumberChoice.addItem(resultSet.getString("meter_no"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel labelMonth = new JLabel("Search by Month");
        labelMonth.setBounds(400, 20, 130, 22);
        labelMonth.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelMonth);

        this.monthChoice = new JComboBox <String> ();
        monthChoice.setBounds(550, 20, 150, 22);
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

        this.depositDetailsTable = new JTable();

        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.statement.executeQuery("select * from bill");

            depositDetailsTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }
        depositDetailsTable.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(depositDetailsTable);
        scrollPane.setBounds(0, 100, 800, 600);
        add(scrollPane);

        this.searchButton = new JButton("Search");
        searchButton.setBounds(20, 70, 80, 22);
        searchButton.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        searchButton.addActionListener(this);
        add(searchButton);

        this.printButton = new JButton("Print");
        printButton.setBounds(120, 70, 80, 22);
        printButton.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        printButton.addActionListener(this);
        add(printButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchButton) {
            String searchQuery = "select * from bill where meterNumber = '" + meterNumberChoice.getSelectedItem() +
                    "' and month = '" + monthChoice.getSelectedItem() + "'";
            try {
                Connect connect = new Connect();
                ResultSet resultSet = connect.statement.executeQuery(searchQuery);
                depositDetailsTable.setModel(DbUtils.resultSetToTableModel(resultSet));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                depositDetailsTable.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new DepositDetails();
    }
}
