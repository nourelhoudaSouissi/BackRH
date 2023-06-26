package com.csidigital.rh.management.service;

import com.csidigital.rh.shared.dto.request.WeekendUpdatedRequest;
import com.csidigital.rh.shared.dto.response.WeekendUpdatedResponse;

import java.util.List;

public interface WeekendUpdatedService {
    WeekendUpdatedResponse createWeekendUpdated(WeekendUpdatedRequest request);
    List<WeekendUpdatedResponse> getAllWeekendUpdated();
    WeekendUpdatedResponse getWeekendUpdatedById(Long id);

    WeekendUpdatedResponse updateWeekendUpdated(WeekendUpdatedRequest request, Long id);

    void deleteWeekendUpdated(Long id);

}
