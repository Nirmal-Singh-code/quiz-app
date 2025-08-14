package quizapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Quiz extends JFrame implements ActionListener {

    String email;
    JLabel lblQuestion, lblTimer;
    JRadioButton[] options = new JRadioButton[4];
    ButtonGroup bg;
    JButton btnNext;
    javax.swing.Timer countdownTimer;

    int qIndex = 0;
    int score = 0;
    int timeLeft = 15;

    java.util.List<String[]> questions = new ArrayList<>();
    java.util.List<String> incorrectList = new ArrayList<>();

    public Quiz(String email) {
        this.email = email;
        setTitle("Java Quiz");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(230, 240, 255));
        setLayout(null);

        lblTimer = new JLabel("Time left: 15s");
        lblTimer.setBounds(650, 20, 150, 30);
        lblTimer.setFont(new Font("Arial", Font.BOLD, 16));
        lblTimer.setForeground(Color.RED);
        add(lblTimer);

        lblQuestion = new JLabel();
        lblQuestion.setBounds(50, 50, 700, 40);
        lblQuestion.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblQuestion);

        bg = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setBounds(50, 120 + i * 40, 700, 30);
            options[i].setBackground(new Color(230, 240, 255));
            options[i].setFont(new Font("Arial", Font.PLAIN, 14));
            options[i].addActionListener(this);
            bg.add(options[i]);
            add(options[i]);
        }

        btnNext = new JButton("Next");
        btnNext.setBounds(650, 400, 100, 30);
        btnNext.setBackground(new Color(0, 150, 255));
        btnNext.setForeground(Color.WHITE);
        btnNext.setFont(new Font("Arial", Font.BOLD, 14));
        btnNext.addActionListener(this);
        add(btnNext);

        loadQuestions();
        showQuestion();

        countdownTimer = new javax.swing.Timer(1000, e -> {
            timeLeft--;
            lblTimer.setText("Time left: " + timeLeft + "s");
            if (timeLeft <= 0) {
                revealAnswer();
                moveNext();
            }
        });
        countdownTimer.start();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadQuestions() {
        questions.add(new String[]{"What is the size of int in Java?", "2 bytes", "4 bytes", "8 bytes", "Depends on OS", "4 bytes"});
        questions.add(new String[]{"Which keyword is used to inherit a class in Java?", "this", "super", "extends", "implements", "extends"});
        questions.add(new String[]{"Which of these is not a Java feature?", "Object-Oriented", "Portable", "Use of pointers", "Dynamic", "Use of pointers"});
        questions.add(new String[]{"Which method is called when an object is created?", "finalize()", "main()", "constructor", "start()", "constructor"});
        questions.add(new String[]{"Which collection class allows you to grow or shrink its size?", "Array", "ArrayList", "LinkedList", "HashMap", "ArrayList"});
        questions.add(new String[]{"Which keyword is used to prevent inheritance?", "static", "final", "private", "protected", "final"});
        questions.add(new String[]{"Which operator is used for bitwise AND?", "&", "&&", "|", "||", "&"});
        questions.add(new String[]{"Which method is used to start a thread?", "run()", "start()", "execute()", "init()", "start()"});
        questions.add(new String[]{"What is the default value of a boolean variable?", "true", "false", "null", "0", "false"});
        questions.add(new String[]{"Which class is the parent of all classes in Java?", "Object", "Class", "Superclass", "Root", "Object"});
        questions.add(new String[]{"Which exception is thrown when dividing by zero?", "NullPointerException", "ArithmeticException", "NumberFormatException", "IllegalArgumentException", "ArithmeticException"});
        questions.add(new String[]{"Which keyword is used to define a constant in Java?", "static", "final", "const", "immutable", "final"});
        questions.add(new String[]{"Which package contains the Scanner class?", "java.io", "java.util", "java.lang", "java.net", "java.util"});
        questions.add(new String[]{"What is the default value of an object reference?", "0", "null", "undefined", "empty", "null"});
        questions.add(new String[]{"Which interface does java.util.HashMap implement?", "Map", "List", "Set", "Collection", "Map"});
        questions.add(new String[]{"Which method is called before an object is garbage collected?", "main()", "finalize()", "destroy()", "dispose()", "finalize()"});
        questions.add(new String[]{"What is the output of 5 + \"5\" in Java?", "10", "55", "error", "undefined", "55"});
        questions.add(new String[]{"Which of these is synchronized?", "String", "StringBuilder", "StringBuffer", "None", "StringBuffer"});
        questions.add(new String[]{"Which exception is checked at compile time?", "RuntimeException", "IOException", "ArithmeticException", "NullPointerException", "IOException"});
        questions.add(new String[]{"Which data structure uses FIFO order?", "Stack", "Queue", "Array", "Tree", "Queue"});
    }

    private void showQuestion() {
        bg.clearSelection();
        timeLeft = 15;
        lblTimer.setText("Time left: " + timeLeft + "s");

        String[] q = questions.get(qIndex);
        lblQuestion.setText("Q" + (qIndex + 1) + ": " + q[0]);

        java.util.List<String> opts = new ArrayList<>(Arrays.asList(q[1], q[2], q[3], q[4]));
        Collections.shuffle(opts);

        for (int i = 0; i < 4; i++) {
            options[i].setText(opts.get(i));
            options[i].setForeground(Color.BLACK);
            options[i].setEnabled(true);
        }
    }

    private void revealAnswer() {
        String[] q = questions.get(qIndex);
        for (JRadioButton option : options) {
            if (option.getText().equals(q[5])) {
                option.setForeground(Color.GREEN);
            } else if (option.isSelected()) {
                option.setForeground(Color.RED);
            }
            option.setEnabled(false);
        }
    }

    private void moveNext() {
        countdownTimer.stop();
        Timer t = new Timer(1000, e -> {
            qIndex++;
            if (qIndex >= questions.size()) {
                setVisible(false);
                new Score(email, score, questions.size(), incorrectList);
            } else {
                showQuestion();
                countdownTimer.start();
            }
            ((Timer) e.getSource()).stop();
        });
        t.setRepeats(false);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNext) {
            revealAnswer();
            String[] q = questions.get(qIndex);
            for (JRadioButton option : options) {
                if (option.isSelected() && option.getText().equals(q[5])) {
                    score++;
                } else if (option.isSelected() && !option.getText().equals(q[5])) {
                    incorrectList.add("Q" + (qIndex + 1) + ": " + q[0] + " | Correct: " + q[5]);
                }
            }
            moveNext();
        } else {
            // User clicked an option
            revealAnswer();
            String[] q = questions.get(qIndex);
            for (JRadioButton option : options) {
                if (option.isSelected() && option.getText().equals(q[5])) {
                    score++;
                } else if (option.isSelected() && !option.getText().equals(q[5])) {
                    incorrectList.add("Q" + (qIndex + 1) + ": " + q[0] + " | Correct: " + q[5]);
                }
            }
            moveNext();
        }
    }
}
