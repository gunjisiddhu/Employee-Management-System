import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

import com.mysql.cj.xdevapi.Result;

public class MarkAttendance extends JFrame implements ActionListener {

    JCheckBox arr[] = new JCheckBox[1000];
    JTextField j1;
    JLabel l1, l2;
    ResultSet rs, rs2;
    int i = 0;
    int x = 0, y = 90, width = 100, height = 30;
    JButton b1, b2, b3, b4;

    MarkAttendance() {
        l1 = new JLabel("Date  :");
        l2 = new JLabel("(YYYY/MM/DD)");

        Font font = new Font("TimesRoman", Font.BOLD, 30);
        l1.setFont(font);

        l1.setBounds(30, 20, 150, 30);
        l2.setBounds(30, 40, 150, 40);
        add(l1);
        add(l2);
        j1 = new JTextField();
        j1.setBounds(150, 20, 100, 30);
        add(j1);
        b1 = new JButton("Mark Attendance");
        b1.setBounds(250, 650, 150, 40);
        b1.addActionListener(this);
        add(b1);

        Icon icon1 = new ImageIcon("C:\\Users\\pavan\\Downloads\\Image pack\\back.png");
        b4 = new JButton(icon1);
        b4.setBounds(700, 20, 60, 40);
        add(b4);
        b4.addActionListener(this);

        try {
            String url = "jdbc:mysql://localhost:3306/exercise";
            jdbc ob = new jdbc();
            String uname = ob.user();
            String pass = ob.pass();
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, uname, pass);
            String sql = "select * from employee";
            PreparedStatement pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            // conn.close();
            // pst.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            while (rs.next()) {
                x += 100;
                if (x >= 600) {
                    x = 100;
                    y += 70;
                }
                arr[i] = new JCheckBox(rs.getString(2));
                arr[i].setBounds(x, y, width, height);
                add(arr[i]);
                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 6 - this.getSize().width / 4, dim.height / 50 - this.getSize().height / 1);

        setTitle("Attendance Registry");
        setSize(800, 800);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    public static void main(String[] args) throws Exception {
        new MarkAttendance();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String date = j1.getText();
            if (date != "") {
                try {
                    String url = "jdbc:mysql://localhost:3306/exercise";
                    String uname = "root";
                    String pass = "pavani10";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(url, uname, pass);
                    String sql = "select * from employee";
                    String sql2 = "select * from attendance where date=?";
                    String sql3 = "insert into attendance values(?,?,?,?)";

                    PreparedStatement pst = conn.prepareStatement(sql);
                    PreparedStatement pst2 = conn.prepareStatement(sql2);
                    PreparedStatement pst3 = conn.prepareStatement(sql3);

                    ResultSet rs = pst.executeQuery();
                    pst2.setString(1, date);
                    ResultSet rs2 = pst2.executeQuery();
                    if (rs2.next()) {
                        JOptionPane.showMessageDialog(null, "Attendance Already Marked", "Yes..!",
                                JOptionPane.PLAIN_MESSAGE);
                    } else {
                        for (int j = 0; j < i; j++) {
                            rs.next();
                            pst3.setString(1, rs.getString(1));
                            pst3.setString(2, rs.getString(2));
                            if (arr[j].isSelected())
                                pst3.setInt(3, 1);
                            else
                                pst3.setInt(3, 0);
                            pst3.setString(4, date);
                            pst3.executeUpdate();
                        }
                        JOptionPane.showMessageDialog(null, "Attendance Marked", "Yes..!", JOptionPane.PLAIN_MESSAGE);
                    }

                    /*
                     * String sql = "select * from attendance"; String sql2 =
                     * "update attendance set count = ? where username = ?"; String sql3 =
                     * "select * from attendance where date = ?"; PreparedStatement pst3 =
                     * conn.prepareStatement(sql3); PreparedStatement pst =
                     * conn.prepareStatement(sql); PreparedStatement pst2 =
                     * conn.prepareStatement(sql2); pst3.setString(1, j1.getText()); ResultSet rr =
                     * pst3.executeQuery(); if (rr.next()) { JOptionPane.showMessageDialog(null,
                     * "Attendance Already Marked", "Yes..!", JOptionPane.PLAIN_MESSAGE); } else {
                     * rs = pst.executeQuery(); for (int j = 0; j < i; j++) { rs.next(); int c =
                     * rs.getInt(3); if (arr[j].isSelected()) { c = c + 1; pst2.setInt(1, 1);
                     * pst2.setString(2, rs.getString(1)); pst2.executeUpdate(); } else {
                     * pst2.setInt(1, 0); pst2.setString(2, rs.getString(1)); pst2.executeUpdate();
                     * }
                     * 
                     * }
                     */
                    JOptionPane.showMessageDialog(null, "Done", "Yes..!", JOptionPane.PLAIN_MESSAGE);
                }

                catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1, "Yes..!", JOptionPane.PLAIN_MESSAGE);
                }
            }
        }
        if (e.getSource() == b4) {
            new AdminPage();
            dispose();
        }
    }
}
