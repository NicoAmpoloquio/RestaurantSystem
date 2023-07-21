//Gawa naming tatlo ni Lumaong, Enciso, Ampoloquio
package RestaurantSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CusinaryReports extends JFrame implements ActionListener{
    private ImageIcon bgImage, homeImage;
    private JLabel bgLbl, reportsLbl;
    private JButton homeBtn, inventoryBtn, salesBtn, wasteBtn;
    
    CusinaryReports(){
        setTitle("Cusinary Reports");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setVisible(true);

        bgImage  = new ImageIcon("C:\\Users\\Nico\\Documents\\NetBeansProjects\\RestaurantSystem\\src\\main\\java\\RestaurantSystem\\ResizedBG.jpg");
        bgLbl = new JLabel(bgImage);
        
        reportsLbl = new JLabel("Cusinary Reports");
        reportsLbl.setBounds(390, 120, 100, 50);
        bgLbl.add(reportsLbl);
        
        homeImage = new ImageIcon("C:\\Users\\Nico\\Documents\\NetBeansProjects\\RestaurantSystem\\src\\main\\java\\RestaurantSystem\\Home.png");
        homeBtn = new JButton (homeImage);
        homeBtn.setBounds(50, 90, homeImage.getIconWidth(), homeImage.getIconHeight());
        homeBtn.setContentAreaFilled(false);
        homeBtn.setOpaque(false);
        homeBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeBtn.addActionListener(this);
        bgLbl.add(homeBtn);
        
        inventoryBtn = new JButton ("INVENTORY");
        inventoryBtn.setBounds(370, 180, 130, 20);
        inventoryBtn.setContentAreaFilled(false);
        inventoryBtn.setFocusPainted(false);
        inventoryBtn.setOpaque(true);
        inventoryBtn.setForeground(Color.BLACK);
        inventoryBtn.setBackground(Color.WHITE);
        inventoryBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        inventoryBtn.addActionListener(this);
        bgLbl.add(inventoryBtn);
        
        salesBtn = new JButton ("SALES");
        salesBtn.setBounds(370, 210, 130, 20);
        salesBtn.setContentAreaFilled(false);
        salesBtn.setFocusPainted(false);
        salesBtn.setOpaque(true);
        salesBtn.setForeground(Color.BLACK);
        salesBtn.setBackground(Color.WHITE);
        salesBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        salesBtn.addActionListener(this);
        bgLbl.add(salesBtn);
        
        wasteBtn = new JButton ("FOOD WASTE");
        wasteBtn.setBounds(370, 240, 130, 20);
        wasteBtn.setContentAreaFilled(false);
        wasteBtn.setFocusPainted(false);
        wasteBtn.setOpaque(true);
        wasteBtn.setForeground(Color.BLACK);
        wasteBtn.setBackground(Color.WHITE);
        wasteBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        wasteBtn.addActionListener(this);
        bgLbl.add(wasteBtn);
        
        add(bgLbl);
    }
    @Override
    public void actionPerformed(ActionEvent clicked) {
        if(clicked.getSource() == homeBtn){
            dispose();
            new HomePage();
        }
        else if(clicked.getSource() == inventoryBtn){
            dispose();
            new Inventory();
        }
        else if(clicked.getSource() == salesBtn){
            dispose();
            new CusinarySales();
        }
        else if(clicked.getSource() == wasteBtn){
            dispose();
            new CuisinaryFoodWaste();
        }
    }
}
