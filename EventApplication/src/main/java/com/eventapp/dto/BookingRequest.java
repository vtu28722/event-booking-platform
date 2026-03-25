package com.eventapp.dto;

public class BookingRequest {
    private Long eventId;
    private Integer numberOfTickets;
    private String paymentMethod;

    public BookingRequest() {
    }

    public BookingRequest(Long eventId, Integer numberOfTickets) {
        this.eventId = eventId;
        this.numberOfTickets = numberOfTickets;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Integer getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(Integer numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
