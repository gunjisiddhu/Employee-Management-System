import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Project extends JFrame implements ActionListener {
    JButton b1, b2, b3, b4, b5, b6, b7;

    Project() {
        Icon icon = new ImageIcon("C:\\Users\\pavan\\Downloads\\Image pack\\logout.png");

        b6 = new JButton(icon);
        b1 = new JButton("Create New Project");
        b2 = new JButton("Add Employees to Project");
        b3 = new JButton("Get Project Details");
        b4 = new JButton("Get Employees working on a Project");
        b7 = new JButton("Get Projects participated by Employee");

        b1.setBounds(200, 100, 275, 40);
        b2.setBounds(200, 200, 275, 40);
        b3.setBounds(200, 300, 275, 40);
        b4.setBounds(200, 400, 275, 40);
        b6.setBounds(600, 30, 60, 40);
        b7.setBounds(200, 500, 275, 40);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        b6.setBackground(Color.white);

        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b6);
        add(b7);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 4 - this.getSize().width / 4, dim.height / 22 - this.getSize().height / 2);

        setTitle("Admin Page");
        setSize(700, 750);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    public static void main(String[] args) {
        new Project();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            new NewProject();
            dispose();

        }
        if (e.getSource() == b2) {
            new ProjectPerson();
            dispose();
        }
        if (e.getSource() == b3) {
            new ProjectDetails();
            dispose();

        }
        if (e.getSource() == b4) {
            new ProjectEmplo();
            dispose();
        }
        if (e.getSource() == b6) {
            new AdminPage();
            dispose();
        }
        if (e.getSource() == b7) {
            new EmployeeProj();
            dispose();
        }

    }
}
