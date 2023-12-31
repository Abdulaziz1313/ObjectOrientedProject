
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage implements ActionListener {

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("username:");
    JLabel userPasswordLabel = new JLabel("password:");
    JLabel messageLabel = new JLabel();

    HashMap<String,String> logininfo = new HashMap<String,String>();

    LoginPage(HashMap<String,String> loginInfoOriginal){
    	

        logininfo = loginInfoOriginal;

        userIDLabel.setBounds(50,100,75,25);
        userPasswordLabel.setBounds(50,150,75,25);

        messageLabel.setBounds(125,250,250,35);
        messageLabel.setFont(new Font(null,Font.ITALIC,25));

        userIDField.setBounds(125,100,200,25);
        userPasswordField.setBounds(125,150,200,25);

        loginButton.setBounds(125,200,100,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        resetButton.setBounds(225,200,100,25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(messageLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(resetButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
    	final String DATABASE_URL = "jdbc:mysql://localhost/loginpage";
        if(e.getSource()==resetButton)
        {
            userIDField.setText("");
            userPasswordField.setText("");
        }
        if(e.getSource()==loginButton)
        {
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            // Establish a connection to the MySQL database
            try (Connection conn = DriverManager.getConnection(DATABASE_URL,"root","Aziz.1313")) {

                // Prepare a SQL statement to retrieve the password associated with the user ID
                String sql = "SELECT password FROM login WHERE userID = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, userID);

                    // Execute the query and get the result set
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        // Check if the retrieved password matches the password entered by the user
                        String storedPassword = rs.getString("password");
                        if (password.equals(storedPassword)) {
                            messageLabel.setForeground(Color.green);
                            messageLabel.setText("Login Successful");
                            frame.dispose();
                            Main main = new Main();
                        } else {
                            messageLabel.setForeground(Color.red);
                            messageLabel.setText("Wrong password");
                        }
                    } else {
                        messageLabel.setForeground(Color.red);
                        messageLabel.setText("username not found");
                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

}
