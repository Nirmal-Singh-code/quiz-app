package quizapp;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Score extends JFrame {

    public Score(String email, int score, int total, List<String> incorrectList) {
        setTitle("Score - Java Quiz App");
        setSize(800, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 228, 225));
        setLayout(null);

        JLabel lblTitle = new JLabel("Quiz Completed!");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitle.setBounds(280, 20, 400, 40);
        add(lblTitle);

        JLabel lblScore = new JLabel("Score: " + score + "/" + total);
        lblScore.setFont(new Font("Arial", Font.BOLD, 22));
        lblScore.setBounds(320, 80, 300, 30);
        add(lblScore);

        double percent = (score * 100.0) / total;
        JLabel lblResult = new JLabel("Result: " + (percent >= 50 ? "PASS" : "FAIL"));
        lblResult.setFont(new Font("Arial", Font.BOLD, 20));
        lblResult.setBounds(330, 120, 300, 30);
        lblResult.setForeground(percent >= 50 ? Color.GREEN : Color.RED);
        add(lblResult);

        JTextArea mistakes = new JTextArea("Incorrect Answers:\n");
        for (String s : incorrectList) {
            mistakes.append(s + "\n");
        }
        mistakes.setFont(new Font("Arial", Font.PLAIN, 14));
        mistakes.setBounds(50, 170, 700, 200);
        mistakes.setEditable(false);
        add(mistakes);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Arial", Font.BOLD, 18));
        btnLogout.setBounds(330, 400, 120, 40);
        btnLogout.setBackground(Color.BLUE);
        btnLogout.setForeground(Color.WHITE);
        btnLogout.addActionListener(e -> {
            setVisible(false);
            new Login();
        });
        add(btnLogout);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
