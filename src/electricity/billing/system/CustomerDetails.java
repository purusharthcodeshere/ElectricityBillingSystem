package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CustomerDetails extends JFrame implements ActionListener {

    JComboBox <String> meterNumberChoice, monthChoice;
    JTable customerDetailsTable;
    JButton  printButton;

    CustomerDetails() {
        super("Customer Details");

        setSize(1200, 650);
        setLocation(200, 150);

        this.customerDetailsTable = new JTable();

        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.statement.executeQuery("select * from customer");

            customerDetailsTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }
        customerDetailsTable.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(customerDetailsTable);
        add(scrollPane);

        this.printButton = new JButton("Print");
        printButton.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        printButton.addActionListener(this);
        add(printButton, "South");

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            customerDetailsTable.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CustomerDetails();
    }
}
