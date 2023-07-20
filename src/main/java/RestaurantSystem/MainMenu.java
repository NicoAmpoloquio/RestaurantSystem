//Inisip ni Enciso yung UI, tapos pinagtulungan naming tatlo nila Lumaong, Ampoloquio at Enciso

package RestaurantSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener {
    private JPanel buttonPanel,areaPanel, totalPanel,buttonPanel1;
    private JButton btnHome, btnMain,btnAppetizer,btnSalad,btnDessert,btnDrinks,btnViewOrder,btnVoid,btnCancel,btnTotal;
    private JTextArea orderArea, totalArea;
    private JLabel lblBackground;
    private ImageIcon homeImage;
    //MainCourse
    private int grbPrice = 2000, spPrice = 1580, sprPrice = 1330, bfwcsPrice = 700, bsbPrice = 1650;
    //Appetizers
    private int pdPrice = 150, sppPrice = 175, mmwsPrice = 340;
    //Dessert
    private int ctPrice = 178, clcPrice = 230, bcPrice = 315, lpPrice = 100, uhPrice = 60;
    //Salad
    private int psPrice = 247, vsPrice = 299, fsPrice = 250;
    //Drinks
    private int bwPrice = 40, msPrice = 150, bsPrice = 150, scPrice = 99, ccPrice = 60;
    
    private Map<String,Integer> program2Order;
     
    MainMenu(){
        program2Order = new HashMap<>();
        panelButton();
        areaPanel();
        buttonPanel1();
        totalPanel();
        
        setSize(900, 650);
        setTitle("Main Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        ImageIcon imgIcon = new ImageIcon("C:\\Users\\Nico\\Documents\\NetBeansProjects\\RestaurantSystem\\src\\main\\java\\RestaurantSystem\\ResizedBG.jpg");
        
        Image img = imgIcon.getImage();
        Image temp_img = img.getScaledInstance(900, 650, Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(temp_img);
        lblBackground = new JLabel("",imgIcon,JLabel.CENTER);
        
        homeImage = new ImageIcon("C:\\Users\\Nico\\Documents\\NetBeansProjects\\RestaurantSystem\\src\\main\\java\\RestaurantSystem\\Home.png");
        btnHome = new JButton (homeImage);
        btnHome.setBounds(50, 20, homeImage.getIconWidth(), homeImage.getIconHeight());
        btnHome.setContentAreaFilled(false);
        btnHome.setOpaque(false);
        btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnHome.addActionListener(this);
        lblBackground.add(btnHome);
        
        add(lblBackground);
        
        setVisible(true);
    }
    
    public void panelButton(){
        Font f1 = new Font("Open Sans",Font.BOLD,25);
        
       buttonPanel = new JPanel();
       buttonPanel.setBackground(new Color(0,0,0,50));
       buttonPanel.setBounds(50,60,250,500);
       buttonPanel.setLayout(new GridLayout(6,0,2,2));
       buttonPanel.setFont(f1);
       
       btnMain = new JButton("Main Courses");
       btnMain.setBackground(Color.YELLOW);
       btnMain.addActionListener(this);
       
       btnAppetizer = new JButton("Appetizer");
       btnAppetizer.setBackground(Color.YELLOW);
       btnAppetizer.addActionListener(this);
       
       btnSalad = new JButton("Salad");
       btnSalad.setBackground(Color.YELLOW);
       btnSalad.addActionListener(this);
       
       btnDessert = new JButton("Dessert");
       btnDessert.setBackground(Color.YELLOW);
       btnDessert.addActionListener(this);
       
       btnDrinks = new JButton("Drinks");
       btnDrinks.setBackground(Color.YELLOW);
       btnDrinks.addActionListener(this);
       
       btnViewOrder = new JButton("View Order");
       btnViewOrder.setBackground(Color.YELLOW);
       btnViewOrder.addActionListener(this);
       
       buttonPanel.add(btnMain);
       buttonPanel.add(btnAppetizer);
       buttonPanel.add(btnSalad);
       buttonPanel.add(btnDessert);
       buttonPanel.add(btnDrinks);
       buttonPanel.add(btnViewOrder);
       
       add(buttonPanel);
    }
    
    public void areaPanel(){
        areaPanel = new JPanel();
        areaPanel.setBackground(Color.PINK);
        areaPanel.setBounds(330,60,500,200);
        areaPanel.setLayout(new BorderLayout());
        
        orderArea = new JTextArea();
        orderArea.setEditable(false);
        
        areaPanel.add(orderArea,BorderLayout.CENTER);
        add(areaPanel);
    }
    
    public void buttonPanel1(){
        buttonPanel1 = new JPanel();
        buttonPanel1.setBackground(new Color(0,0,0,50));
        buttonPanel1.setBounds(330,260,500,50);
        buttonPanel1.setLayout(new GridLayout(0,3,5,5));
        buttonPanel1.setFont(new Font("Open Sans",Font.BOLD,25));
        
        btnVoid = new JButton("Void");
        btnVoid.setBackground(Color.YELLOW);
        btnVoid.addActionListener(this);
        
        btnCancel = new JButton("Cancel");
        btnCancel.setBackground(Color.YELLOW);
        btnCancel.addActionListener(this);
        
        btnTotal = new JButton("Total");
        btnTotal.setBackground(Color.YELLOW);
        btnTotal.addActionListener(this);
        
        buttonPanel1.add(btnVoid);
        buttonPanel1.add(btnCancel);
        buttonPanel1.add(btnTotal);
        
        add(buttonPanel1);
    }

    public void totalPanel(){
        totalPanel = new JPanel();
        totalPanel.setBackground(Color.PINK);
        totalPanel.setBounds(330,370,500,190);
        totalPanel.setLayout(new BorderLayout());
        
        totalArea = new JTextArea();
        totalArea.setEditable(false);
        
        totalPanel.add(totalArea,BorderLayout.CENTER);
        add(totalPanel);
    }
    
    @Override
    public void actionPerformed(ActionEvent clicked) {
        if(clicked.getSource() == btnMain){
            showMainCourse();
        }
        else if (clicked.getSource() == btnHome) {
            new HomePage();
            
        }
        else if (clicked.getSource() == btnAppetizer){
            showAppetizers();
        }
        else if (clicked.getSource() == btnSalad){
            showSalad();
        }
        else if (clicked.getSource() == btnDessert){
            showDessert();
        }
        else if (clicked.getSource() == btnDrinks){
            showDrinks();
        }
        else if (clicked.getSource() == btnViewOrder) {
            showViewOrder();
        }
        else if (clicked.getSource() == btnVoid) {
            orderArea.setText("");
        }
        else if (clicked.getSource() == btnCancel) {
            orderArea.setText("Order Cancelled.");
        }
        else if (clicked.getSource() == btnTotal) {
        StringBuilder receipt = new StringBuilder("Receipt:\n\n");
        int overallTotal = 0;
        
        //Date
        Date currentDate = new Date();
        receipt.append("Date: ").append(currentDate.toString()).append("\n\n");


        if (program2Order.isEmpty()) {
            receipt.append("No items in the order.");
        } else {
            for (Map.Entry<String, Integer> entry : program2Order.entrySet()) {
                String itemName = entry.getKey();
                int quantity = entry.getValue();
                int itemTotal = calculateItemTotal(itemName, quantity);
                receipt.append(itemName).append(" (x").append(quantity).append(") - P").append(itemTotal).append("\n");
                overallTotal += itemTotal;
            }
            receipt.append("\nOverall Total: P").append(overallTotal);
        }

        totalArea.setText(receipt.toString());
        }
    }
    
    public void showMainCourse() {
        String[] mainCourse = {"Grilled Roast Beef", "Seafood Pack", "Spicy Pork Rim", "Baked Fish with Chili Sauce", "Buttered Shrimp Bucket"};
        String selectedMainCourse = (String) JOptionPane.showInputDialog(this, "Select Main Course", "Main Course", JOptionPane.PLAIN_MESSAGE, null, mainCourse, mainCourse[0]);
        if (selectedMainCourse != null) {
            int quantity = getQuantity();
            program2Order.put(selectedMainCourse, quantity);
            orderArea.setText(quantity + " " + selectedMainCourse);
            showMessageDialog("Order Added.");
        } else {
            //Area.setText("Order Cancelled");
            showMessageDialog("Order Cancelled.");
        }
    }

    public void showAppetizers() {
        String[] appetizers = {"Pork Dumplings", "Spinach Pancakes", "Mild Mushroom with Shrimp"};
        String selectedAppetizer = (String) JOptionPane.showInputDialog(this, "Select Appetizers", "Appetizers", JOptionPane.PLAIN_MESSAGE, null, appetizers, appetizers[0]);
        if (selectedAppetizer != null) {
            int quantity = getQuantity();
            program2Order.put(selectedAppetizer, quantity);
            orderArea.setText(quantity + " " + selectedAppetizer);
            showMessageDialog("Order Added");
        } else {
            showMessageDialog("Order Cancelled.");
        }
    }

    public void showSalad(){
        String[] Salad = {"Prawn Salad","Vegetable Salad","Fruit Salad"};
        String selectedSalad = (String)JOptionPane.showInputDialog(this, "Select Salad", "Salad", JOptionPane.PLAIN_MESSAGE,null,Salad,Salad[0]);
        if(selectedSalad != null){
            int quantity = getQuantity();
            program2Order.put(selectedSalad, quantity);
            orderArea.setText(quantity + " " + selectedSalad);
            showMessageDialog("Order Added.");
        } else {
            showMessageDialog("Order Cancelled.");
        }
    }
    public void showDessert(){
        String[] Dessert = {"Coffee Tirimasu","Choco Lava Cake","Blueberry Cheesecake","Leche Plan","Ube Halaya"};
        String selectedDessert = (String)JOptionPane.showInputDialog(this, "Select Dessert", "Dessert", JOptionPane.PLAIN_MESSAGE,null,Dessert,Dessert[0]);
        if(selectedDessert != null){
            int quantity = getQuantity();
            program2Order.put(selectedDessert, quantity);
            orderArea.setText(quantity + " " + selectedDessert);
            showMessageDialog("Order Added.");
        } else {
            showMessageDialog("Order Cancelled.");
        }
    }
    public void showDrinks(){
        String[] Drinks = {"Bottled Water","Mango Shake","Buko Shake","Sprite in Can","Coke in Can"};
        String selectedDrinks = (String)JOptionPane.showInputDialog(this, "Select Main Course", "Main Course", JOptionPane.PLAIN_MESSAGE,null,Drinks,Drinks[0]);
        if(selectedDrinks != null){
            int quantity = getQuantity();
            program2Order.put(selectedDrinks, quantity);
            orderArea.setText(quantity + " " + selectedDrinks);
            showMessageDialog("Order Added.");
        } else {
            showMessageDialog("Order Cancelled.");
        }
    }
    
    public void showViewOrder() {
        StringBuilder orderSummary = new StringBuilder("Current Order:\n");
        if (program2Order.isEmpty()) {
            orderSummary.append("No items in the order.");
        } else {
            for (Map.Entry<String, Integer> entry : program2Order.entrySet()) {
                String itemName = entry.getKey();
                int quantity = entry.getValue();
                orderSummary.append(quantity).append(" x ").append(itemName).append("\n");
            }
        }
        orderArea.setText(orderSummary.toString());
    }
    
    private int getQuantity(){
        String quantityString = JOptionPane.showInputDialog(this,"Enter a quantity","Quantity",JOptionPane.PLAIN_MESSAGE);
        orderArea.setText(quantityString);
        try{
            return Integer.parseInt(quantityString);
            
        }catch(NumberFormatException e){
            return 0;
        }
    }
    
    private int calculateItemTotal(String itemName, int quantity) {
        int itemTotal = 0;
        switch (itemName) {
            case "Grilled Roast Beef":
                itemTotal = grbPrice * quantity;
                break;
            case "Seafood Pack":
                itemTotal = spPrice * quantity;
                break;
            case "Spicy Pork Rim":
                itemTotal = sprPrice * quantity;
                break;
            case "Baked Fish with Chili Sauce":
                itemTotal = bfwcsPrice * quantity;
                break;
            case "Buttered Shrimp Bucket":
                itemTotal = bsbPrice * quantity;
                break;
            case "Pork Dumplings":
                itemTotal = pdPrice * quantity;
                break;
            case "Spinach Pancakes":
                itemTotal = sppPrice * quantity;
                break;
            case "Mild Mushroom with Shrimp":
                itemTotal = mmwsPrice * quantity;
                break;
            case "Prawn Salad":
                itemTotal = psPrice * quantity;
                break;
            case "Vegetable Salad":
                itemTotal = vsPrice * quantity;
                break;
            case "Fruit Salad":
                itemTotal = fsPrice * quantity;
                break;
            case "Coffee Tirimasu":
                itemTotal = ctPrice * quantity;
                break;
            case "Choco Lava Cake":
                itemTotal = clcPrice * quantity;
                break;
            case "Blueberry Cheesecake":
                itemTotal = bcPrice * quantity;
                break;
            case "Leche Plan":
                itemTotal = lpPrice * quantity;
                break;
            case "Ube Halaya":
                itemTotal = uhPrice * quantity;
                break;
            case "Bottled Water":
                itemTotal = bwPrice * quantity;
                break;
            case "Buko Shake":
                itemTotal = bsPrice * quantity;
                break;
            case "Mango Shake":
                itemTotal = msPrice * quantity;
                break;
            case "Sprite in Can":
                itemTotal = scPrice * quantity;
                break;
            case "Coke in Can":
                itemTotal = ccPrice * quantity;
                break;
            default:
                break;
        }
        return itemTotal;
    }
    
    private void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
