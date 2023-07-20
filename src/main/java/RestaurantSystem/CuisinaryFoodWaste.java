//Gawa ni Happy Enciso
package RestaurantSystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CuisinaryFoodWaste extends JFrame implements ActionListener{
    private JLabel lbltitle,lblBackground,lblCategory,lblDishes,lblNumOfItems;
    private JPanel panel4,panel2,panel3,panel1;
    private JComboBox reportCbx,cbMonth;
    private JComboBox<String> cbCategory,cbDishes;
    private JTable recordTbl;
    private JScrollPane scrollPane;
    private JButton btnJan,btnFeb,btnMar,btnApr,btnMay,btnJun,btnJul,btnAug,btnSept,btnOct,btnNov,btnDec,btnHome,btnUpdate,btnOverall;
    private JTextField txtfld1,txtfld2;
    private String selectedMonth;
    private Connection connection;
    private Statement statement;
    
    //Category
    String[] arrCategory = {"Select Category","Main Course","Appetizer","Salad","Desert","Drinks"};
    //Dishes
    String[] arrSelectedCategory = {};
    String[] arrMainCourse = {"Grilled Roast Beef","SeaFood Pack","Spicy Pork Rim","Baked Fish with Chili Sauce","Buttered Shrimp Bucket"};
    String[] arrAppetizer = {"Pork Dumpling","Spinach Pancake","Mild Mushroom with Shrimp"};
    String[] arrSalad = {"Prawn Salad","Vegetable Salad","Fruit Salad"};
    String[] arrDesert = {"Coffee Tiramisu","Choco Lava Cake","Blueberyy CheeseCake","Leche Plan","Ube Halaya"};
    String[] arrDrinks = {"Bottled Water","Mango Shake","Buko Shake","Sprite in Can","Coke in Can"};
    //Other Arrays
    String[] cbmMonth = {"January","February","March","April","May","June","July","August","September","October","November","December"};
    String[] cbmBox = {"Waste","Sales","Inventory"};
    
    
    //sql
    private static final String dbURL = "jdbc:mysql://localhost:3306/cuisinary";
    private static final String dbUsername = "root";
    private static final String dbPassword = "Nico@2003";
    
    CuisinaryFoodWaste(){
        lbltitle = new JLabel("CUISINARY FOOD WASTE");
        lbltitle.setFont(new Font("Open Sans",Font.BOLD,45));
        lbltitle.setBounds(280, 30, 570, 80);
        lbltitle.setForeground(Color.BLACK);
        lbltitle.setBackground(Color.WHITE);
        add(lbltitle);
        
        panel1();
        panel2();
        panel3();
        panel4();
        //panel1();
        
        setTitle("Food Waste Report");
        setSize(900,800);
        setResizable(false);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        //setting image as background
        ImageIcon imgIcon = new ImageIcon("C:\\Users\\Nico\\Documents\\NetBeansProjects\\RestaurantSystem\\src\\main\\java\\RestaurantSystem\\ResizedBG.jpg");
        
        Image img = imgIcon.getImage();
        Image temp_img = img.getScaledInstance(900, 800, Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(temp_img);
        lblBackground = new JLabel("",imgIcon,JLabel.CENTER);
        lblBackground.setBounds(0,0,900,800);
        add(lblBackground);
       
       //sql
        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
        setVisible(true);
    }
    
    
    //panel2 for selecting and updating waste
    public void panel2(){
        Font panel2Font = new Font("Open Sans",Font.BOLD,20);
        
        panel2 = new JPanel();
        panel2.setBackground(new Color(0,0,0,30));
        panel2.setLayout(new GridBagLayout());
        panel2.setBounds(280,150,570,400);
        
        //gridbaglayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        //components for category
        lblCategory = new JLabel("Category");
        lblCategory.setFont(panel2Font);
        
        cbCategory = new JComboBox<>(arrCategory);
        cbCategory.setFont(panel2Font);
        cbCategory.addActionListener(this);
        
        //component for dishes
        lblDishes = new JLabel("Dishes");
        lblDishes.setFont(panel2Font);
        
        cbDishes = new JComboBox<>();
        cbDishes.setFont(panel2Font);
        
        //component for number of items
        
        lblNumOfItems = new JLabel("Number of Items");
        lblNumOfItems.setFont(panel2Font);
        
        txtfld2 = new JTextField();
        txtfld2.setFont(panel2Font);
        
        Dimension textFieldSize = new Dimension(150, 30); 
        txtfld2.setPreferredSize(textFieldSize);
        txtfld2.setFont(panel2Font);
        
        //component for update
        btnUpdate = new JButton("Update");
        btnUpdate.setFont(panel2Font);
        btnUpdate.addActionListener(this);
        
        //component for month
        cbMonth = new JComboBox(cbmMonth);
        cbMonth.setFont(panel2Font);
        cbMonth.addActionListener(this);
        
        //setting location for components
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel2.add(lblCategory,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel2.add(cbCategory,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel2.add(lblDishes,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel2.add(cbDishes,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel2.add(lblNumOfItems,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel2.add(txtfld2,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        panel2.add(cbMonth,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 7;
        panel2.add(btnUpdate,gbc);
        
        add(panel2); 
    }
    
    //panel 3
    public void panel3(){
        panel3 = new JPanel();
        //panel3.setBackground(new Color(0,0,0,10));
        panel3.setLayout(new GridLayout(14,0,0,2));
        panel3.setBounds(40,150,200,400);
        
        txtfld1 = new JTextField("Selected Month");
        txtfld1.setBackground(Color.WHITE);
        txtfld1.setFont(new Font("Open Sans",Font.BOLD,15));
        txtfld1.setEditable(false);
        
        //JButton
        btnOverall = new JButton("Over All");
        btnOverall.setBackground(Color.YELLOW); 
        btnOverall.addActionListener(this);
        
        btnJan = new JButton("January");
        btnJan.setBackground(Color.YELLOW); 
        btnJan.addActionListener(this);
        
        btnFeb = new JButton("February");
        btnFeb.setBackground(Color.YELLOW);
        btnFeb.addActionListener(this);
        
        btnMar = new JButton("March");
        btnMar.setBackground(Color.YELLOW);
        btnMar.addActionListener(this);
        
        btnApr = new JButton("April");
        btnApr.setBackground(Color.YELLOW);
        btnApr.addActionListener(this);
        
        btnMay = new JButton("May");
        btnMay.setBackground(Color.YELLOW);
        btnMay.addActionListener(this);
        
        btnJun = new JButton("June");
        btnJun.setBackground(Color.YELLOW);
        btnJun.addActionListener(this);
        
        btnJul = new JButton("July");
        btnJul.setBackground(Color.YELLOW);
        btnJul.addActionListener(this);
        
        btnAug = new JButton("August");
        btnAug.setBackground(Color.YELLOW);
        btnAug.addActionListener(this);
        
        btnSept = new JButton("September");
        btnSept.setBackground(Color.YELLOW);
        btnSept.addActionListener(this);
        
        btnOct = new JButton("October");
        btnOct.setBackground(Color.YELLOW);
        btnOct.addActionListener(this);
        
        btnNov = new JButton("November");
        btnNov.setBackground(Color.YELLOW);
        btnNov.addActionListener(this);
        
        btnDec = new JButton("December");
        btnDec.setBackground(Color.YELLOW);
        btnDec.addActionListener(this);
        
        panel3.add(txtfld1);
        panel3.add(btnOverall); panel3.add(btnJan); panel3.add(btnFeb); panel3.add(btnMar);
        panel3.add(btnApr); panel3.add(btnMay); panel3.add(btnJun); panel3.add(btnJul); 
        panel3.add(btnAug); panel3.add(btnSept); panel3.add(btnOct); panel3.add(btnNov);
        panel3.add(btnDec);
        
        add(panel3);
    }
    
    //home and dropdown for sales,etc
    public void panel4(){
        panel4 = new JPanel();
        //panel4.setBackground(new Color(0,0,0,10));
        panel4.setBounds(40,30,200,80);
        panel4.setLayout(new GridLayout(2,0,0,2));
        
        ImageIcon imgg = new ImageIcon("C:\\Users\\Nico\\Documents\\NetBeansProjects\\RestaurantSystem\\src\\main\\java\\RestaurantSystem\\Home.png");
        btnHome = new JButton(imgg);
        btnHome.setBackground(Color.WHITE);
        btnHome.addActionListener(this);
        
        reportCbx = new JComboBox(cbmBox);
        reportCbx.setBackground(Color.black);
        reportCbx.setForeground(Color.WHITE);
        ((JLabel)reportCbx.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        reportCbx.addActionListener(this);
        
        panel4.add(btnHome);
        panel4.add(reportCbx);
        add(panel4);
    }
    
    public void panel1(){
        panel1 = new JPanel();
        panel1.setBackground(new Color(0,0,0,50));
        panel1.setBounds(40, 560, 810, 180);
        panel1.setLayout(new BorderLayout());
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Category");
        model.addColumn("Dishes");
        model.addColumn("Number of Items");
        model.addColumn("Month");

        recordTbl = new JTable(model);
        recordTbl.setFont(new Font("Open Sans",Font.BOLD,20));
        
        scrollPane = new JScrollPane(recordTbl);
        
        
        panel1.add(scrollPane,BorderLayout.CENTER);
        
        add(panel1);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnJan){
            selectedMonth = "January";
            txtfld1.setText("Selected Month: January");
            historyTable(selectedMonth);
        }else if(e.getSource() == btnFeb){
            //selectedMonth = "February";
            txtfld1.setText("Selected Month: February");
            historyTable("February"); 
        }else if(e.getSource() == btnMar){
            txtfld1.setText("Selected Month: March");
            historyTable("March");
        }else if(e.getSource() == btnApr){
            txtfld1.setText("Selected Month: April");
            historyTable("April");
        }else if(e.getSource() == btnMay){
            txtfld1.setText("Selected Month: May");
            historyTable("May");
        }else if(e.getSource() == btnJun){
            txtfld1.setText("Selected Month: June");
            historyTable("June");
        }else if(e.getSource() == btnJul){
            txtfld1.setText("Selected Month: July");
            historyTable("July");
        }else if(e.getSource() == btnAug){
            txtfld1.setText("Selected Month: August");
            historyTable("August");
        }else if(e.getSource() == btnSept){
            txtfld1.setText("Selected Month: September");
            historyTable("September");
        }else if(e.getSource() == btnOct){
            txtfld1.setText("Selected Month: October");
            historyTable("October");
        }else if(e.getSource() == btnNov){
            txtfld1.setText("Selected Month: November");
            historyTable("November");
        }else if(e.getSource() == btnDec){
            txtfld1.setText("Selected Month: December");
            historyTable("December");
        }else if(e.getSource() == cbCategory){
            updateCbDish();
        }else if(e.getSource() == btnUpdate){
            //historyTable((String) cbMonth.getSelectedItem());
            String getCategory = (String)cbCategory.getSelectedItem();
            String getDishes = (String)cbDishes.getSelectedItem();
            String getNumOfItem = (String) txtfld2.getText();
            String getMonth = (String)cbMonth.getSelectedItem();
 
            if(getNumOfItem.isEmpty()||getDishes.isEmpty()||getCategory.isEmpty()||getMonth.isEmpty()){
                JOptionPane.showMessageDialog(rootPane, "Please Enter Number of Item", "ERROR", JOptionPane.ERROR_MESSAGE);
            }else{
                try {
                int numberOfItem = Integer.parseInt(getNumOfItem);
                String insertQuery = "INSERT INTO History (Category, Dishes, NumberOfItems, Month) " +
                                     "VALUES ('" + getCategory + "', '" + getDishes + "', " +
                                     "'" + getNumOfItem + "', '" + getMonth + "')";
                statement.executeUpdate(insertQuery);
                JOptionPane.showMessageDialog(rootPane, "Updated waste successfully","Waste",JOptionPane.INFORMATION_MESSAGE);
                
                    DefaultTableModel model = (DefaultTableModel) recordTbl.getModel();
                model.addRow(new Object[]{getCategory, getDishes, getNumOfItem,getMonth});
                }catch (SQLException ex) {
                    ex.printStackTrace();
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(rootPane, "Please enter number only", "ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if(e.getSource() == btnOverall){
            txtfld1.setText("Selected Month: Overall");
            //historyTable(selectedMonth);
            overallTable("Over All");
        }
        else if(e.getSource() == btnHome){
            dispose();
            new HomePage();
        }
        else if (e.getSource() == reportCbx) {
            String selectedChoice = (String) reportCbx.getSelectedItem();
            if (selectedChoice.equals("Waste")) {
                dispose();
                new CuisinaryFoodWaste();
            }
            else if (selectedChoice.equals("Sales")) {
            }
            else if (selectedChoice.equals("Inventory")) {
                dispose();
                new Inventory();
            }
        }
    }

    private void historyTable(String month) {
        DefaultTableModel model = (DefaultTableModel) recordTbl.getModel();
        model.setRowCount(0);
        
         try {
            String selectQuery = "SELECT * FROM History WHERE Month = '" + month + "'";
            ResultSet result = statement.executeQuery(selectQuery);
          
            
            while (result.next()) {
                String category = result.getString("Category");
                String dishes = result.getString("Dishes");
                String numOfItems = result.getString("NumberOfItems");
                String recordMonth = result.getString("Month");
                model.addRow(new Object[]{category, dishes, numOfItems, recordMonth});
            }
            
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void overallTable(String overall){
        DefaultTableModel model = (DefaultTableModel) recordTbl.getModel();
        model.setRowCount(0);
        
        try{
            String selectQuery = "SELECT * FROM History";
            ResultSet result = statement.executeQuery(selectQuery);
            
            while (result.next()) {
                String category = result.getString("Category");
                String dishes = result.getString("Dishes");
                String numOfItems = result.getString("NumberOfItems");
                String recordMonth = result.getString("Month");
                model.addRow(new Object[]{category, dishes, numOfItems, recordMonth});
            }
            
            result.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
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
}