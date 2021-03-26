import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class NewUserForm extends JFrame implements ActionListener {

    JTextField id, name, age, add, qltn, exp, pay, jdate, pass;
    JLabel header, lid, lname, lage, ladd, lqltn, lexp, lpay, ljdate, lgender, lpass;
    JButton b1, b2, b3;
    JRadioButton r1, r2;
    ButtonGroup bg;

    NewUserForm() {
        // buttons
        b1 = new JButton("Accept");
        b2 = new JButton("Clear All");
        Icon icon1 = new ImageIcon("C:\\Users\\pavan\\Downloads\\Image pack\\back.png");
        b3 = new JButton(icon1);
        b3.setBounds(700, 30, 60, 40);
        add(b3);
        b3.addActionListener(this);
        // Radio Buttons
        r1 = new JRadioButton("male");
        r2 = new JRadioButton("Female");
        bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        // TextFields
        id = new JTextField();
        name = new JTextField();
        age = new JTextField();
        add = new JTextField();
        qltn = new JTextField();
        exp = new JTextField();
        pay = new JTextField();
        jdate = new JTextField();
        pass = new JTextField();
        // Labels
        header = new JLabel("Welcome,Please Enter Your Details");
        lid = new JLabel("Username");
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
        lid.setBounds(80, 120, 80, 40);
        lname.setBounds(80, 170, 60, 40);
        lage.setBounds(80, 220, 60, 40);
        ladd.setBounds(80, 270, 60, 40);
        lqltn.setBounds(80, 320, 100, 40);
        lexp.setBounds(80, 370, 130, 40);
        lpay.setBounds(80, 420, 130, 40);
        ljdate.setBounds(80, 470, 60, 40);
        lgender.setBounds(80, 520, 60, 40);
        lpass.setBounds(80, 570, 60, 40);

        id.setBounds(250, 120, 200, 40);
        name.setBounds(250, 170, 200, 40);
        age.setBounds(250, 220, 200, 40);
        add.setBounds(250, 270, 200, 40);
        qltn.setBounds(250, 320, 200, 40);
        exp.setBounds(250, 370, 200, 40);
        pay.setBounds(250, 420, 200, 40);
        jdate.setBounds(250, 470, 200, 40);
        pass.setBounds(250, 570, 200, 40);

        r1.setBounds(250, 520, 200, 40);
        r2.setBounds(450, 520, 300, 40);
        b1.setBounds(180, 640, 80, 40);
        b2.setBounds(280, 640, 120, 40);

        b1.addActionListener(this);
        b2.addActionListener(this);
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
        add(id);
        add(name);
        add(age);
        add(add);
        add(qltn);
        add(exp);
        add(pay);
        add(jdate);
        add(r1);
        add(r2);
        add(b1);
        add(b2);
        add(pass);
        add(lpass);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 6 - this.getSize().width / 4, /* dim.height / 50 - */this.getSize().height / 1);

        setSize(800, 800);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    public static void main(String[] args) {
        new NewUserForm();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String sql = "insert into employee values(?,?,?,?,?,?,?,?,?)";
            String sql1 = "insert into userlogin values (?,?)";
            String sql3 = "insert into attendance values(?,?,0)";
            try {
                String url = "jdbc:mysql://localhost:3306/exercise";
                jdbc ob = new jdbc();
                String uname = ob.user();
                String pass = ob.pass();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, uname, pass);
                PreparedStatement pst = conn.prepareStatement(sql);
                PreparedStatement pst1 = conn.prepareStatement(sql1);
                PreparedStatement pst2 = conn.prepareStatement(sql3);

                pst.setString(1, id.getText());
                pst.setString(2, name.getText());
                pst.setInt(3, Integer.parseInt(age.getText()));
                pst.setString(5, add.getText());
                pst.setString(6, qltn.getText());
                pst.setInt(7, Integer.parseInt(exp.getText()));
                pst.setDouble(8, Double.parseDouble(pay.getText()));
                pst.setString(9, jdate.getText());

                pst1.setString(1, id.getText());
                pst1.setString(2, this.pass.getText());
                if (r1.isSelected())
                    pst.setString(4, "Male");
                if (r2.isSelected())
                    pst.setString(4, "Female");

                pst2.setString(1, id.getText());
                pst2.setString(2, name.getText());

                pst.executeUpdate();
                pst1.executeUpdate();
                // pst2.executeUpdate();
                JOptionPane.showMessageDialog(null, "DONE\nYou can Now Login", "Insertion Box",
                        JOptionPane.PLAIN_MESSAGE);

                conn.close();
                pst.close();
                new OpenPage();
                dispose();

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1, "Insertion Box", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == b2) {
            id.setText(null);
            name.setText(null);
            age.setText(null);
            add.setText(null);
            qltn.setText(null);
            exp.setText(null);
            pay.setText(null);
            jdate.setText(null);
            pass.setText(null);
            bg.clearSelection();

        }
        if (e.getSource() == b3) {
            new OpenPage();
            dispose();
        }

    }

}
