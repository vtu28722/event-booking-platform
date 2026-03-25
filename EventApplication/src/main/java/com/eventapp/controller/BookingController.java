package com.eventapp.controller;

import com.eventapp.dto.BookingRequest;
import com.eventapp.dto.BookingResponse;
import com.eventapp.entity.Booking;
import com.eventapp.service.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponse> bookTickets(@RequestBody BookingRequest request, HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);
        Long userId = (Long) session.getAttribute("userId");
        Booking booking = bookingService.bookTickets(userId, request);
        return ResponseEntity.ok(new BookingResponse(booking));
    }

    @GetMapping("/my-bookings")
    public ResponseEntity<List<BookingResponse>> getMyBookings(HttpServletRequest httpRequest) {
        HttpSession session = httpRequest.getSession(false);
        Long userId = (Long) session.getAttribute("userId");
        List<Booking> bookings = bookingService.getUserBookings(userId);
        List<BookingResponse> response = bookings.stream()
                .map(BookingResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
