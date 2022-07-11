package view;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.ArrayList; 

import controller.*;
import model.*;

public class menuSearch extends JFrame implements ActionListener {
    JFrame f;
    JTable table;
    JComboBox comboCategories;
    controller controller = new controller();
    public menuSearch() {
        JLabel lblCategories = new JLabel("Categories");
        lblCategories.setBounds(100, 40, 100,25);
        add(lblCategories);

        ArrayList<Category> categories = controller.getCategories();
        String[] categoriesName = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            categoriesName[i] = categories.get(i).getName();
        }
        comboCategories = new JComboBox(categoriesName);
        comboCategories.setBounds(200,40,200,25);
        add(comboCategories);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds (175, 100, 100, 40);
        add(btnSearch);
        
        JButton btnBack = new JButton("Back");
        btnBack.setBounds (275, 100, 100, 40);
        add(btnBack);
        
        btnSearch.addActionListener(this);
        btnBack.addActionListener(this);

        setTitle("Menu Search");
        setSize(550, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch(command) {
            case "Search":
                String data[][];
                ArrayList<User> dataList;
                if (comboCategories.getSelectedItem().toString().equals("Private Account")) {
                    dataList = controller.getUsers(1);
                } else if (comboCategories.getSelectedItem().toString().equals("Creator Account")) {
                    dataList = controller.getUsers(2);
                } else {
                    dataList = controller.getUsers(3);
                }
                data = new String[dataList.size()][6];
                for (int i = 0; i < dataList.size(); i++) {
                    User currentUser= dataList.get(i);
                    data[i][0]=Integer.toString(currentUser.getIdUser());
                    data[i][1]=currentUser.getName();
                    data[i][2]=currentUser.getEmail();
                    data[i][3]=currentUser.getPassword();
                    data[i][4]=Integer.toString(currentUser.getIdCategory());
                    data[i][5]=currentUser.getPhoto();
                }
                dispose();
                new menuData(data);
                break;
            case "Back":
                dispose();
                new mainMenu();
                break;
            default: 
                break;
        }
    }
}