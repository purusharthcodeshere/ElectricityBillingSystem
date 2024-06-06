package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MeterInformation extends JFrame implements ActionListener {

    Font customFont = new Font("Book Antiqua", Font.BOLD, 14);
    JButton submitButton;
    JComboBox <String> meterLocation, meterType, phaseCode, connectionType;
    String meterNumber;

    MeterInformation(String meterNumber) {
        this.meterNumber = meterNumber;
        setSize(700, 500);
        setLocation(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(173, 216, 230));
        add(panel);

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(170, 12, 250, 25);
        heading.setFont(new Font("Book Antiqua", Font.BOLD, 24));
        panel.add(heading);

        JLabel labelMeterNumber = new JLabel("Meter Number");
        labelMeterNumber.setBounds(100, 80, 120, 20);
        labelMeterNumber.setFont(customFont);
        panel.add(labelMeterNumber);

        JLabel labelMeterNumberField = new JLabel(this.meterNumber);
        labelMeterNumberField.setBounds(250, 80, 120, 20);
        labelMeterNumberField.setFont(customFont);
        panel.add(labelMeterNumberField);

        JLabel labelMeterLocation = new JLabel("Meter Location");
        labelMeterLocation.setBounds(100, 120, 120, 20);
        labelMeterLocation.setFont(customFont);
        panel.add(labelMeterLocation);

//        this.meterLocation = new Choice();
//        meterLocation.add("Outside");
//        meterLocation.add("Inside");
//        meterLocation.setBounds(250, 120, 100, 20);
//        meterLocation.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
//        panel.add(meterLocation);
//        Changed this code to something else below
//        As the option below allows more flexibility in font change and design

        this.meterLocation = new JComboBox<>();
        meterLocation.addItem("Outside");
        meterLocation.addItem("Inside");
        meterLocation.setBounds(250, 120, 150, 20);
        meterLocation.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(meterLocation);

        JLabel labelMeterType = new JLabel("Meter Type");
        labelMeterType.setBounds(100, 160, 120, 20);
        labelMeterType.setFont(customFont);
        panel.add(labelMeterType);

        this.meterType = new JComboBox<>();
        meterType.addItem("Electric Meter");
        meterType.addItem("Solar Meter");
        meterType.addItem("Smart Meter");
        meterType.setBounds(250, 160, 150, 20);
        meterType.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(meterType);


        JLabel labelPhaseCode = new JLabel("Phase Code");
        labelPhaseCode.setBounds(100, 200, 120, 20);
        labelPhaseCode.setFont(customFont);
        panel.add(labelPhaseCode);

        this.phaseCode = new JComboBox<>();
        phaseCode.addItem("Phase 01");
        phaseCode.addItem("Phase 02");
        phaseCode.addItem("Phase 03");
        phaseCode.addItem("Phase 04");
        phaseCode.addItem("Phase 05");
        phaseCode.addItem("Phase 06");
        phaseCode.addItem("Phase 07");
        phaseCode.addItem("Phase 08");
        phaseCode.addItem("Phase 09");
        phaseCode.addItem("Phase 10");
        phaseCode.setBounds(250, 200, 150, 20);
        phaseCode.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(phaseCode);

        JLabel labelConnectionType = new JLabel("Connection Type");
        labelConnectionType.setBounds(100, 240, 120, 20);
        labelConnectionType.setFont(customFont);
        panel.add(labelConnectionType);

        //Using connectionType as the variable name instead of BillType
        //This is to ensure more clarity about the usage of the object
        this.connectionType = new JComboBox<>();
        connectionType.addItem("Household");
        connectionType.addItem("Industrial Meter");
//        connectionType.addItem("Factory");
        connectionType.setBounds(250, 240, 150, 20);
        connectionType.setFont(new Font("Book Antiqua", Font.PLAIN, 14));
        panel.add(connectionType);

        JLabel labelDays = new JLabel("Days");
        labelDays.setBounds(100, 280, 120, 20);
        labelDays.setFont(customFont);
        panel.add(labelDays);

        JLabel labelDaysNumber = new JLabel("30");
        labelDaysNumber.setBounds(250, 280, 120, 20);
        labelDaysNumber.setFont(customFont);
        panel.add(labelDaysNumber);

        JLabel labelNote = new JLabel("Note:");
        labelNote.setBounds(100, 320, 50, 20);
        labelNote.setFont(new Font("Georgia", Font.BOLD, 16));
        panel.add(meterLocation);
        panel.add(labelNote);

        JLabel labelMessage = new JLabel("By default the bill is generated for 30 days only.");
        labelMessage.setBounds(150, 320, 500, 20);
        labelMessage.setFont(new Font("Georgia", Font.ITALIC, 16));
        panel.add(labelMessage);

        this.submitButton = new JButton("Submit");
        submitButton.setBounds(220, 390, 100, 25);
        submitButton.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        panel.add(submitButton);

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
        if (ae.getSource() == submitButton) {
            String meterNumberValue = meterNumber;
            String meterLocationValue = (String) meterLocation.getSelectedItem();
            String meterTypeValue = (String) meterType.getSelectedItem();
            String phaseCodeValue = (String) phaseCode.getSelectedItem();
            String connectionTypeValue = (String) connectionType.getSelectedItem();
            String daysValue = "30";

            String meterInformationQuery = "insert into meterInformation values('" + meterNumberValue + "', '" +
                    meterLocationValue + "', '" + meterTypeValue + "', '" + phaseCodeValue + "', '" +
                    connectionTypeValue + "', '" + daysValue + "')";
            try {

                Connect connect = new Connect();
                connect.statement.executeUpdate(meterInformationQuery);

                //This is not taught in the video
                //I want all the text being displayed on the project
                //In the "Book Antiqua" font and thus used ChatGPT to do the same.

                JLabel messageLabel = new JLabel("Meter Information Added Successfully");
                // Set the font and size of the JLabel
                messageLabel.setFont(new Font("Book Antiqua", Font.BOLD, 14));
                // Show the JOptionPane with the customized JLabel
                JOptionPane.showMessageDialog(null, messageLabel);

                setVisible(false);

                //new Frame

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new MeterInformation("");
    }
}
