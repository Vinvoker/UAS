package view;

import controller.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.awt.*;

public class menuData {
    JFrame f;
    public menuData(String[][] data){
        f = new JFrame("Data User");
        f.setSize(800, 800);

        String kolom[] = {"id","name","email","password","idCategory","photo"};

        JTable table = new JTable(data, kolom);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 0, 780, 700);

        JPanel contentPane = new JPanel(null);
        contentPane.add(sp);
        f.setContentPane(contentPane);

        JButton btnBack = new JButton("Back");
        btnBack.setBounds (350, 710, 100, 25);
        f.add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new menuSearch();
            }
        });

        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}