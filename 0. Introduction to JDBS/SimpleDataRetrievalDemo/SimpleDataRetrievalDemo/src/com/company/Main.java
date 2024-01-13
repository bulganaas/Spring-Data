package com.company;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username default (root): ");
        String user = sc.nextLine();
        user = user.equals("") ? "root" : user;
        System.out.println();

        System.out.print("Enter password default (empty):");
        String password = sc.nextLine().trim();
        System.out.println();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/soft_uni", props);

        PreparedStatement stmt =
                connection.prepareStatement("SELECT * FROM employees WHERE salary > ? and salary < ?");
        System.out.print("Enter salary: ");

        //Salary criteria by user input
        String salary = sc.nextLine();
        stmt.setDouble(1, Double.parseDouble(salary));
        stmt.setDouble(2, 1000000);

        //Runs the SQL statement and returns retrieved result
        ResultSet rs = stmt.executeQuery();

        //Iterating over the result
        while(rs.next()){ // Retrieved data
            //The ResultSet is a set of table rows  //Getter method,  Column name
            System.out.println(rs.getString("first_name") + " " + rs.getString("last_name"));
        }
        stmt.close();
        connection.close();
    }
}