package com.eventapp.dto;

import com.eventapp.entity.Booking;
import java.time.LocalDateTime;

public class BookingResponse {
    private Long id;
    private Integer numberOfTickets;
    private Double totalAmount;
    private LocalDateTime bookingDate;
    private String paymentMethod;
    private String paymentStatus;
    private EventDto event;

    public BookingResponse(Booking booking) {
        this.id = booking.getId();
        this.numberOfTickets = booking.getNumberOfTickets();
        this.totalAmount = booking.getTotalAmount();
        this.bookingDate = booking.getBookingDate();
        this.paymentMethod = booking.getPaymentMethod();
        this.paymentStatus = booking.getPaymentStatus();
        if (booking.getEvent() != null) {
            this.event = new EventDto(
                booking.getEvent().getId(),
                booking.getEvent().getTitle(),
                booking.getEvent().getDate().toString(),
                booking.getEvent().getVenue(),
                booking.getEvent().getPrice()
            );
        }
    }

    public Long getId() { return id; }
    public Integer getNumberOfTickets() { return numberOfTickets; }
    public Double getTotalAmount() { return totalAmount; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getPaymentStatus() { return paymentStatus; }
    public EventDto getEvent() { return event; }

    public static class EventDto {
        private Long id;
        private String title;
        private String date;
        private String venue;
        private Double price;

        public EventDto(Long id, String title, String date, String venue, Double price) {
            this.id = id;
            this.title = title;
            this.date = date;
            this.venue = venue;
            this.price = price;
        }

        public Long getId() { return id; }
        public String getTitle() { return title; }
        public String getDate() { return date; }
        public String getVenue() { return venue; }
        public Double getPrice() { return price; }
    }
}
