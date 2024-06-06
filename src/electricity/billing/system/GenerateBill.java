package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.ResultSet;

public class GenerateBill extends JFrame implements ActionListener {

    JComboBox <String> monthChoice;
    JPanel jPanel, buttonPanel;
    JTextArea textArea;
    JLabel meterNumber;
    JButton generateBillButton, printButton;
    String meterNumberValue;


    GenerateBill(String meterNumberValue) {
        this.meterNumberValue = meterNumberValue;

        setSize(500, 780);
        setLocation(550, 30);
        setLayout(new BorderLayout());

        this.jPanel = new JPanel();

        JLabel heading = new JLabel("Generate Electricity Bill");
        heading.setBounds(120, 5, 400, 30);
        heading.setFont(new Font("Book Antiqua", Font.BOLD, 18));

        this.meterNumber = new JLabel(this.meterNumberValue);

        this.monthChoice = new JComboBox <String> ();
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

        this.textArea = new JTextArea(50, 15);
        textArea.setText("\n\n\t--------Click on the--------" +
                "\n\t \"Generate Bill\" Button to generate\n\t" +
                "the bill of the Selected Month");
        textArea.setFont(new Font("Book Antiqua", Font.ITALIC, 20));

        JScrollPane scrollPane = new JScrollPane(textArea);

        this.generateBillButton = new JButton("Generate Bill");
//        this.generateBillButton.setBounds(100, 460, 100, 25);
        this.generateBillButton.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        this.generateBillButton.setBackground(new Color(24, 129, 251));
        this.generateBillButton.setForeground(Color.WHITE);
        this.generateBillButton.addActionListener(this);

        //Print Button Added extra by me
        //Was not taught in the video
        this.printButton = new JButton("Print Bill");
//        this.generateBillButton.setBounds(100, 460, 100, 25);
        this.printButton.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        this.printButton.setBackground(new Color(230, 10, 35));
        this.printButton.setForeground(Color.WHITE);
        this.printButton.addActionListener(this);

        //To add the buttons at the bottom together
        //To add the "Generate Bill" and "Print Bill" buttons
        // side by side at the bottom of your layout,
        // you can use a JPanel with a horizontal layout
        // (like FlowLayout or GridLayout).
        // Here is an updated version of your code
        // that includes a new JPanel to hold the buttons

        this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //Add the buttons on the panel
        buttonPanel.add(generateBillButton);
        buttonPanel.add(printButton);

        this.jPanel.add(heading);
        this.jPanel.add(meterNumber);
        this.jPanel.add(monthChoice);
        add(jPanel, "North");

        add(scrollPane, "Center");
        //Add the button panel instead of adding the buttons separately.
        add(buttonPanel, "South");
//        add(generateBillButton, "South");
//        add(printButton, "South");


        setVisible(true);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == generateBillButton) {
            try {
                Connect connect = new Connect();
                String monthValue = (String) monthChoice.getSelectedItem();

                textArea.setText("\tF3 Power House Private Limited\n\n\tELECTRICITY BILL GENERATED\n\t FOR THE MONTH OF\n\t " +
                        monthValue + ", 2024\n\n\n");

                ResultSet customerResultSet = connect.statement.executeQuery("select * from customer " +
                        "where meter_no = '" + meterNumberValue + "'");

                if (customerResultSet.next()) {
                    textArea.append("\n    Customer Name:\t " + customerResultSet.getString("name"));
                    textArea.append("\n    Meter Number:\t " + customerResultSet.getString("meter_no"));
                    textArea.append("\n    Address:\t\t " + customerResultSet.getString("address"));
                    textArea.append("\n    City:\t\t " + customerResultSet.getString("city"));
                    textArea.append("\n    State:\t\t " + customerResultSet.getString("state"));
                    textArea.append("\n    Email:\t\t " + customerResultSet.getString("email"));
                    textArea.append("\n    Phone Number:\t " + customerResultSet.getString("phone"));
                    textArea.append("\n    -----------------------------------------------------------------");
                    textArea.append("\n");
                }

                ResultSet meterInformationQuery = connect.statement.executeQuery("select * from meterInformation" +
                        " where meterNumber = '" + meterNumberValue + "'");

                if (meterInformationQuery.next()) {
                    textArea.append("\n    Meter Location:\t " +
                            meterInformationQuery.getString("meterLocation"));
                    textArea.append("\n    Meter Type:\t\t " +
                            meterInformationQuery.getString("meterType"));
                    textArea.append("\n    Phase Code:\t\t " +
                            meterInformationQuery.getString("phaseCode"));
                    textArea.append("\n    Bill Type:\t\t " +
                            meterInformationQuery.getString("connectionType"));
                    textArea.append("\n    Days:\t\t " +
                            meterInformationQuery.getString("days"));
                    textArea.append("\n    -----------------------------------------------------------------");
                    textArea.append("\n");
                }

                ResultSet taxQuery = connect.statement.executeQuery("select * from tax");

                if (taxQuery.next()) {
                    textArea.append("\n");
                    textArea.append("\n    Cost-Per-Unit:\t " +
                            taxQuery.getString("costPerUnit"));
                    textArea.append("\n    Meter Rent:\t\t " +
                            taxQuery.getString("meterRent"));
                    textArea.append("\n    Service Charge:\t " +
                            taxQuery.getString("serviceCharge"));
                    textArea.append("\n    Service Tax:\t\t " +
                            taxQuery.getString("serviceTax"));
                    textArea.append("\n    Swacch Bharat Cess:\t " +
                            taxQuery.getString("swacchBharatCess"));
                    textArea.append("\n    Fixed Tax:\t\t " +
                            taxQuery.getString("fixedTax"));

                    textArea.append("\n    -----------------------------------------------------------------");
                    textArea.append("\n");
                }

                ResultSet customerBillQuery = connect.statement.executeQuery("select * from bill where meterNumber = '"
                        + meterNumberValue + "' AND month = '" + monthChoice.getSelectedItem() + "'");

                if (customerBillQuery.next()) {
                    textArea.append("\n");
                    textArea.append("\n    Current Month:\t " +
                            customerBillQuery.getString("month"));
                    textArea.append("\n    Units Consumed:\t " +
                            customerBillQuery.getString("unitsConsumed"));
                    textArea.append("\n    Total Charges:\t " +
                            customerBillQuery.getString("totalBill"));
                    textArea.append("\n    Status:\t\t " +
                            customerBillQuery.getString("status"));
                    textArea.append("\n    -----------------------------------------------------------------");
                    textArea.append("\n    Total Payable:\t " +
                            customerBillQuery.getString("totalBill"));
                    textArea.append("\n");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (actionEvent.getSource() == printButton) {
            try {
                //Print Button ActionListener
                //Inside the actionPerformed method, a PrinterJob instance is created.
                //The printDialog method displays the print dialog.
                // If the user accepts, the print method is called to print the content.

                //Print Method:
                //The print method uses the Graphics object to render the JTextArea content.
                //If pageIndex is greater than 0, it returns NO_SUCH_PAGE, indicating there are no more pages to print.
                //With these changes, clicking the "Print Bill" button
                // will display a print dialog, and if confirmed,
                // it will print the contents of the JTextArea.

//                PrinterJob job = PrinterJob.getPrinterJob();
//                job.setPrintable((Printable) this);
//                boolean doPrint = job.printDialog();
//                if (doPrint) {
//                    job.print();
//                }
                textArea.print();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new GenerateBill("");
    }
}
