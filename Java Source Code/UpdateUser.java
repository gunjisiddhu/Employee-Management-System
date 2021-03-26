import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateUser extends JFrame implements ActionListener {

    JTextField id, name, age, add, qltn, exp, pay, jdate, pass;
    JLabel gender;
    JLabel header, lid, lname, lage, ladd, lqltn, lexp, lpay, ljdate, lgender, lpass;
    JButton b1, b2, b3;
    JRadioButton r1, r2;

    UpdateUser() {
        // buttons
        b1 = new JButton("Search");
        b2 = new JButton("Update");
        Icon icon = new ImageIcon("C:\\Users\\pavan\\Downloads\\Image pack\\back.png");
        b3 = new JButton(icon);

        // TextFields
        id = new JTextField();
        name = new JTextField();
        age = new JTextField();
        add = new JTextField();
        qltn = new JTextField();
        exp = new JTextField();
        pay = new JTextField();
        jdate = new JTextField();
        gender = new JLabel();
        pass = new JTextField();
        // Labels
        header = new JLabel("Please Enter Details to Update");
        lid = new JLabel("User ID");
        lname = new JLabel("Name");
        lage = new JLabel("Age");
        ladd = new JLabel("Address");
        lqltn = new JLabel("Qualification");
        lexp = new JLabel("Experience");
        lpay = new JLabel("Salary Amount");
        ljdate = new JLabel("Join Date");
        lgender = new JLabel("Gender");
        lpass = new JLabel("Password");
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
        lpass.setBounds(80, 570, 100, 40);

        id.setBounds(250, 120, 200, 40);
        name.setBounds(250, 170, 200, 40);
        age.setBounds(250, 220, 200, 40);
        add.setBounds(250, 270, 200, 40);
        qltn.setBounds(250, 320, 200, 40);
        exp.setBounds(250, 370, 200, 40);
        pay.setBounds(250, 420, 200, 40);
        jdate.setBounds(250, 470, 200, 40);
        gender.setBounds(250, 520, 200, 40);
        pass.setBounds(250, 570, 200, 40);

        b1.setBounds(180, 630, 80, 40);
        b2.setBounds(280, 630, 120, 40);
        b3.setBounds(700, 30, 60, 40);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
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
        add(lpass);

        add(id);
        add(name);
        add(age);
        add(add);
        add(qltn);
        add(exp);
        add(pay);
        add(jdate);
        add(gender);
        add(pass);

        add(b1);
        add(b2);

        setSize(800, 800);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    public static void main(String[] args) {
        new UpdateUser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String sql = "select * from employee where username = ?";
            try {
                String url = "jdbc:mysql://localhost:3306/exercise";
                jdbc ob = new jdbc();
                String uname = ob.user();
                String pass = ob.pass();
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, uname, pass);
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, id.getText());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    id.setText(rs.getString(1));
                    name.setText(rs.getString(2));
                    age.setText(rs.getString(3));
                    gender.setText(rs.getString(4));
                    add.setText(rs.getString(5));
                    qltn.setText(rs.getString(6));
                    exp.setText(rs.getString(7));
                    pay.setText(rs.getString(8));
                    jdate.setText(rs.getString(9));

                    // JOptionPane.showMessageDialog(null, "DONE", "Insertion Box",
                    // JOptionPane.PLAIN_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null, "User Data Not Found", "Error..!", JOptionPane.ERROR_MESSAGE);
                }
                conn.close();
                pst.close();

            } catch (Exception e1) {
                System.out.println(e1);
            }

        } else if (e.getSource() == b2) {
            if (pass.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Choose a password", "Password Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                /*
                 * String sql = "insert into employee values(?,?,?,?,?,?,?,?,?)"; String sql1 =
                 * "delete from employee where username = ?"; String sq3 =
                 * "insert into userlogin values(?,?)";
                 */
                String sql = "update  employee set ename = ?,age = ?,gender = ?,address = ?,qualification = ? , experience = ? , pay = ? , joinDate = ? where username = ?;";
                String sql1 = "update userlogin set password = ? where username = ?";
                try {
                    String url = "jdbc:mysql://localhost:3306/exercise";
                    jdbc ob = new jdbc();
                    String uname = ob.user();
                    String pass = ob.pass();
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection(url, uname, pass);
                    PreparedStatement pst = conn.prepareStatement(sql);
                    PreparedStatement pst1 = conn.prepareStatement(sql1);

                    pst.setString(9, id.getText());
                    pst.setString(1, name.getText());
                    pst.setInt(2, Integer.parseInt(age.getText()));
                    pst.setString(3, gender.getText());
                    pst.setString(4, add.getText());
                    pst.setString(5, qltn.getText());
                    pst.setInt(6, Integer.parseInt(exp.getText()));
                    pst.setDouble(7, Double.parseDouble(pay.getText()));
                    pst.setString(8, jdate.getText());

                    pst.executeUpdate();

                    pst1.setString(1, this.pass.getText());
                    pst1.setString(2, id.getText());
                    pst1.executeUpdate();

                    JOptionPane.showMessageDialog(null, "DONE", "Insertion Box", JOptionPane.PLAIN_MESSAGE);

                    conn.close();
                    pst.close();

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, e1, "Insertion Box", JOptionPane.PLAIN_MESSAGE);
                }

            }
        }
        if (e.getSource() == b3) {
            new AdminPage();
            dispose();
        }

    }

}
