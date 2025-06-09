/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fiu.bookingapp.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import fiu.bookingapp.models.Booking;
import fiu.bookingapp.ConnectionFIU;

public class BookingControlers {
    public static boolean insertBooking(Booking booking) {
        String sql = "INSERT INTO bookings (user_id, service_id, schedule_id, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFIU.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, booking.getUserId());
            stmt.setInt(2, booking.getServiceId());
            stmt.setInt(3, booking.getScheduleId());
            stmt.setString(4, booking.getStatus());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
