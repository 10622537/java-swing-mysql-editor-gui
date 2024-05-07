// By Premdeep Maiti
// Roll 10622537
// Sem - IV
// Branch - Comp. Sci. Engineering


package gui;

import javax.swing.*;

import java.awt.event.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.*;

class mysql_create_database { // This class creates database in MySQL
    mysql_create_database(String databasename) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", "rohan", "3876");

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE DATABASE "+databasename);
            System.out.println("Database Created Successfully");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class mysql_execute_command { // This Class Creates Table in mySQL
    mysql_execute_command(String databasename, String execute_command) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", "rohan", "3876");

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("use "+databasename);
            stmt.executeQuery("CREATE TABLE "+execute_command+" ;");
            System.out.println("Command Executed Successfully");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class mysql_insert_command { // This Class Inserts Values in Table in mySQL Database
    mysql_insert_command(String databasename, String execute_command) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", "rohan", "3876");

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("use "+databasename);
            stmt.executeQuery("insert into Persons values ( "+execute_command+");");
            System.out.println("Command Executed Successfully");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


class createDatabaseFrame implements ActionListener { // This Class creates frame for Database creation

    JButton Submit;
    JTextArea databaseName;
    public static String dbname;
    createDatabaseFrame() {
        JFrame createDatabaseFrame = new JFrame();
        databaseName = new JTextArea();
        Submit = new JButton("Submit");
        Submit.addActionListener(this);

        databaseName.setPreferredSize(new Dimension(250, 40));
        // Submit.setBounds(100,100,200,100);
        // database_name.setBounds(300,100,200,100);

        createDatabaseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createDatabaseFrame.add(databaseName);
        createDatabaseFrame.add(Submit);
        createDatabaseFrame.setVisible(true); // makes window visible
        createDatabaseFrame.setSize(420, 420); // sets x & y dimension of our frame
        createDatabaseFrame.setLayout(new FlowLayout());
        // createDatabaseFrame.pack();
        createDatabaseFrame.setTitle("Create new database"); // sets title of window
      
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Submit) {
            dbname= databaseName.getText();
            System.out.println(dbname);
            new mysql_create_database(dbname);
            new executeMySQLCommands(dbname);
            
        }
    }
}
class executeMySQLCommands implements ActionListener{ // This Class Creates Frame for Table Creation 
    JTextArea  mysql_command;
    JButton Execute;
    public static String table_name;
    executeMySQLCommands(String databasename){
        JFrame executeMySQLCommands=new JFrame();
        mysql_command = new JTextArea();
        Execute = new JButton("CREATE TABLE");
        JLabel label=new JLabel();
        label.setText("Enter table in the form of 'table_name ( column1 datatype, column2 datatype, column3 datatype, .... )'\n" + //
                        "");
        Execute.addActionListener(this);

        mysql_command.setPreferredSize(new Dimension(250, 40));
        // Submit.setBounds(100,100,200,100);
        // database_name.setBounds(300,100,200,100);

        executeMySQLCommands.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        executeMySQLCommands.add(label);
        executeMySQLCommands.add(mysql_command);
        executeMySQLCommands.add(Execute);
        executeMySQLCommands.setVisible(true); // makes window visible
        executeMySQLCommands.setSize(420, 420); // sets x & y dimension of our frame
        executeMySQLCommands.setLayout(new FlowLayout());

        executeMySQLCommands.setTitle("Create Tables in "+createDatabaseFrame.dbname); // sets title of window
      
    }

    public static void add(JLabel label) {
      
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Execute) {
            System.out.println(mysql_command.getText());
            table_name=mysql_command.getText();
            new mysql_execute_command(createDatabaseFrame.dbname,mysql_command.getText());
            new insertValuesMySQL();
        }
    }

    }


    class insertValuesMySQL implements ActionListener{ // This class creates Frame to insert values in table
        JTextArea  mysql_command;
        JButton Execute;

        insertValuesMySQL(){
            JFrame executeMySQLCommands=new JFrame();
            mysql_command = new JTextArea();
            Execute = new JButton("INSERT VALUES");
            JLabel label=new JLabel();
            label.setText("Enter values in the form of 'value1, value2, value3, ..., valueN'");
            Execute.addActionListener(this);
    
            mysql_command.setPreferredSize(new Dimension(250, 40));
            // Submit.setBounds(100,100,200,100);
            // database_name.setBounds(300,100,200,100);
    
            executeMySQLCommands.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
            executeMySQLCommands.add(label);
            executeMySQLCommands.add(mysql_command);
            executeMySQLCommands.add(Execute);
            executeMySQLCommands.setVisible(true); // makes window visible
            executeMySQLCommands.setSize(420, 420); // sets x & y dimension of our frame
            executeMySQLCommands.setLayout(new FlowLayout());
            // createDatabaseFrame.pack();
            executeMySQLCommands.setTitle("Insert Values in "+createDatabaseFrame.dbname); // sets title of window
          
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Execute) {
                System.out.println(mysql_command.getText());
            new mysql_insert_command(createDatabaseFrame.dbname,mysql_command.getText());            
            }
        }

    
        }

       

public class Main {

    public static void main(String[] args) {

        JFrame f = new JFrame();
        JButton createDatabase = new JButton();
        createDatabase.setText("Create Database");
        createDatabase.setBounds(100, 100, 200, 100);
        createDatabase.addActionListener(e -> new createDatabaseFrame());

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setVisible(true); // makes window visible
    
        f.setLayout(null);

        f.setSize(420, 420); // sets x & y dimension of our frame
        f.setTitle("MySQL Editor"); // sets title of window
        f.add(createDatabase);

    }
}