import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AttendanceCount extends JFrame implements ActionListener {
    JComboBox month;
    JTextField year;
    JButton b1, b2;
    String months[] = { "month", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
    String User = "";
    int days, fin, sal, sall;
    JLabel j1, j2;

    AttendanceCount() {
        Icon icon1 = new ImageIcon("C:\\Users\\pavan\\Downloads\\Image pack\\back.png");

        j1 = new JLabel("Attendance Count For The Month");
        Font font = new Font("TimesRoman", Font.BOLD, 20);
        j1.setFont(font);

        month = new JComboBox(months);
        year = new JTextField();

        b1 = new JButton("Generate");
        b2 = new JButton(icon1);
        b2.addActionListener(this);
        b1.addActionListener(this);
        b2.setBounds(10, 30, 60, 40);
        add(b2);

        month.setBounds(425, 20, 60, 20);
        year.setBounds(490, 20, 60, 20);
        j1.setBounds(90, 55, 400, 35);
        b1.setBounds(250, 450, 100, 30);
        add(month);
        add(year);
        add(j1);
        add(b1);

        setTitle("Attendance Count");
        setSize(600, 550);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    public static void main(String[] args) {
        new AttendanceCount();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if ((month.getSelectedItem().equals("month")) || year.getText() == null) {
                JOptionPane.showMessageDialog(null, "Please Choose a Month And Year", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {

                // String sql = "select count(*) from attendance where username = ? and count =
                // 1 and date>? and date<=?";
                String sql = "select username,count(username) as Attendance from attendance  where date>=? and date<=? group by username";
                try {
                    String url = "jdbc:mysql://localhost:3306/exercise";
                    jdbc ob = new jdbc();
                    String uname = ob.user();
                    String pass = ob.pass();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(url, uname, pass);
                    PreparedStatement pst = conn.prepareStatement(sql);

                    if ((month.getSelectedItem().equals("1")) || (month.getSelectedItem().equals("3"))
                            || (month.getSelectedItem().equals("5")) || (month.getSelectedItem().equals("7"))
                            || (month.getSelectedItem().equals("8")) || (month.getSelectedItem().equals("10"))
                            || (month.getSelectedItem().equals("12"))) {
                        days = 31;

                        pst.setString(1, year.getText() + "-" + month.getSelectedItem() + "-01");
                        pst.setString(2, year.getText() + "-" + month.getSelectedItem() + "-31");
                    } else if ((month.getSelectedItem().equals("2"))) {
                        days = 28;
                        pst.setString(1, year.getText() + "-" + month.getSelectedItem() + "-01");
                        pst.setString(2, year.getText() + "-" + month.getSelectedItem() + "-28");
                    } else {
                        days = 30;
                        pst.setString(1, year.getText() + "-" + month.getSelectedItem() + "-01");
                        pst.setString(2, year.getText() + "-" + month.getSelectedItem() + "-30");
                    }

                    ResultSet rs1 = pst.executeQuery();

                    JScrollPane scrollPane = new JScrollPane();
                    scrollPane.setBounds(130, 154, 300, 300);
                    add(scrollPane);
                    JTable table = new JTable();
                    scrollPane.setViewportView(table);
                    table.setModel(DbUtils.resultSetToTableModel(rs1));

                    conn.close();
                    pst.close();

                } catch (Exception e1) {
                    // qmonth.setText(null);
                    JOptionPane.showMessageDialog(null, e1, "Wrong", JOptionPane.ERROR_MESSAGE);
                }
            }

        }
        if (e.getSource() == b2) {
            new AdminPage();
            dispose();
        }
    }

}
