package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PayTM extends JFrame implements ActionListener {

    String meterNumberValue;
    JButton backButton;

    PayTM(String meterNumberValue) {
        this.meterNumberValue = meterNumberValue;

        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);

        try {
            editorPane.setPage("https://paytm.com/online-payments");
        } catch (Exception e) {
            editorPane.setContentType("text/html");
            editorPane.setText("<html>Could not load the page.<html>");
        }

        JScrollPane scrollPane = new JScrollPane(editorPane);
        add(scrollPane);

        this.backButton = new JButton("Back");
        backButton.setBounds(640, 20, 80, 30);
        backButton.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        backButton.setBackground(new Color(230, 10, 25));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        //Need to either setLayout as "null"
        //Or can simply add the button on top of the editorPane
        editorPane.add(backButton);

        setSize(800, 600);
        setLocation(400, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        this.setVisible(false);
        new PayBill(meterNumberValue);
    }

    public static void main(String[] args) {
        new PayTM("");
    }
}
