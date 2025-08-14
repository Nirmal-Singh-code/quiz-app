package quizapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    JTextField tfEmail;
    JButton btnLogin;
    String email;

    public Login() {
        setTitle("Login - Java Quiz App");
        setLayout(null);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(220, 240, 255));

        JLabel lblTitle = new JLabel("Java Quiz Login");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setBounds(180, 30, 300, 40);
        lblTitle.setForeground(Color.BLUE);
        add(lblTitle);

        JLabel lblEmail = new JLabel("Enter your Gmail:");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 18));
        lblEmail.setBounds(100, 120, 200, 25);
        add(lblEmail);

        tfEmail = new JTextField();
        tfEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        tfEmail.setBounds(250, 120, 220, 30);
        add(tfEmail);

        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 18));
        btnLogin.setBounds(230, 200, 120, 40);
        btnLogin.setBackground(Color.GREEN);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.addActionListener(this);
        add(btnLogin);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        email = tfEmail.getText().trim();
        if (email.isEmpty() || !email.endsWith("@gmail.com")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Gmail address.");
            return;
        }
        setVisible(false);
        new Rules(email);
    }

    public static void main(String[] args) {
        new Login();
    }
}
