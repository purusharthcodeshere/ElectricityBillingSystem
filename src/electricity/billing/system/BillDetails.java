package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class BillDetails extends JFrame {

    JTable billDetailsTable;
    String meterNumberValue;

    BillDetails(String meterNumberValue) {
        this.meterNumberValue = meterNumberValue;

        setSize(700, 650);
        setLocation(400, 150);

        getContentPane().setBackground(Color.WHITE);

        this.billDetailsTable = new JTable();

        try {
            Connect connect = new Connect();
            String billDetailsQuery = "select * from bill where meterNumber = '" + meterNumberValue + "'";
            ResultSet resultSet = connect.statement.executeQuery(billDetailsQuery);

            billDetailsTable.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (Exception e) {
            e.printStackTrace();
        }

        billDetailsTable.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(billDetailsTable);
        scrollPane.setBounds(0, 0, 700, 650);
        add(scrollPane);



        setVisible(true);
    }
    public static void main(String[] args) {
        new BillDetails("");
    }
}
