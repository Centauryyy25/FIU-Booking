/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fiu.bookingapp.models;

import fiu.bookingapp.ConnectionFIU;

/**
 *
 * @author User
 */
public class Booking {
    private int id;
    private int userId;
    private int serviceId;
    private int scheduleId;
    private String status;
    private final String date;

    // Constructor
    public Booking(int userId, int serviceId, int scheduleId, String date) {
        this.userId = userId;
        this.serviceId = serviceId;
        this.scheduleId = scheduleId;
        this.status = "pending";
        this.date = date; // buat field `date` kalau belum ada
    }

    // Getter & Setter
    public int getUserId() { return userId; }
    public int getServiceId() { return serviceId; }
    public int getScheduleId() { return scheduleId; }
    public String getStatus() { return status; }
}
