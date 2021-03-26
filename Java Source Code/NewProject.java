import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class NewProject extends JFrame implements ActionListener {

    JTextField pid, name, sdate, edate;
    JLabel header, lpid, lname, lsdate, ledate;
    JButton b1, b2, b3;

    ButtonGroup bg;

    NewProject() {
        // buttons
        b1 = new JButton("Accept");
        b2 = new JButton("Clear All");
        Icon icon1 = new ImageIcon("C:\\Users\\pavan\\Downloads\\Image pack\\back.png");
        b3 = new JButton(icon1);
        b3.setBounds(400, 30, 60, 40);
        add(b3);
        b3.addActionListener(this);
        // Radio Buttons

        // TextFields
        pid = new JTextField();
        name = new JTextField();
        sdate = new JTextField();
        edate = new JTextField();

        // Labels
        header = new JLabel("Enter Project Details");
        lpid = new JLabel("Project ID");
        lname = new JLabel("Name");
        lsdate = new JLabel("Start Date  :");
        ledate = new JLabel("End Date  :");

        // Bounds
        header.setBounds(150, 75, 300, 30);
        lpid.setBounds(80, 120, 180, 40);
        lname.setBounds(80, 170, 160, 40);
        lsdate.setBounds(80, 220, 160, 40);
        ledate.setBounds(80, 270, 160, 40);

        pid.setBounds(220, 120, 120, 30);
        name.setBounds(220, 170, 120, 30);
        sdate.setBounds(220, 220, 120, 30);
        edate.setBounds(220, 270, 120, 30);

        b1.setBounds(140, 340, 80, 30);
        b2.setBounds(240, 340, 100, 30);

        b1.addActionListener(this);
        b2.addActionListener(this);
        add(header);
        add(lpid);
        add(lname);
        add(lsdate);
        add(ledate);

        add(pid);
        add(name);
        add(sdate);
        add(edate);

        add(b1);
        add(b2);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 6 - this.getSize().width / 4, /* dim.height / 50 - */this.getSize().height / 1);

        setSize(500, 600);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    public static void main(String[] args) {
        new NewProject();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {

            try {
                String url = "jdbc:mysql://localhost:3306/exercise";
                jdbc ob = new jdbc();
                String uname = ob.user();
                String pass = ob.pass();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, uname, pass);
                String sql = "insert into projectInfo values(?,?,?,?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, pid.getText());
                pst.setString(2, name.getText());
                pst.setString(3, sdate.getText());
                pst.setString(4, edate.getText());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "DONE\nYou can Now Add Employees to Project", "Insertion Box",
                        JOptionPane.PLAIN_MESSAGE);

                conn.close();
                pst.close();

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1, "Insertion Box", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == b2) {
            pid.setText(null);
            name.setText(null);
            sdate.setText(null);
            edate.setText(null);

        }
        if (e.getSource() == b3) {
            new Project();
            dispose();
        }

    }

}
