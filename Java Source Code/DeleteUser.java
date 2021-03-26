import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class DeleteUser extends JFrame implements ActionListener {
    JLabel j1;
    JButton b1, b2, b3;
    JTextField f1, f2;

    DeleteUser() {
        Icon icon1 = new ImageIcon("C:\\Users\\pavan\\Downloads\\Image pack\\back.png");

        b1 = new JButton("Search For User");
        b2 = new JButton("Delete User");
        b3 = new JButton(icon1);
        j1 = new JLabel("Username");
        f1 = new JTextField();
        f2 = new JTextField();

        b1.setBounds(100, 200, 180, 40);
        b2.setBounds(300, 200, 180, 40);
        b3.setBounds(500, 40, 60, 40);
        j1.setBounds(80, 100, 230, 40);
        f1.setBounds(210, 100, 230, 40);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        add(b1);
        add(b2);
        add(b3);
        add(j1);
        add(f1);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 3 - this.getSize().width / 3, dim.height / 6 - this.getSize().height / 5);

        setSize(600, 400);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(3);
    }

    public static void main(String[] args) {
        new DeleteUser();
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
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, f1.getText());
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Userfound", "Success Message",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Data Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                }

                conn.close();
                pst.close();

            } catch (Exception e1) {
                System.out.println(e1);
            }
        }
        if (e.getSource() == b2) {
            try {
                String url = "jdbc:mysql://localhost:3306/exercise";
                String uname = "root";
                String pass = "pavani10";
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, uname, pass);

                String sql = "delete from employee where username = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, f1.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Deleted User " + f1.getText(), "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                conn.close();
                pst.close();

            } catch (Exception e1) {
                System.out.println(e1);
            }
        }
        if (e.getSource() == b3) {
            new AdminPage();
            dispose();
        }

    }
}
