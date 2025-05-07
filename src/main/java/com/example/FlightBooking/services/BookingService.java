package com.example.FlightBooking.services;

import com.example.FlightBooking.models.Booking;
import com.example.FlightBooking.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public void saveBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    // Get Booking by ID
    public Booking getBookingById(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        return optionalBooking.orElse(null);
    }

    // Update Seat Number
    public void updateSeatNumber(Long id, String seatNumber) {
        Booking booking = getBookingById(id);
        if (booking != null) {
            booking.setSeatNumber(seatNumber);
            bookingRepository.save(booking);
        }
    }
}
