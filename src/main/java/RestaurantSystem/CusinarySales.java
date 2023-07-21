package RestaurantSystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CusinarySales extends JFrame implements ActionListener{
    private JTable salesTable;
    private DefaultTableModel tableModel;
    private Connection connection;
    private Statement statement;
    private JButton btnHome;
    private JPanel panel1, panel2;
    private JComboBox reportCbx;
    private JLabel lblBackground;
    
    String[] cbmBox = {"Sales","Waste","Inventory"};
//    String[] columnNames = {"Item Name", "Quantity Sold", "Amount"};

    private static final String dbURL = "jdbc:mysql://localhost:3306/cuisinary";
    private static final String dbUsername = "root";
    private static final String dbPassword = "Nico@2003";

    CusinarySales() {
        panel1();
        panel2();
        setTitle("Cusinary Sales");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        setResizable(false);

        ImageIcon imgIcon = new ImageIcon("C:\\Users\\Nico\\Documents\\NetBeansProjects\\RestaurantSystem\\src\\main\\java\\RestaurantSystem\\ResizedBG.jpg");
        
        Image img = imgIcon.getImage();
        Image temp_img = img.getScaledInstance(900, 650, Image.SCALE_SMOOTH);
        imgIcon = new ImageIcon(temp_img);
        lblBackground = new JLabel("",imgIcon,JLabel.CENTER);
        lblBackground.setBounds(0,0,900,800);
        add(lblBackground);
        
        //Database
        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword); // Database Connection Link
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        

//        initSalesTable();
        fetchSalesData(); // Automatic Magiinput sa table yung nasa database
        setVisible(true);
    }

    public void panel1(){
        panel1 = new JPanel();
        //panel4.setBackground(new Color(0,0,0,10));
        panel1.setBounds(40,30,200,80);
        panel1.setLayout(new GridLayout(2,0,0,2));
        
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
        add(panel1);
    }
    public void panel2(){
        panel2 = new JPanel();
        panel2.setBackground(new Color(0,0,0,50));
        panel2.setBounds(350,30,500,500);
        panel2.setLayout(new BorderLayout());
        
        String[] columnNames = {"Item Name", "Quantity Sold", "Amount"};
        tableModel = new DefaultTableModel(columnNames, 0);
        salesTable = new JTable(tableModel);
        salesTable.setFont(new Font("Open Sans", Font.BOLD, 16));
        
        JScrollPane scrollPane = new JScrollPane(salesTable);
        
        panel2.add(scrollPane);
        
        add(panel2);
    }
    @Override
    public void actionPerformed(ActionEvent clicked) {
        if (clicked.getSource() == btnHome){
            dispose();
            new HomePage();
        }
        else if (clicked.getSource() == reportCbx) {
            String selectedChoice = (String) reportCbx.getSelectedItem();
            if (selectedChoice.equals("Sales")) {
                dispose();
                new CusinarySales();
            }
            else if (selectedChoice.equals("Waste")) {
                dispose();
                new CuisinaryFoodWaste();
            }
            else if (selectedChoice.equals("Inventory")) {
                dispose();
                new Inventory();
            }
        }
    }
//    private void initSalesTable() {
//        String[] columnNames = {"Item Name", "Quantity Sold", "Amount"};
//        tableModel = new DefaultTableModel(columnNames, 0);
//        salesTable = new JTable(tableModel);
//        salesTable.setFont(new Font("Open Sans", Font.BOLD, 16));
//
//        JScrollPane scrollPane = new JScrollPane(salesTable);
//        scrollPane.setBounds(40, 150, 820, 620);
//        add(scrollPane);
//    }

    public void addOrderToSalesTable(String itemName, int quantitySold, double amount) {
        tableModel.addRow(new Object[]{itemName, quantitySold, "P" + amount});

        try {
            String insertQuery = "INSERT INTO sales (Item_Name, Quantity_Sold, Amount, Order_Date) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, itemName);
            preparedStatement.setInt(2, quantitySold);
            preparedStatement.setDouble(3, amount);
            preparedStatement.setString(4, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchSalesData() {
        try {
            String selectQuery = "SELECT Item_name, Quantity_sold, Amount FROM sales";
            ResultSet result = statement.executeQuery(selectQuery);

            while (result.next()) {
                String itemName = result.getString("Item_Name");
                int quantitySold = result.getInt("Quantity_Sold");
                double amount = result.getDouble("Amount");
                tableModel.addRow(new Object[]{itemName, quantitySold, "P" + amount});
            }

            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
