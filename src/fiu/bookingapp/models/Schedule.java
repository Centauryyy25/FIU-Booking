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
public class Schedule {
    private int id;
    private String date;
    private String timeSlot;
    private boolean available;
    private String time;

    // Constructor + Getters
    public Schedule(int id, String date, String timeSlot, boolean available) {
        this.id = id;
        this.date = date;
        this.timeSlot = timeSlot;
        this.available = available;
    }

    public int getId() { return id; }
    public String getDate() { return date; }
    public String getTimeSlot() { return timeSlot; }
    public boolean isAvailable() { return available; }

    public static List<Schedule> getAllSchedules() {
        List<Schedule> list = new ArrayList<>();
        try (Connection conn = ConnectionFIU.connect()) {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM schedules");
            while (rs.next()) {
                list.add(new Schedule(
                    rs.getInt("id"),
                    rs.getDate("date").toString(),
                    rs.getTime("time_slot").toString(),
                    rs.getBoolean("available")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean insertSchedule(String date, String time) {
        try (Connection conn = ConnectionFIU.connect()) {
            String sql = "INSERT INTO schedules (date, time_slot, available) VALUES (?, ?, TRUE)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(date));
            stmt.setTime(2, java.sql.Time.valueOf(time));
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean isScheduleAvailableForDate(String date) {
    boolean exists = false;
    try (Connection conn = ConnectionFIU.connect()) {
        String query = "SELECT COUNT(*) FROM schedules WHERE date = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, date);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            exists = rs.getInt(1) > 0;
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return exists;
}

    
    @Override
    public String toString() {
        return timeSlot + " (Tgl: " + date + ")";
    }
}


