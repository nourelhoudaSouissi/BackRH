package com.csidigital.rh.management.service;

import com.csidigital.rh.shared.dto.request.CalendarRequest;
import com.csidigital.rh.shared.dto.request.HolidayRequest;
import com.csidigital.rh.shared.dto.response.CalendarResponse;
import com.csidigital.rh.shared.dto.response.HolidayResponse;

import java.util.List;

public interface CalendarService {
    CalendarResponse createCalendar(CalendarRequest request);
    List<CalendarResponse> getAllCalendars();
    CalendarResponse getCalendarById(Long id);

    CalendarResponse updateCalendar(CalendarRequest request, Long id);

    void deleteCalendar(Long id);

    Long countCalendars();
}
