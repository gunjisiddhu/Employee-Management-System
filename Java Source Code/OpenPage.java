import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.awt.*;

public class OpenPage extends JFrame implements ActionListener {
    JLabel j1, j2;
    JButton b1, b2;
    JPanel jp;

    OpenPage() {
        Icon icon = new ImageIcon("welcome.png");

        b1 = new JButton("Admin");
        b2 = new JButton("User");
        j1 = new JLabel("          Employee Database");
        j2 = new JLabel();
        jp = new JPanel();

        jp.setBounds(50, 10, 500, 200);
        b1.setBounds(300, 350, 120, 40);
        b2.setBounds(150, 350, 120, 40);
        j1.setBounds(90, 280, 410, 40);

        b1.addActionListener(this);
        b2.addActionListener(this);

        // Color
        Color c1 = new Color(170, 150, 255);
        Color c2 = new Color(210, 55, 55);

        Border border = BorderFactory.createLineBorder(Color.black);
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 20);
        j1.setFont(font);
        j1.setBorder(border);
        b1.setBackground(c1);
        b2.setBackground(c2);
        add(b1);
        add(b2);
        add(j1);
        add(j2);
        j2.setIcon(icon);
        jp.add(j2);

        add(jp);
        validate();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 3 - this.getSize().width / 3, dim.height / 5 - this.getSize().height / 5);

        setTitle("Entry Page");
        setSize(650, 500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    public static void main(String[] args) {
        new OpenPage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            // new AdminLogin();
            dispose();
        }
        if (e.getSource() == b2) {
            // new UserLogin();
            dispose();
        }

    }

}
