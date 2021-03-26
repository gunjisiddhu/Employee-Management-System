
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PaySlip extends JFrame implements ActionListener {
    JComboBox month;
    JTextField year;
    JButton b1, b2;
    JLabel j1, name, lname, attendance, lattendance, salary, lsalary, qmonth, lqmonth, lmonth, lyear, netsalary,
            lnetsalary, designation, ldesignation;
    String months[] = { "month", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
    String User = "";
    int days, fin, sal, sall;

    PaySlip(String str, String pser) {
        User = pser;
        Icon icon1 = new ImageIcon("C:\\Users\\pavan\\Downloads\\Image pack\\back.png");

        j1 = new JLabel("Pay Slip");
        Font font = new Font("TimesRoman", Font.BOLD, 30);
        j1.setFont(font);

        month = new JComboBox(months);
        year = new JTextField();

        b1 = new JButton("Generate");
        b2 = new JButton(icon1);
        b2.addActionListener(this);
        b1.addActionListener(this);
        b2.setBounds(10, 30, 60, 40);
        add(b2);

        name = new JLabel("Name                     :");
        lname = new JLabel(str);
        attendance = new JLabel("Attendance          :");
        lattendance = new JLabel();
        salary = new JLabel("Monthly Salary     :");
        lsalary = new JLabel();
        qmonth = new JLabel("Month & Year       :");
        lqmonth = new JLabel();
        lmonth = new JLabel();
        lyear = new JLabel();
        netsalary = new JLabel("NET Salary          :");
        lnetsalary = new JLabel();
        designation = new JLabel("Designation          :");
        ldesignation = new JLabel();

        // left Side
        name.setBounds(80, 120, 150, 40);
        designation.setBounds(80, 170, 150, 40);
        qmonth.setBounds(80, 220, 150, 40);
        salary.setBounds(80, 270, 150, 40);
        attendance.setBounds(80, 320, 150, 40);
        netsalary.setBounds(80, 370, 150, 40);

        add(name);
        add(designation);
        add(qmonth);
        add(salary);
        add(attendance);
        add(netsalary);

        // right side
        lname.setBounds(250, 120, 150, 40);
        ldesignation.setBounds(250, 170, 150, 40);
        lqmonth.setBounds(250, 220, 150, 40);
        lsalary.setBounds(250, 270, 150, 40);
        lattendance.setBounds(250, 320, 150, 40);
        lnetsalary.setBounds(250, 370, 150, 40);

        add(lname);
        add(ldesignation);
        add(lqmonth);
        add(lsalary);
        add(lattendance);
        add(lnetsalary);

        month.setBounds(425, 20, 60, 20);
        year.setBounds(490, 20, 60, 20);
        j1.setBounds(225, 35, 200, 35);
        b1.setBounds(250, 450, 100, 30);

        add(month);
        add(year);
        add(j1);
        add(b1);

        setTitle("Pay Slip");
        setSize(600, 550);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(3);

    }

    public static void main(String[] args) {
        new PaySlip("siddhu", "bhavana");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            if ((month.getSelectedItem().equals("month")) || year.getText() == null) {
                JOptionPane.showMessageDialog(null, "Please Choose a Month And Year", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String sql2 = "select * from employee where username = ?";
                String sql = "select count(*) from attendance where username = ? and count = 1 and date>? and date<=?";
                try {
                    String url = "jdbc:mysql://localhost:3306/exercise";
                    String uname = "root";
                    String pass = "pavani10";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(url, uname, pass);
                    PreparedStatement pst = conn.prepareStatement(sql);
                    PreparedStatement pst1 = conn.prepareStatement(sql2);
                    pst1.setString(1, User);
                    ResultSet rs = pst1.executeQuery();
                    if (rs.next()) {
                        lname.setText(rs.getString(2));
                        ldesignation.setText(rs.getString(6));
                        lsalary.setText(rs.getString(8));
                        sal = rs.getInt(8);

                        lqmonth.setText("" + month.getSelectedItem() + " , " + year.getText());
                        lnetsalary.setText("" + 0);

                    }

                    if ((month.getSelectedItem().equals("1")) || (month.getSelectedItem().equals("3"))
                            || (month.getSelectedItem().equals("5")) || (month.getSelectedItem().equals("7"))
                            || (month.getSelectedItem().equals("8")) || (month.getSelectedItem().equals("10"))
                            || (month.getSelectedItem().equals("12"))) {
                        days = 31;

                        pst.setString(2, year.getText() + "-" + month.getSelectedItem() + "-01");
                        pst.setString(3, year.getText() + "-" + month.getSelectedItem() + "-31");
                    } else if ((month.getSelectedItem().equals("2"))) {
                        days = 28;
                        pst.setString(2, year.getText() + "-" + month.getSelectedItem() + "-01");
                        pst.setString(3, year.getText() + "-" + month.getSelectedItem() + "-28");
                    } else {
                        days = 30;
                        pst.setString(2, year.getText() + "-" + month.getSelectedItem() + "-01");
                        pst.setString(3, year.getText() + "-" + month.getSelectedItem() + "-30");
                    }
                    pst.setString(1, User);
                    ResultSet rs1 = pst.executeQuery();
                    rs1.next();
                    lattendance.setText("" + rs1.getInt(1));
                    fin = rs1.getInt(1);
                    sall = fin * (sal / days);
                    lnetsalary.setText("" + sall + "/-");

                    conn.close();
                    pst.close();

                } catch (Exception e1) {
                    qmonth.setText(null);
                    JOptionPane.showMessageDialog(null, e1, "Wrong", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (e.getSource() == b2) {
            new ShowDetails(User);
            dispose();
        }

    }

}
