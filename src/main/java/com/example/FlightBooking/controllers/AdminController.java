package com.example.FlightBooking.controllers;

import com.example.FlightBooking.models.Booking;
import com.example.FlightBooking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private BookingService bookingService;

    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "adminDashboard";
    }
}
