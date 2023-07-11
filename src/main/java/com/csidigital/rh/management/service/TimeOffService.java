package com.csidigital.rh.management.service;

import com.csidigital.rh.shared.dto.request.TimeOffRequest;
import com.csidigital.rh.shared.dto.response.TimeOffResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimeOffService {

    TimeOffResponse createTimeOff(TimeOffRequest request);
    List<TimeOffResponse> getAllTimeOff();
    TimeOffResponse getTimeOffById(Long id);

    TimeOffResponse updateTimeOff(TimeOffRequest request, Long id);

    void deleteTimeOff(Long id);
    void updateStatusToValidatedById(Long id);
    void updateStatusToRejectedById( Long id);
    List<Object[]> getTotalDurationByLeaveTypeAndEmployeeId(@Param("employeeId") Long employeeId);
    List<Object[]> getTotalDurationSpecialPaidLeaveByLeaveTypeAndEmployeeId(@Param("employeeId") Long employeeId);
    List<Object[]> getTotalDurationSicknessLeaveByLeaveTypeAndEmployeeId(@Param("employeeId") Long employeeId);
    Double getTotalDurationSpecialPaidLeaveEmployeeId(@Param("employeeId") Long employeeId);
    Double getTotalDurationSicknessLeaveEmployeeId(@Param("employeeId") Long employeeId);

}
