import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

class Threads extends JFrame implements ActionListener {

    Threads() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String sql = "select * from customer";
            String url = "jdbc:mysql://localhost:3306/exercise";
            String uname = "root";
            String pass = "pavani10";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, uname, pass);
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            int i = 0;
            while (rs.next())
                i++;
            if (i == 25)
                JOptionPane.showMessageDialog(null, "NO ROOMS AVAILABLE", "Room Availability",
                        JOptionPane.ERROR_MESSAGE);
            else {
                // bookRoom
            }
            conn.close();
            pst.close();

        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, e1, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

}