import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class AdminPage extends JFrame implements ActionListener {
    JButton b1, b2, b3, b4, b5, b6, b7, b8;

    AdminPage() {
        Icon icon = new ImageIcon("C:\\Users\\pavan\\Downloads\\Image pack\\logout.png");

        b6 = new JButton(icon);
        b1 = new JButton("Add New Employee");
        b2 = new JButton("Update Employee Details");
        b3 = new JButton("Remove an Employee");
        b4 = new JButton("Mark Attendance");
        b7 = new JButton("Attendance Report for the Month");
        b8 = new JButton("Project Working and Details");

        b1.setBounds(200, 100, 275, 40);
        b2.setBounds(200, 200, 275, 40);
        b3.setBounds(200, 300, 275, 40);
        b4.setBounds(200, 400, 275, 40);
        b6.setBounds(600, 30, 60, 40);
        b7.setBounds(200, 500, 275, 40);
        b8.setBounds(200, 600, 275, 40);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);

        b6.setBackground(Color.white);

        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b6);
        add(b7);
        add(b8);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 4 - this.getSize().width / 4, dim.height / 22 - this.getSize().height / 2);

        setTitle("Admin Page");
        setSize(700, 800);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    public static void main(String[] args) {
        new AdminPage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            new NewUserForm();
            dispose();

        }
        if (e.getSource() == b2) {
            new UpdateUser();
            dispose();
        }
        if (e.getSource() == b3) {
            new DeleteUser();
            dispose();

        }
        if (e.getSource() == b4) {
            new MarkAttendance();
            dispose();
        }
        if (e.getSource() == b6) {
            new OpenPage();
            dispose();
        }
        if (e.getSource() == b7) {
            new AttendanceCount();
            dispose();
        }
        if (e.getSource() == b8) {
            new Project();
            dispose();
        }

    }
}
