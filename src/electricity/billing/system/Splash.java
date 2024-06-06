package electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {

    Thread thread;

    Splash() {

        ImageIcon elect = new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));
        Image electScale = elect.getImage().getScaledInstance(730, 550, Image.SCALE_DEFAULT);
        ImageIcon newImage = new ImageIcon(electScale);
        JLabel image = new JLabel(newImage);
        add(image);

        setVisible(true);

        for (int i = 2, x = 1; i < 600; i += 4, x += 1) {
            setSize(i + x, i);
            setLocation(700 - ((i + x)/2) , 400 - (i/2));

            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        thread = new Thread(this);
        thread.start();

        setVisible(true);
    }

    public void run () {
        try {
            Thread.sleep(3500);
            setVisible(false);

            //login frame
            new Login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Splash();
    }
}
