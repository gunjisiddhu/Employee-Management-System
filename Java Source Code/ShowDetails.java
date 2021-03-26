import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.awt.*;

public class ShowDetails extends JFrame implements ActionListener {
    JTextField id;
    JLabel header, lid, lname, lage, ladd, lqltn, lexp, lpay, ljdate, lgender, name, age, add, qltn, exp, pay, jdate,
            gender, attendance, lattendance;
    JButton b1, b2, b3;
    JRadioButton r1, r2;
    int count = 0;

    ShowDetails(String strk) {
        // buttons
        b1 = new JButton("Search");
        b2 = new JButton("PaySlip");
        b3 = new JButton("Logout");
        // Radio Buttons
        r1 = new JRadioButton("male");
        r2 = new JRadioButton("Female");
        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        // TextFields
        id = new JTextField(strk);
        name = new JLabel();
        age = new JLabel();
        add = new JLabel();
        qltn = new JLabel();
        exp = new JLabel();
        pay = new JLabel();
        jdate = new JLabel();
        gender = new JLabel();
        attendance = new JLabel();
        // Labels
        header = new JLabel("Welcome,Please Enter Your Details");
        lid = new JLabel("User ID");
        lname = new JLabel("Name");
        lage = new JLabel("Age");
        ladd = new JLabel("Address");
        lqltn = new JLabel("Qualification");
        lexp = new JLabel("Experience");
        lpay = new JLabel("Salary Amount");
        ljdate = new JLabel("Join Date");
        lgender = new JLabel("Gender");
        lattendance = new JLabel("Attendance");
        // Bounds
        header.setBounds(150, 75, 300, 30);
        lid.setBounds(80, 120, 60, 40);
        lname.setBounds(80, 170, 60, 40);
        lage.setBounds(80, 220, 60, 40);
        ladd.setBounds(80, 270, 60, 40);
        lqltn.setBounds(80, 320, 100, 40);
        lexp.setBounds(80, 370, 130, 40);
        lpay.setBounds(80, 420, 130, 40);
        ljdate.setBounds(80, 470, 60, 40);
        lgender.setBounds(80, 520, 60, 40);
        lattendance.setBounds(80, 570, 100, 40);
        b3.setBounds(650, 30, 100, 30);

        id.setBounds(250, 120, 200, 40);
        name.setBounds(250, 170, 200, 40);
        age.setBounds(250, 220, 200, 40);
        add.setBounds(250, 270, 200, 40);
        qltn.setBounds(250, 320, 200, 40);
        exp.setBounds(250, 370, 200, 40);
        pay.setBounds(250, 420, 200, 40);
        jdate.setBounds(250, 470, 200, 40);
        gender.setBounds(250, 520, 200, 40);
        r1.setBounds(250, 520, 200, 40);
        r2.setBounds(450, 520, 300, 40);
        b1.setBounds(180, 650, 80, 40);
        b2.setBounds(280, 650, 120, 40);
        attendance.setBounds(250, 570, 200, 40);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b2.setEnabled(false);

        add(b3);
        add(header);
        add(lid);
        add(lname);
        add(lage);
        add(ladd);
        add(lqltn);
        add(lexp);
        add(lpay);
        add(ljdate);
        add(lgender);
        add(lattendance);
        add(id);
        add(name);
        add(age);
        add(add);
        add(qltn);
        add(exp);
        add(pay);
        add(jdate);
        add(gender);
        add(b1);
        add(b2);
        add(attendance);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 6 - this.getSize().width / 4, dim.height / 50 - this.getSize().height / 1);

        setTitle("User Details");
        setSize(800, 800);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    public static void main(String[] args) {
        new ShowDetails("ok");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {

            try {
                String url = "jdbc:mysql://localhost:3306/exercise";
                String uname = "root";
                String pass = "pavani10";
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, uname, pass);

                String sql = "select * from employee where username = ?";
                String sql2 = "select * from attendance where username = ?";

                PreparedStatement pst = conn.prepareStatement(sql);
                PreparedStatement pst1 = conn.prepareStatement(sql2);

                pst.setString(1, id.getText());
                pst1.setString(1, id.getText());
                ResultSet rs = pst.executeQuery();
                ResultSet rs1 = pst1.executeQuery();
                if (rs1.next()) {
                    if (rs1.getInt(3) == 1)
                        count++;
                    while (rs1.next())
                        if (rs1.getInt(3) == 1)
                            count++;

                }

                if (rs.next()) {
                    b2.setEnabled(true);
                    rs1.next();
                    name.setText(rs.getString(2));
                    age.setText("" + rs.getInt(3));
                    add.setText(rs.getString(5));
                    qltn.setText(rs.getString(6));
                    exp.setText("" + rs.getString(7));
                    pay.setText("" + rs.getString(8));
                    jdate.setText(rs.getString(9));
                    gender.setText(rs.getString(4));
                    attendance.setText("" + count);
                } else {
                    JOptionPane.showMessageDialog(null, "Data Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                }

                conn.close();
                pst.close();

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1, "Error", JOptionPane.ERROR_MESSAGE);

            }

        } else if (e.getSource() == b3) {
            new UserLogin();
            dispose();
        }
        if (e.getSource() == b2) {
            new PaySlip(name.getText(), id.getText());
            dispose();

        }

    }
}
