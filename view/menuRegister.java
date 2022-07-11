package view;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

import controller.*;
import model.*;

public class menuRegister extends JFrame {
    JFrame f;
    ArrayList<String> listPath = new ArrayList<String>();
    public menuRegister() {
        listPath.add("");
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(100, 50, 100,25);
        add(lblEmail);

        JTextField textEmail = new JTextField(16);
        textEmail.setBounds(200, 50, 200, 25);
        add(textEmail);

        JLabel lblNama = new JLabel("Nama");
        lblNama.setBounds(100, 100, 100,25);
        add(lblNama);

        JTextField textNama = new JTextField(16);
        textNama.setBounds(200, 100, 200, 25);
        add(textNama);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(100, 150, 100,25);
        add(lblPassword);

        JPasswordField textPassword = new JPasswordField(16);
        textPassword.setBounds(200, 150, 200, 25);
        add(textPassword);
        
        JLabel lblCategories = new JLabel("Categories");
        lblCategories.setBounds(100, 200, 100,25);
        add(lblCategories);

        controller controller = new controller();
        ArrayList<Category> categories = controller.getCategories();
        String[] categoriesName = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            categoriesName[i] = categories.get(i).getName();
        }
        JComboBox comboCategories = new JComboBox(categoriesName);
        comboCategories.setBounds(200,200,200,25);
        add(comboCategories);

        JLabel lblPhoto = new JLabel("Photo");
        lblPhoto.setBounds(100, 250, 100,25);
        add(lblPhoto);

        JLabel lPathPhoto = new JLabel();
        lPathPhoto.setBounds(200,275,200,25);
        add(lPathPhoto);
        
        JMenuItem openPhoto;
        openPhoto = new JMenuItem("Pilih Photo");
        openPhoto.setBounds(200, 250, 200, 25);
        openPhoto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser choosefilePhoto;
                if(e.getSource()==openPhoto) {
                    choosefilePhoto = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
                    choosefilePhoto.addChoosableFileFilter(filter);
                    int resultPhoto = choosefilePhoto.showSaveDialog(null);
                    if (resultPhoto == JFileChooser.APPROVE_OPTION) {
                        File filePhoto = choosefilePhoto.getSelectedFile();
                        lPathPhoto.setText(filePhoto.getPath());
                        listPath.set(0, filePhoto.getPath());
                    } else if (resultPhoto == JFileChooser.CANCEL_OPTION) {
                        System.out.println("No File Select");
                    }
                }
            }  
        });
        add(openPhoto);

        JButton btnRegister = new JButton("Register");
        btnRegister.setBounds (175, 325, 100, 40);
        add(btnRegister);
        
        JButton btnBack = new JButton("Back");
        btnBack.setBounds (275, 325, 100, 40);
        add(btnBack);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(textPassword.getPassword());
                if (textEmail.getText().isEmpty() || password.isEmpty() || textNama.getText().isEmpty() || listPath.get(0) == "") {
                    JOptionPane.showMessageDialog(null, "Ada Data Kosong!");
                } else {
                    User user = new User(0, textNama.getText(), textEmail.getText(), password, comboCategories.getSelectedIndex()+1, listPath.get(0));
                    boolean sukses = controller.insertUser(user);
                    if (sukses) {
                        JOptionPane.showMessageDialog(null, "Registrasi Berhasil");
                        dispose();
                        new mainMenu();
                    } else {
                        JOptionPane.showMessageDialog(null, "Registrasi Gagal");
                        dispose();
                        new mainMenu();
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

        setTitle("Menu Register");
        setSize(550, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}