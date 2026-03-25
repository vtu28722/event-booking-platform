package com.eventapp.service;

import com.eventapp.dto.BookingRequest;
import com.eventapp.entity.Booking;
import com.eventapp.entity.Event;
import com.eventapp.entity.User;
import com.eventapp.repository.BookingRepository;
import com.eventapp.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final EventRepository eventRepository;
    private final UserService userService;

    public BookingService(BookingRepository bookingRepository, EventRepository eventRepository, UserService userService) {
        this.bookingRepository = bookingRepository;
        this.eventRepository = eventRepository;
        this.userService = userService;
    }

    @Transactional
    public Booking bookTickets(Long userId, BookingRequest request) {
        User user = userService.findById(userId);
        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (event.getAvailableSeats() < request.getNumberOfTickets()) {
            throw new RuntimeException("Not enough seats available");
        }

        // Deduct seats
        event.setAvailableSeats(event.getAvailableSeats() - request.getNumberOfTickets());
        eventRepository.save(event);

        // Calculate amount
        Double totalAmount = event.getPrice() * request.getNumberOfTickets();

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setEvent(event);
        booking.setNumberOfTickets(request.getNumberOfTickets());
        booking.setTotalAmount(totalAmount);
        booking.setBookingDate(LocalDateTime.now());
        booking.setPaymentMethod(request.getPaymentMethod());
        booking.setPaymentStatus("COMPLETED");

        return bookingRepository.save(booking);
    }

    @Transactional(readOnly = true)
    public List<Booking> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId);
    }
}
