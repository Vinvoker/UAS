package view;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*; 

public class mainMenu extends JFrame implements ActionListener {
    JFrame f;
    public mainMenu() {
        JButton btnLogin = new JButton("Login Pengguna");
        btnLogin.setBounds (100, 80, 400, 40);
        add(btnLogin);
        
        JButton btnRegister = new JButton("Registrasi Pengguna Baru");
        btnRegister.setBounds (100, 140, 400, 40);
        add(btnRegister);
        
        JButton btnSearch = new JButton("Lihat Data Pengguna Berdasarkan Kategori Dipilih");
        btnSearch.setBounds (100, 200, 400, 40);
        add(btnSearch);
        
        btnLogin.addActionListener(this);
        btnRegister.addActionListener(this);
        btnSearch.addActionListener(this);

        setTitle("Main Menu");
        setSize(600, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command) {
            case "Login Pengguna":
                dispose();
                new menuLogin();
                break;
            case "Registrasi Pengguna Baru":
                dispose();
                new menuRegister();
                break;
            case "Lihat Data Pengguna Berdasarkan Kategori Dipilih":
                dispose();
                new menuSearch();
                break;
            default: 
                break;
        }
        
    }
}