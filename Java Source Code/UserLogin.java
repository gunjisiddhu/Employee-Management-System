import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.*;

public class UserLogin extends JFrame implements ActionListener {
    JLabel j1, j2, j3;
    JTextField f1, f2;
    JButton b1, b2, b3, b4;
    JPasswordField p1;
    JPanel jp;

    UserLogin() {
        Icon icon = new ImageIcon("C:\\Users\\pavan\\Downloads\\Image pack\\userlogin.png");
        Icon icon1 = new ImageIcon("C:\\Users\\pavan\\Downloads\\Image pack\\back.png");

        j1 = new JLabel("Username");
        j2 = new JLabel("Passsword");
        j3 = new JLabel();
        f1 = new JTextField();
        f2 = new JTextField();
        b1 = new JButton("Login");
        b2 = new JButton("Sign Up");
        p1 = new JPasswordField();
        b3 = new JButton("Go Back");
        b4 = new JButton(icon1);

        jp = new JPanel();
        b4.setBounds(500, 30, 60, 40);
        j1.setBounds(120, 260, 110, 50);
        j2.setBounds(120, 300, 110, 50);
        // j3.setBounds(290, 40, 70, 30);
        f1.setBounds(230, 260, 100, 30);
        p1.setBounds(230, 300, 100, 30);
        b1.setBounds(150, 400, 90, 35);
        b2.setBounds(250, 400, 90, 35);
        b3.setBounds(200, 450, 90, 35);

        jp.setBounds(20, 10, 500, 250);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b4.addActionListener(this);

        add(j1);
        add(j2);
        // add(b3);
        add(f1);
        add(p1);
        add(b1);
        add(b2);
        add(b4);
        j3.setIcon(icon);
        jp.add(j3);
        b3.addActionListener(this);
        add(jp);
        validate();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 3 - this.getSize().width / 3, dim.height / 6 - this.getSize().height / 5);

        setTitle("Login");
        setSize(600, 600);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String sql = "select * from userlogin where username = ? and password = ?";
            try {
                String url = "jdbc:mysql://localhost:3306/exercise";
                String uname = "root";
                String pass = "pavani10";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, uname, pass);
                PreparedStatement pst = conn.prepareStatement(sql);

                pst.setString(1, f1.getText());
                pst.setString(2, new String(p1.getPassword()));
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    // j3.setText("Login Success");
                    JOptionPane.showMessageDialog(null, "Login Success\nWelcome " + f1.getText(), "Success Page",
                            JOptionPane.INFORMATION_MESSAGE);

                    new ShowDetails(f1.getText());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, f1.getText() + " Not an Existing User", "Unsuccessful Login",
                            JOptionPane.ERROR_MESSAGE);
                }
                conn.close();
                pst.close();

            } catch (Exception e1) {

            }
        }
        if (e.getSource() == b2) {
            new NewUserForm();
            dispose();
        }
        if (e.getSource() == b3) {
            new OpenPage();
            dispose();
        }
        if (e.getSource() == b4) {
            new OpenPage();
            dispose();
        }

    }

    public static void main(String[] args) {
        new UserLogin();
    }

}
