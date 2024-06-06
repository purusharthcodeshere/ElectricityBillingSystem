package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Project extends JFrame implements ActionListener {

    JMenu masterMenu, informationMenu, userMenu, reportMenu, utilityMenu, exitMenu;
    //Menu Items for Master menu option
    JMenuItem newCustomer, customerDetails, depositDetails, calculateBill;
    //Menu items for Information menu option
    JMenuItem updateInformation, viewInformation;
    //Menu Items for user menu option
    JMenuItem payBill, billDetails;
    //Menu item for Report menu option
    JMenuItem generateBill;
    //Menu Item for Utility Menu option
    JMenuItem notepad, calculator, chrome;
    JMenuItem exit;

    String userTypeValue, meterNumberValue;

    Project(String userTypeValue, String meterNumberValue) {
        this.userTypeValue = userTypeValue;
        this.meterNumberValue = meterNumberValue;

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon projectPageImage = new ImageIcon(ClassLoader.getSystemResource("icon/elect1.jpg"));
        Image scaleProjectPageImage = projectPageImage.getImage().getScaledInstance(1540, 850, Image.SCALE_DEFAULT);
        ImageIcon projectPageIcon = new ImageIcon(scaleProjectPageImage);
        JLabel projectImage = new JLabel(projectPageIcon);
        add(projectImage);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        this.masterMenu = new JMenu("Master");
        masterMenu.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        masterMenu.setForeground(new Color(230, 10, 25));
//        menuBar.add(masterMenu);
//        added below

        this.newCustomer = new JMenuItem("New Customer");
        newCustomer.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
        ImageIcon newCustomerIcon = new ImageIcon(ClassLoader.getSystemResource("icon/icon1.png"));
        Image scaleNewCustomerImage = newCustomerIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        newCustomer.setIcon(new ImageIcon(scaleNewCustomerImage));
        newCustomer.setMnemonic('N');
        newCustomer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newCustomer.addActionListener(this);
        masterMenu.add(newCustomer);

        this.customerDetails = new JMenuItem("Customer Details");
        customerDetails.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
        ImageIcon customerDetailsIcon = new ImageIcon(ClassLoader.getSystemResource("icon/icon2.png"));
        Image scaleCustomerDetailsImage = customerDetailsIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        customerDetails.setIcon(new ImageIcon(scaleCustomerDetailsImage));
        customerDetails.setMnemonic('C');
        customerDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        customerDetails.addActionListener(this);
        masterMenu.add(customerDetails);

        this.depositDetails = new JMenuItem("Deposit Details");
        depositDetails.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
        ImageIcon depositDetailsIcon = new ImageIcon(ClassLoader.getSystemResource("icon/icon3.png"));
        Image scaleDepositDetailsImage = depositDetailsIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        depositDetails.setIcon(new ImageIcon(scaleDepositDetailsImage));
        depositDetails.setMnemonic('D');
        depositDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        depositDetails.addActionListener(this);
        masterMenu.add(depositDetails);

        this.calculateBill = new JMenuItem("Calculate Bill");
        calculateBill.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
        ImageIcon calculateBillIcon = new ImageIcon(ClassLoader.getSystemResource("icon/icon5.png"));
        Image scaleCalculateBillImage = calculateBillIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculateBill.setIcon(new ImageIcon(scaleCalculateBillImage));
        calculateBill.setMnemonic('L');
        calculateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        calculateBill.addActionListener(this);
        masterMenu.add(calculateBill);

        this.informationMenu = new JMenu("Information");
        informationMenu.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        informationMenu.setForeground(Color.BLUE);
//        menuBar.add(informationMenu);
//        added below

        this.updateInformation = new JMenuItem("Update Information");
        updateInformation.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
        ImageIcon updateInformationIcon = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image scaleUpdateInformationIcon = updateInformationIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        updateInformation.setIcon(new ImageIcon(scaleUpdateInformationIcon));
        updateInformation.setMnemonic('U');
        updateInformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, ActionEvent.CTRL_MASK));
        updateInformation.addActionListener(this);
        informationMenu.add(updateInformation);

        this.viewInformation = new JMenuItem("View Information");
        viewInformation.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
        ImageIcon viewInformationIcon = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        Image scaleViewInformationIcon = viewInformationIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        viewInformation.setIcon(new ImageIcon(scaleViewInformationIcon));
        viewInformation.setMnemonic('V');
        viewInformation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        viewInformation.addActionListener(this);
        informationMenu.add(viewInformation);

        this.userMenu = new JMenu("User");
        userMenu.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        userMenu.setForeground(Color.BLUE);
//        menuBar.add(userMenu);
//        added below

        this.payBill = new JMenuItem("Pay Bill");
        payBill.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
        ImageIcon payBillIcon = new ImageIcon(ClassLoader.getSystemResource("icon/icon4.png"));
        Image scalePayBillIcon = payBillIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        payBill.setIcon(new ImageIcon(scalePayBillIcon));
        payBill.setMnemonic('P');
        payBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        payBill.addActionListener(this);
        userMenu.add(payBill);

        this.billDetails = new JMenuItem("Bill Details");
        billDetails.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
        ImageIcon billDetailsIcon = new ImageIcon(ClassLoader.getSystemResource("icon/icon6.png"));
        Image scaleBillDetailsIcon = billDetailsIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        billDetails.setIcon(new ImageIcon(scaleBillDetailsIcon));
        billDetails.setMnemonic('B');
        billDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        billDetails.addActionListener(this);
        userMenu.add(billDetails);

        this.reportMenu = new JMenu("Report");
        reportMenu.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        reportMenu.setForeground(Color.BLUE);
//        menuBar.add(reportMenu);
//        added below

        this.generateBill = new JMenuItem("Generate Bill");
        generateBill.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
        ImageIcon generateBillIcon = new ImageIcon(ClassLoader.getSystemResource("icon/icon7.png"));
        Image scaleGenerateBillIcon = generateBillIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        generateBill.setIcon(new ImageIcon(scaleGenerateBillIcon));
        generateBill.setMnemonic('G');
        generateBill.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));
        generateBill.addActionListener(this);
        reportMenu.add(generateBill);

        this.utilityMenu = new JMenu("Utility");
        utilityMenu.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        utilityMenu.setForeground(Color.BLUE);
//        menuBar.add(utilityMenu);
//        added below

        this.notepad = new JMenuItem("Notepad");
        notepad.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("icon/icon12.png"));
        Image scaleNotepadIcon = notepadIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        notepad.setIcon(new ImageIcon(scaleNotepadIcon));
        notepad.setMnemonic('T');
        notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
        notepad.addActionListener(this);
        utilityMenu.add(notepad);

        this.calculator = new JMenuItem("Calculator");
        calculator.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
        ImageIcon calculatorIcon = new ImageIcon(ClassLoader.getSystemResource("icon/icon9.png"));
        Image scaleCalculatorIcon = calculatorIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        calculator.setIcon(new ImageIcon(scaleCalculatorIcon));
        calculator.setMnemonic('A');
        calculator.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        calculator.addActionListener(this);
        utilityMenu.add(calculator);

        this.chrome = new JMenuItem("Google Chrome");
        chrome.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
        ImageIcon chromeIcon = new ImageIcon(ClassLoader.getSystemResource("icon/icon9.png"));
        Image scaleChromeIcon = chromeIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        chrome.setIcon(new ImageIcon(scaleChromeIcon));
        chrome.setMnemonic('R');
        chrome.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        chrome.addActionListener(this);
        utilityMenu.add(chrome);

        this.exitMenu = new JMenu("Exit");
        exitMenu.setFont(new Font("Book Antiqua", Font.BOLD, 14));
        exitMenu.setForeground(new Color(230, 10, 25));

        this.exit = new JMenuItem("Exit");
        exit.setFont(new Font("Book Antiqua", Font.PLAIN, 12));
        ImageIcon exitIcon = new ImageIcon(ClassLoader.getSystemResource("icon/icon11.png"));
        Image scaleExitIcon = exitIcon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        exit.setIcon(new ImageIcon(scaleExitIcon));
        exit.setMnemonic('W');
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK));
        exit.addActionListener(this);
        exitMenu.add(exit);

        if (this.userTypeValue.equals("Admin")) {
            menuBar.add(masterMenu);

        } else {
            menuBar.add(informationMenu);
            menuBar.add(userMenu);
            menuBar.add(reportMenu);
        }



        menuBar.add(utilityMenu);
        menuBar.add(exitMenu);

        setLayout(new FlowLayout());

        setVisible(true);
    }

    public void actionPerformed (ActionEvent ae) {
        String message = ae.getActionCommand();
        if (message.equals("New Customer")) {
            new NewCustomer();
        } else if (message.equals("Customer Details")) {
            new CustomerDetails();
        } else if (message.equals("Deposit Details")) {
            new DepositDetails();
        } else if (message.equals("Calculate Bill")) {
            new CalculateBill();
        } else if (message.equals("Update Information")) {
            new UpdateInformation(meterNumberValue);
        } else if (message.equals("View Information")) {
            new ViewInformation(meterNumberValue);
        } else if (message.equals("Pay Bill")) {
            new PayBill(meterNumberValue);
        } else if (message.equals("Bill Details")) {
            new BillDetails(meterNumberValue);
        } else if (message.equals("Generate Bill")) {
            new GenerateBill(meterNumberValue);
        } else if (message.equals("Notepad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (message.equals("Calculator")) {
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (message.equals("Google Chrome")) {
            try {
                //This was not taught on the video
                //We wanted another utility function where I can open the Google Chrome App
                //So we used this command. We took help from ChatGPT for errors.
                Runtime.getRuntime().exec("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (message.equals("Exit")) {
            setVisible(false);
            new Login();
        }
    }
    public static void main(String[] args) {
        new Project("", "");
    }
}
