package RestaurantSystem;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Inventory extends JFrame implements ActionListener {
    private JPanel panel1, panel2;
    private JComboBox<String> cbCategory,cbDishes, reportCbx;
    private JTextField txtfldQuantity;
    private JButton btnAdd,btnRemove,btnUpdate,btnHome;
    private JTextArea Area;
    private JLabel lblBackground; 
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    
    
    //Category
    String[] arrCategory = {"Select Category","Main Course","Appetizer","Salad","Desert","Drinks"};
    //Dishes
    String[] arrSelectedCategory = {};
    String[] arrMainCourse = {"Grilled Roast Beef","SeaFood Pack","Spicy Pork Rim","Baked Fish with Chili Sauce","Buttered Shrimp Bucket"};
    String[] arrAppetizer = {"Pork Dumpling","Spinach Pancake","Mild Mushroom with Shrimp"};
    String[] arrSalad = {"Prawn Salad","Vegetable Salad","Fruit Salad"};
    String[] arrDesert = {"Coffee Tiramisu","Choco Lava Cake","Blueberyy CheeseCake","Leche Plan","Ube Halaya"};
    String[] arrDrinks = {"Bottled Water","Mango Shake","Buko Shake","Sprite in Can","Coke in Can"};
    
    String[] cbmBox = {"Inventory","Sales","Waste"};
    
    private Connection connection;
    private Statement statement;
    private static final String dbURL = "jdbc:mysql://localhost:3306/cuisinary";
    private static final String dbUsername = "root";
    private static final String dbPassword = "Nico@2003";
     
    Inventory(){
        panel1();
        panel2();
       
        
        setFont(new Font("Open Sans",Font.BOLD,45));
        setSize(900,650);
        setTitle("Inventory");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setForeground(Color.BLACK);
        setBackground(Color.WHITE);
      
        
        //code for image background
        
        ImageIcon imgIcon = new ImageIcon("C:\\Users\\Nico\\Documents\\NetBeansProjects\\RestaurantSystem\\src\\main\\java\\RestaurantSystem\\ResizedBG.jpg");
        
        Image img = imgIcon.getImage();
        Image temp_img = img.getScaledInstance(900, 800, Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(temp_img);
        lblBackground = new JLabel("",imgIcon,JLabel.CENTER);
        lblBackground.setBounds(0,0,900,800);
        add(lblBackground);
        
        //sql
         try {
            connection = (Connection) DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            statement = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
             
         setVisible(true);
    }
    
    public void panel1(){
        
        Font panel1Font = new Font("Open Sans",Font.BOLD,20);
        
        panel1 = new JPanel();
        panel1.setBackground(new Color(0,0,0,50));
        panel1.setBounds(50,30,300,500);
        panel1.setLayout(new GridLayout(8,0,3,3));
        
        ImageIcon imgg = new ImageIcon("C:\\Users\\Nico\\Documents\\NetBeansProjects\\RestaurantSystem\\src\\main\\java\\RestaurantSystem\\Home.png");
        btnHome = new JButton(imgg);
        btnHome.setBackground(Color.WHITE);
        btnHome.addActionListener(this);
        
        reportCbx = new JComboBox(cbmBox);
        reportCbx.setBackground(Color.black);
        reportCbx.setForeground(Color.WHITE);
        ((JLabel)reportCbx.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        reportCbx.addActionListener(this);
        
        panel1.add(btnHome);
        panel1.add(reportCbx);
        
        cbCategory = new JComboBox<>(arrCategory);
        cbCategory.setFont(panel1Font);
        cbCategory.addActionListener(this);
        
        cbDishes = new JComboBox<>();
        cbDishes.setFont(panel1Font);
        
        txtfldQuantity = new JTextField();
        txtfldQuantity.setFont(panel1Font);
        
        btnAdd = new JButton("Add Item");
        btnRemove = new JButton("Remove Item");
        btnUpdate = new JButton("Update Item");
        
        
        panel1.add(cbCategory);
        cbCategory.setBackground(Color.WHITE); 
        cbCategory.addActionListener(this);
        
        panel1.add(cbDishes);
        panel1.add(txtfldQuantity);
        
        panel1.add(btnAdd);
        btnAdd.setBackground(Color.YELLOW); 
        btnAdd.addActionListener(this);
        
        panel1.add(btnRemove);
        btnRemove.setBackground(Color.YELLOW); 
        btnRemove.addActionListener(this);
                
        panel1.add(btnUpdate);
        btnUpdate.setBackground(Color.YELLOW); 
        btnUpdate.addActionListener(this);
        
        add(panel1);
        
    }
    
    public void panel2(){
        panel2 = new JPanel();
        panel2.setBackground(new Color(0,0,0,50));
        panel2.setBounds(350,30,500,500);
        panel2.setLayout(new BorderLayout());
        
        model = new DefaultTableModel();
        model.addColumn("Category");
        model.addColumn("Dishes");
        model.addColumn("Quantity");
        
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        
        panel2.add(scrollPane);
        
        add(panel2);
    }
    
    private void updateCbDish(){
        cbDishes.removeAllItems();
        
        String selectedCategory = (String)cbCategory.getSelectedItem();
        
        if(selectedCategory.equals(arrSelectedCategory)){
           for(String dishes : arrSelectedCategory){
                cbDishes.addItem(dishes);
            } 
        }else if(selectedCategory.equals("Main Course")){
            for(String dishes : arrMainCourse){
            cbDishes.addItem(dishes);
          }
        }else if(selectedCategory.equals("Appetizer")){
            for(String dishes : arrAppetizer){
                cbDishes.addItem(dishes);
            }
        }else if(selectedCategory.equals("Salad")){
            for(String dishes : arrSalad){
                cbDishes.addItem(dishes);
            }
        }else if(selectedCategory.equals("Desert")){
            for(String dishes : arrDesert){
                cbDishes.addItem(dishes);
            }
        }else if(selectedCategory.equals("Drinks")){
           for(String dishes : arrDrinks){
                cbDishes.addItem(dishes);
            } 
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cbCategory){
            updateCbDish();
        }
        else if (e.getSource() == btnHome) {
            dispose();
            new HomePage();
        }
        else if (e.getSource() == btnAdd) {
            addItemToTable();
        }
        else if (e.getSource () == btnRemove){
            removeItemFromTable();
        }
        else if (e.getSource() == btnUpdate) {
            updateItemInTable();
        }
        else if (e.getSource() == reportCbx) {
            String selectedChoice = (String) reportCbx.getSelectedItem();
            if (selectedChoice.equals("Inventory")) {
                dispose();
                new Inventory();
            }
            else if (selectedChoice.equals("Sales")) {
            }
            else if (selectedChoice.equals("Waste")) {
                dispose();
                new CuisinaryFoodWaste();
            }
        }
    }

    private void addItemToTable() {
        String selectedCategory = (String) cbCategory.getSelectedItem();
        String selectedDish = (String) cbDishes.getSelectedItem();
        String quantity = txtfldQuantity.getText();

        // Validate input
        if (selectedCategory.equals("Select Category") || selectedDish == null || selectedDish.isEmpty() || quantity.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a category, dish, and enter the quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int intQuantity;
        try {
            intQuantity = Integer.parseInt(quantity);
            if (intQuantity < 1) {
                JOptionPane.showMessageDialog(this, "Quantity should be a positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid quantity input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add the item to the JTable
        model.addRow(new Object[]{selectedCategory, selectedDish, intQuantity});

        // Insert the data into the database
        try {
            String insertQuery = "INSERT INTO inv (Category, Dishes, Quantity) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, selectedCategory);
            preparedStatement.setString(2, selectedDish);
            preparedStatement.setInt(3, intQuantity);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error inserting data into the database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            
            model.removeRow(model.getRowCount() - 1);
            return;
        }

        txtfldQuantity.setText("");
        cbCategory.setSelectedIndex(0);
        cbDishes.removeAllItems();
    }

    private void removeItemFromTable() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow != -1) {
        String selectedCategory = (String) table.getValueAt(selectedRow, 0);
        String selectedDish = (String) table.getValueAt(selectedRow, 1);

        // Remove the row from the JTable
        model.removeRow(selectedRow);

        try {
            String deleteQuery = "DELETE FROM inv WHERE Category = ? AND Dishes = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, selectedCategory);
            preparedStatement.setString(2, selectedDish);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error deleting data from the database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select a row to remove.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    
    private void updateItemInTable() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String quantity = txtfldQuantity.getText();

            // Validate input
            if (quantity.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the new quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Convert quantity to integer and check if it's valid
            int intQuantity;
            try {
                intQuantity = Integer.parseInt(quantity);
                if (intQuantity < 1) {
                    JOptionPane.showMessageDialog(this, "Quantity should be a positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid quantity input. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update the quantity in the JTable
            model.setValueAt(intQuantity, selectedRow, 2);

            // Update the corresponding item in the database
            try {
                String selectedCategory = (String) table.getValueAt(selectedRow, 0);
                String selectedDish = (String) table.getValueAt(selectedRow, 1);

                String updateQuery = "UPDATE inv SET Quantity = ? WHERE Category = ? AND Dishes = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setInt(1, intQuantity);
                preparedStatement.setString(2, selectedCategory);
                preparedStatement.setString(3, selectedDish);
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error updating data in the database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            // Clear the input fields
            txtfldQuantity.setText("");
            cbCategory.setSelectedIndex(0);
            cbDishes.removeAllItems();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


