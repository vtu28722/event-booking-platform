package com.eventapp.service;

import com.eventapp.entity.Event;
import com.eventapp.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findByDateGreaterThanEqualOrderByDateAsc(LocalDate.now());
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    @Transactional
    public Event createEvent(Event event) {
        event.setAvailableSeats(event.getTotalSeats()); // Initially, all seats are available
        return eventRepository.save(event);
    }

    @Transactional
    public Event updateEvent(Long id, Event eventDetails) {
        Event event = getEventById(id);
        
        event.setTitle(eventDetails.getTitle());
        event.setDescription(eventDetails.getDescription());
        event.setDate(eventDetails.getDate());
        event.setVenue(eventDetails.getVenue());
        event.setPrice(eventDetails.getPrice());
        
        // Handling seat updates carefully. If total seats change, available seats should adjust.
        int seatDifference = eventDetails.getTotalSeats() - event.getTotalSeats();
        event.setTotalSeats(eventDetails.getTotalSeats());
        event.setAvailableSeats(event.getAvailableSeats() + seatDifference);
        
        if (event.getAvailableSeats() < 0) {
            throw new RuntimeException("Cannot reduce total seats below already booked seats.");
        }

        return eventRepository.save(event);
    }

    @Transactional
    public void deleteEvent(Long id) {
        Event event = getEventById(id);
        eventRepository.delete(event);
    }
}
