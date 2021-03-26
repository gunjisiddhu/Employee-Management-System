import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProjectDetails extends JFrame implements ActionListener {
    /*
     * pid char(20) primary key, name char(20), sdate date, edate date,
     */
    JLabel lpid, lname, lsdate, ledate, name, sdate, edate, head;
    JTextField pid;
    JButton b1, b2;

    ProjectDetails() {
        Icon icon1 = new ImageIcon("C:\\Users\\pavan\\Downloads\\Image pack\\back.png");
        b2 = new JButton(icon1);
        b2.setBounds(460, 30, 60, 40);
        add(b2);
        b2.addActionListener(this);

        Font font = new Font("TimesRoman", Font.BOLD, 20);
        head = new JLabel("PROJECT DETAILS");
        head.setBounds(130, 45, 400, 35);
        head.setFont(font);
        add(head);

        b1 = new JButton("Display");
        b1.addActionListener(this);
        b1.setBounds(250, 450, 100, 30);
        add(b1);

        lpid = new JLabel("Project ID");
        lname = new JLabel("Project Name");
        lsdate = new JLabel("Start Date :");
        ledate = new JLabel("End Date : ");
        pid = new JTextField();
        name = new JLabel();
        sdate = new JLabel();
        edate = new JLabel();

        lpid.setBounds(50, 90, 150, 40);
        lname.setBounds(50, 160, 150, 40);
        lsdate.setBounds(50, 240, 150, 40);
        ledate.setBounds(50, 320, 150, 40);

        pid.setBounds(150, 90, 150, 30);
        name.setBounds(150, 160, 150, 30);
        sdate.setBounds(150, 240, 150, 30);
        edate.setBounds(150, 320, 150, 30);

        add(lpid);
        add(lname);
        add(lsdate);
        add(ledate);
        add(pid);
        add(name);
        add(sdate);
        add(edate);

        setTitle("Project Details");
        setSize(600, 600);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(3);

    }

    public static void main(String[] args) {
        new ProjectDetails();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String sql = "select * from projectInfo where PID = ?";
            try {
                String url = "jdbc:mysql://localhost:3306/exercise";
                jdbc ob = new jdbc();
                String uname = ob.user();
                String pass = ob.pass();
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, uname, pass);
                PreparedStatement pst = conn.prepareStatement(sql);

                pst.setString(1, pid.getText());

                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    name.setText(rs.getString(2));
                    sdate.setText(rs.getString(3));
                    edate.setText(rs.getString(4));
                } else {
                    JOptionPane.showMessageDialog(null, pid.getText() + " Not an Existing Project",
                            "Unsuccessful Login", JOptionPane.ERROR_MESSAGE);
                }
                conn.close();
                pst.close();

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1, "Unsuccessful Login", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == b2) {
            new Project();
            dispose();
        }

    }

}
