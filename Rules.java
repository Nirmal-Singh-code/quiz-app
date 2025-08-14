package quizapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rules extends JFrame implements ActionListener {

    String email;
    JButton btnStart, btnLogout;

    public Rules(String email) {
        this.email = email;

        setTitle("Rules - Java Quiz App");
        setLayout(null);
        setSize(700, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 250, 205));

        JLabel lblWelcome = new JLabel("Welcome " + email);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 20));
        lblWelcome.setBounds(50, 20, 600, 25);
        add(lblWelcome);

        JTextArea rules = new JTextArea(
                "1. You will have 15 seconds for each question.\n"
                        + "2. Once you select an answer, it will be locked.\n"
                        + "3. Questions and answers are randomized.\n"
                        + "4. Green = correct answer, Red = your wrong choice.\n"
                        + "5. Quiz will auto-move to the next question.\n"
                        + "6. Final score will be displayed with mistakes."
        );
        rules.setFont(new Font("Arial", Font.PLAIN, 16));
        rules.setBounds(50, 70, 600, 200);
        rules.setEditable(false);
        rules.setOpaque(false);
        add(rules);

        btnStart = new JButton("Start Quiz");
        btnStart.setFont(new Font("Arial", Font.BOLD, 18));
        btnStart.setBounds(150, 350, 150, 40);
        btnStart.setBackground(Color.BLUE);
        btnStart.setForeground(Color.WHITE);
        btnStart.addActionListener(this);
        add(btnStart);

        btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Arial", Font.BOLD, 18));
        btnLogout.setBounds(400, 350, 150, 40);
        btnLogout.setBackground(Color.RED);
        btnLogout.setForeground(Color.WHITE);
        btnLogout.addActionListener(this);
        add(btnLogout);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnStart) {
            setVisible(false);
            new Quiz(email);
        } else if (ae.getSource() == btnLogout) {
            setVisible(false);
            new Login();
        }
    }
}
