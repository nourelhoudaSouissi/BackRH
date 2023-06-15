package com.csidigital.rh.dao.repository;

import com.csidigital.rh.dao.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
}
