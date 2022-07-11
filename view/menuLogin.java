package view;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import controller.*;

public class menuLogin extends JFrame {
    JFrame f;
    public menuLogin() {
        JLabel lblLogo = new JLabel();
        ImageIcon icon = new ImageIcon("assets/logo.jpeg");
        lblLogo.setIcon(icon);
        lblLogo.setBounds(10,10,200,200);
        add(lblLogo);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(260, 50, 100,25);
        add(lblEmail);

        JTextField textEmail = new JTextField(16);
        textEmail.setBounds(360, 50, 200, 25);
        add(textEmail);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(260, 100, 100,25);
        add(lblPassword);

        JPasswordField textPassword = new JPasswordField(16);
        textPassword.setBounds(360, 100, 200, 25);
        add(textPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds (300, 150, 100, 40);
        add(btnLogin);
        
        JButton btnBack = new JButton("Back");
        btnBack.setBounds (400, 150, 100, 40);
        add(btnBack);
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(textPassword.getPassword());
                if (textEmail.getText().isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Ada Data Kosong!");
                } else {
                    if (controller.logIn(textEmail.getText(), password)) {
                        JOptionPane.showMessageDialog(null, "Login Berhasil!");
                        dispose();
                        new menuSearch();
                    } else {
                        JOptionPane.showMessageDialog(null, "Username/Password Salah!");
                    }
                }
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new mainMenu();
            }
        });

        setTitle("Menu Login");
        setSize(600, 270);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}