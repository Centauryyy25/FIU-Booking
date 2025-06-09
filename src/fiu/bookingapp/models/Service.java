/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fiu.bookingapp.models;

import fiu.bookingapp.ConnectionFIU;
import java.sql.*;
import java.util.*;
/**
 *
 * @author User
 */
public class Service {
    private int id;
    private String name;
    private double price;
    private int duration;

    // Constructor
    public Service(int id, String name, double price, int duration) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.duration = duration;
    }
    
    public Service(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }

    // Getter
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getDuration() { return duration; }

    // Static method untuk ambil semua data dari DB
    public static List<Service> getAllServices() {
        List<Service> list = new ArrayList<>(); 
        try (Connection conn = ConnectionFIU.connect()) {
            String sql = "SELECT * FROM services";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Service s = new Service(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("duration")
                );
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        
    }
    
    public static boolean insertService(String name, double price, int duration) {
    try (Connection conn = ConnectionFIU.connect()) {
        String sql = "INSERT INTO services (name, price, duration) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);
        stmt.setDouble(2, price);
        stmt.setInt(3, duration);
        stmt.executeUpdate();
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    
}

