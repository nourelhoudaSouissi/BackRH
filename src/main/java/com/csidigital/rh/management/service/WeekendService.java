package com.csidigital.rh.management.service;

import com.csidigital.rh.shared.dto.request.LeaveTypeRequest;
import com.csidigital.rh.shared.dto.request.WeekendRequest;
import com.csidigital.rh.shared.dto.response.LeaveTypeResponse;
import com.csidigital.rh.shared.dto.response.WeekendResponse;

import java.util.List;

public interface WeekendService {
    WeekendResponse createWeekend(WeekendRequest request);
    List<WeekendResponse> getAllWeekend();
    WeekendResponse getWeekendById(Long id);

    WeekendResponse updateWeekend(WeekendRequest request, Long id);

    void deleteWeekend(Long id);
}
