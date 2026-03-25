package com.eventapp.repository;

import com.eventapp.entity.Booking;
import com.eventapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @EntityGraph(attributePaths = {"event"})
    List<Booking> findByUser(User user);

    @EntityGraph(attributePaths = {"event"})
    List<Booking> findByUserId(Long userId);
}
