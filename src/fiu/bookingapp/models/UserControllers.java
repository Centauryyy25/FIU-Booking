/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fiu.bookingapp.models;

import fiu.bookingapp.ConnectionFIU;
import fiu.bookingapp.models.HashUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author User
 */
public class UserControllers {
    public static User login(String name, String password) {
    try (Connection conn = ConnectionFIU.connect()) {
        String sql = "SELECT * FROM users WHERE name=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, name);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String dbHashedPassword = rs.getString("password");
            String inputHashedPassword = HashUtil.hashPassword(password); // Hash input pengguna

            if (dbHashedPassword.equals(inputHashedPassword)) {
                int id = rs.getInt("id");
                String uname = rs.getString("name");
                String role = rs.getString("role");
                return new User(id, uname, role);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

}
