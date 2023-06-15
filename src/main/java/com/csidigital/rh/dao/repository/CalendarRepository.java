package com.csidigital.rh.dao.repository;

import com.csidigital.rh.dao.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    @Query("SELECT COUNT(e) FROM Calendar e")
    Long countCalendars();
}

