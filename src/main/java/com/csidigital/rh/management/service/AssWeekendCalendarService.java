package com.csidigital.rh.management.service;

import com.csidigital.rh.shared.dto.request.AssWeekendCalendarRequest;
import com.csidigital.rh.shared.dto.request.HolidayRequest;
import com.csidigital.rh.shared.dto.response.AssWeekendCalendarResponse;
import com.csidigital.rh.shared.dto.response.HolidayResponse;

import java.util.List;

public interface AssWeekendCalendarService {
    AssWeekendCalendarResponse createAssWeekendCalendar(AssWeekendCalendarRequest request);
    List<AssWeekendCalendarResponse> getAllAssWeekendCalendar();
    AssWeekendCalendarResponse getAssWeekendCalendarById(Long id);

    AssWeekendCalendarResponse updateAssWeekendCalendar(AssWeekendCalendarRequest request, Long id);

    void deleteAssWeekendCalendar(Long id);

}
