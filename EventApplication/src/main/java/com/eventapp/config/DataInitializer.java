package com.eventapp.config;

import com.eventapp.entity.Event;
import com.eventapp.entity.User;
import com.eventapp.repository.EventRepository;
import com.eventapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, EventRepository eventRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setName("Admin User");
                admin.setEmail("admin@test.com");
                admin.setPassword("admin123");
                admin.setRole("ROLE_ADMIN");

                userRepository.save(admin);

                User user = new User();
                user.setName("Normal User");
                user.setEmail("user@test.com");
                user.setPassword("user123");
                user.setRole("ROLE_USER");

                userRepository.save(user);
            }

            if (eventRepository.count() == 0) {
                Event event1 = new Event();
                event1.setTitle("Tech Conference 2024");
                event1.setDescription("A massive technology conference covering AI, Cloud, and Web Development.");
                event1.setDate(LocalDate.now().plusDays(30));
                event1.setVenue("Silicon Valley Convention Center");
                event1.setPrice(199.99);
                event1.setTotalSeats(500);
                event1.setAvailableSeats(500);
                
                Event event2 = new Event();
                event2.setTitle("Music Fest");
                event2.setDescription("Live music from top artists all weekend.");
                event2.setDate(LocalDate.now().plusDays(15));
                event2.setVenue("Central Park Arena");
                event2.setPrice(75.00);
                event2.setTotalSeats(1000);
                event2.setAvailableSeats(1000);

                eventRepository.save(event1);
                eventRepository.save(event2);
            }
        };
    }
}
