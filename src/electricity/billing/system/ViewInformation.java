package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ViewInformation extends JFrame implements ActionListener {

    JButton cancelButton;
    String meterNumberValue;
    //Note: Set the alignment of all the labels a little to the left
    //So that the email field is visible clearly
    //Right now the email cuts halfway

    ViewInformation(String meterNumberValue) {
        this.meterNumberValue = meterNumberValue;

        setBounds(350, 120, 850, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("View Customer Information");
        heading.setBounds(280, 0, 500, 40);
        heading.setFont(new Font("Book Antiqua", Font.BOLD, 20));
        add(heading);

        JLabel labelName = new JLabel("Name");
        labelName.setBounds(70, 80, 100, 20);
        labelName.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelName);

        JLabel labelNameField = new JLabel("");
        labelNameField.setBounds(220, 80, 150, 20);
        labelNameField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(labelNameField);

        JLabel labelMeterNumber = new JLabel("Meter Number");
        labelMeterNumber.setBounds(70, 140, 100, 20);
        labelMeterNumber.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelMeterNumber);

        JLabel labelMeterNumberField = new JLabel("");
        labelMeterNumberField.setBounds(220, 140, 150, 20);
        labelMeterNumberField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(labelMeterNumberField);

        JLabel labelAddress = new JLabel("Address");
        labelAddress.setBounds(70, 200, 100, 20);
        labelAddress.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelAddress);

        JLabel labelAddressField = new JLabel("");
        labelAddressField.setBounds(220, 200, 150, 20);
        labelAddressField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(labelAddressField);

        JLabel labelCity = new JLabel("City");
        labelCity.setBounds(70, 260, 100, 20);
        labelCity.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelCity);

        JLabel labelCityField = new JLabel("");
        labelCityField.setBounds(220, 260, 150, 20);
        labelCityField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(labelCityField);

        JLabel labelState = new JLabel("State");
        labelState.setBounds(500, 80, 100, 20);
        labelState.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelState);

        JLabel labelStateField = new JLabel("");
        labelStateField.setBounds(620, 80, 150, 20);
        labelStateField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(labelStateField);

        JLabel labelEmail = new JLabel("Email");
        labelEmail.setBounds(500, 140, 100, 20);
        labelEmail.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelEmail);

        JLabel labelEmailField = new JLabel("");
        labelEmailField.setBounds(620, 140, 150, 20);
        labelEmailField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(labelEmailField);

        JLabel labelPhone = new JLabel("Phone");
        labelPhone.setBounds(500, 200, 100, 20);
        labelPhone.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        add(labelPhone);

        JLabel labelPhoneField = new JLabel("");
        labelPhoneField.setBounds(620, 200, 150, 20);
        labelPhoneField.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        add(labelPhoneField);

        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.statement.executeQuery("select * from customer where meter_no = '" +
                    meterNumberValue + "'");
            while (resultSet.next()) {
                labelNameField.setText(resultSet.getString("name"));
                labelMeterNumberField.setText(resultSet.getString("meter_no"));
                labelAddressField.setText(resultSet.getString("address"));
                labelCityField.setText(resultSet.getString("city"));
                labelStateField.setText(resultSet.getString("state"));
                labelEmailField.setText(resultSet.getString("email"));
                labelPhoneField.setText(resultSet.getString("phone"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.cancelButton = new JButton("Cancel");
        cancelButton.setBounds(350, 320, 100, 25);
        cancelButton.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        cancelButton.setBackground(new Color(230, 10, 25));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(this);
        add(cancelButton);

        ImageIcon viewInformationPageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image scaleViewInformationImage = viewInformationPageIcon.getImage().getScaledInstance(600,
                300, Image.SCALE_DEFAULT);
        ImageIcon viewInformationIcon = new ImageIcon(scaleViewInformationImage);
        JLabel viewInformationImage = new JLabel(viewInformationIcon);
        viewInformationImage.setBounds(20, 350, 600, 300);
        add(viewInformationImage);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new ViewInformation("");
    }
}
