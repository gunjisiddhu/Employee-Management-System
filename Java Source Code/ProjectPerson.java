import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class ProjectPerson extends JFrame implements ActionListener {

    JTextField pid, username, work, edate;
    JLabel header, lpid, lusername, lwork, ledate;
    JButton b1, b2, b3;

    ButtonGroup bg;

    ProjectPerson() {
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
        username = new JTextField();
        work = new JTextField();
        edate = new JTextField();

        // Labels
        header = new JLabel("Enter Project Details");
        lpid = new JLabel("Project ID");
        lusername = new JLabel("Username");
        lwork = new JLabel("Type Of Work  :");
        ledate = new JLabel("Join Date  :");

        // Bounds
        header.setBounds(150, 75, 300, 30);
        lpid.setBounds(80, 120, 180, 40);
        lusername.setBounds(80, 170, 160, 40);
        lwork.setBounds(80, 220, 160, 40);
        ledate.setBounds(80, 270, 160, 40);

        pid.setBounds(220, 120, 120, 30);
        username.setBounds(220, 170, 120, 30);
        work.setBounds(220, 220, 120, 30);
        edate.setBounds(220, 270, 120, 30);

        b1.setBounds(140, 340, 80, 30);
        b2.setBounds(240, 340, 100, 30);

        b1.addActionListener(this);
        b2.addActionListener(this);
        add(header);
        add(lpid);
        add(lusername);
        add(lwork);
        add(ledate);

        add(pid);
        add(username);
        add(work);
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
        new ProjectPerson();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {

            try {
                String url = "jdbc:mysql://localhost:3306/exercise";
                String uname = "root";
                String pass = "pavani10";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, uname, pass);
                // String sql = "insert into projectInfo values(?,?,?,?)";
                String sql = "insert into working values(?,?,?,?)";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(2, pid.getText());
                pst.setString(1, username.getText());
                pst.setString(3, work.getText());
                pst.setString(4, edate.getText());

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "DONE Adding Employee", "Insertion Box", JOptionPane.PLAIN_MESSAGE);

                conn.close();
                pst.close();

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1, "Insertion Box", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == b2) {
            pid.setText(null);
            username.setText(null);
            work.setText(null);
            edate.setText(null);

        }
        if (e.getSource() == b3) {
            new Project();
            dispose();
        }

    }

}
