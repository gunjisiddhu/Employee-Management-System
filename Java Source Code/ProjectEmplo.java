import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProjectEmplo extends JFrame implements ActionListener {

    JButton b1, b2;
    JLabel head, lusername;
    JTextField username;

    ProjectEmplo() {
        Icon icon1 = new ImageIcon("C:\\Users\\pavan\\Downloads\\Image pack\\back.png");
        b2 = new JButton(icon1);
        b2.setBounds(460, 30, 60, 40);
        add(b2);
        b2.addActionListener(this);

        Font font = new Font("TimesRoman", Font.BOLD, 20);
        head = new JLabel("Employee working Projects");
        head.setBounds(90, 55, 400, 35);
        head.setFont(font);
        add(head);

        b1 = new JButton("Display");
        b1.addActionListener(this);
        b1.setBounds(250, 450, 100, 30);
        add(b1);

        lusername = new JLabel("Project ID");
        lusername.setBounds(100, 160, 200, 30);
        add(lusername);

        username = new JTextField();
        username.setBounds(200, 160, 200, 30);
        add(username);

        setTitle("Employee Working Projects");
        setSize(600, 550);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    public static void main(String[] args) {
        new ProjectEmplo();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                String sql = "select w.username as Employees from working w,projectInfo p where w.pid = p.pid and p.pid=?";
                String url = "jdbc:mysql://localhost:3306/exercise";
                jdbc ob = new jdbc();
                String uname = ob.user();
                String pass = ob.pass();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, uname, pass);
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, username.getText());
                ResultSet rs = pst.executeQuery();
                if (rs.isBeforeFirst()) {
                    JScrollPane scrollPane = new JScrollPane();
                    scrollPane.setBounds(130, 200, 300, 150);
                    add(scrollPane);
                    JTable table = new JTable();
                    scrollPane.setViewportView(table);
                    table.setModel(DbUtils.resultSetToTableModel(rs));
                } else {
                    JOptionPane.showMessageDialog(null, "NO EXISTING PROJECTS", "Error", 3);
                }

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1, "Error", 3);
            }
        }

        if (e.getSource() == b2)

        {
            new Project();
        }
    }

}
