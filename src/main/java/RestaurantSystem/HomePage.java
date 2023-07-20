//Gawa ni Nico Ampoloquio
package RestaurantSystem;

import java.awt.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class HomePage extends JFrame implements ActionListener{
    private ImageIcon bgImage, logoImage;
    private JLabel bgLbl, logoLbl;
    private JButton takeorderBtn, reportsButton;
    
    public HomePage(){
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setVisible(true);
        setLocationRelativeTo(null);

        bgImage  = new ImageIcon("C:\\Users\\Nico\\Documents\\NetBeansProjects\\RestaurantSystem\\src\\main\\java\\RestaurantSystem\\ResizedBG.jpg");
        bgLbl = new JLabel(bgImage);
        
        logoImage = new ImageIcon("C:\\Users\\Nico\\Documents\\NetBeansProjects\\RestaurantSystem\\src\\main\\java\\RestaurantSystem\\Logo100x100.png");
        logoLbl = new JLabel(logoImage);
        logoLbl.setBounds(387, 150, logoImage.getIconWidth(), logoImage.getIconHeight());
        bgLbl.add(logoLbl);
        
        takeorderBtn = new JButton("TAKE ORDERS");
        takeorderBtn.setBounds(370, 270, 130, 20);
        takeorderBtn.setContentAreaFilled(false);
        takeorderBtn.setFocusPainted(false);
        takeorderBtn.setOpaque(true);
        takeorderBtn.setForeground(Color.BLACK);
        takeorderBtn.setBackground(Color.WHITE);
        takeorderBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        takeorderBtn.addActionListener(this);
        bgLbl.add(takeorderBtn);
        
        reportsButton = new JButton ("REPORTS");
        reportsButton.setBounds(370, 300, 130, 20);
        reportsButton.setContentAreaFilled(false);
        reportsButton.setFocusPainted(false);
        reportsButton.setOpaque(true);
        reportsButton.setForeground(Color.BLACK);
        reportsButton.setBackground(Color.WHITE);
        reportsButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        reportsButton.addActionListener(this);
        bgLbl.add(reportsButton);
        
        add(bgLbl);
    }
    
@Override
    public void actionPerformed(ActionEvent clicked){
        if(clicked.getSource() == takeorderBtn){
            dispose();
            new MainMenu();
        }
        else if(clicked.getSource() == reportsButton){
            dispose();
            new CusinaryReports();
        }
    }
}