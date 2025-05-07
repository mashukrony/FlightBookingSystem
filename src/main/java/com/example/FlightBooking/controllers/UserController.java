package com.example.FlightBooking.controllers;

import com.example.FlightBooking.models.Booking;
import com.example.FlightBooking.models.Flight;
import com.example.FlightBooking.services.BookingService;
import com.example.FlightBooking.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/")
    public String showRoleSelectionPage() {
        return "roleSelection";
    }

    @GetMapping("/user/dashboard")
    public String showUserDashboard(Model model) {
        List<Booking> bookings = bookingService.getAllBookings();
        model.addAttribute("bookings", bookings);
        return "userDashboard";
    }

    @PostMapping("/selectRole")
    public String selectRole(@RequestParam String role) {
        if ("ADMIN".equalsIgnoreCase(role)) {
            return "redirect:/admin/dashboard";
        } else if ("USER".equalsIgnoreCase(role)) {
            return "redirect:/user/dashboard";
        }
        return "redirect:/";
    }

    @GetMapping("/searchFlights")
    public String searchFlightsPage(Model model) {
        model.addAttribute("flight", new Flight());
        return "searchFlights";
    }

    @PostMapping("/searchFlights")
    public String searchFlights(@ModelAttribute Flight flight, Model model) {
        List<Flight> flights = flightService.searchFlights(flight.getFromLocation(), flight.getToLocation());
        model.addAttribute("flights", flights);
        return "flightResults";
    }

    // Payment Functionality
    @GetMapping("/payment")
    public String showPaymentPage() {
        return "payment";
    }

    // Modify Seat - Display Form
    @GetMapping("/modifySeat/{id}")
    public String showModifySeatForm(@PathVariable Long id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        if (booking == null) {
            // Handle the case where booking is not found
            model.addAttribute("errorMessage", "Booking not found");
            return "error"; // You can create an error.html template
        }
        model.addAttribute("booking", booking);
        return "modifySeat";
    }

    // Modify Seat - Handle Form Submission
    @PostMapping("/modifySeat/{id}")
    public String modifySeat(@PathVariable Long id, @RequestParam String seatNumber) {
        bookingService.updateSeatNumber(id, seatNumber);
        return "redirect:/user/dashboard";
    }
}
