package com.csidigital.rh.dao.repository;

import com.csidigital.rh.dao.entity.TimeOff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TimeOffRepository extends JpaRepository<TimeOff,Long> {

    @Modifying
    @Query(value = "UPDATE time_off SET request_status =:requestStatus WHERE id =:id", nativeQuery = true)
    void updateStatusById(@Param("id") Long id, @Param("requestStatus") String requestStatus);

    @Modifying
    @Query(value = " UPDATE time_off SET request_status = 'VALIDATED' WHERE id =:id", nativeQuery = true)
    void updateStatusToValidatedById(@Param("id") Long id);

    @Modifying
    @Query(value = " UPDATE time_off SET request_status = 'REJECTED' WHERE id =:id", nativeQuery = true)
    void updateStatusToRejectedById(@Param("id") Long id);

}
