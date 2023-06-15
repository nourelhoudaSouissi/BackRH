package com.csidigital.rh.management.service.impl;

import com.csidigital.rh.dao.entity.Calendar;
import com.csidigital.rh.dao.entity.Holiday;
import com.csidigital.rh.dao.repository.CalendarRepository;
import com.csidigital.rh.dao.repository.HolidayRepository;
import com.csidigital.rh.management.service.CalendarService;
import com.csidigital.rh.shared.dto.request.CalendarRequest;
import com.csidigital.rh.shared.dto.response.CalendarResponse;
import com.csidigital.rh.shared.dto.response.HolidayResponse;
import com.csidigital.rh.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CalendarImpl implements CalendarService {

    @Autowired
    private CalendarRepository calendarRepository ;
    @Autowired
    private HolidayRepository holidayRepository ;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CalendarResponse createCalendar(CalendarRequest request) {
        Calendar calendar = modelMapper.map(request, Calendar.class);
        Calendar savedCalendar = calendarRepository.save(calendar);
        List<Holiday> holidays = request.getHolidays();

        String referencePrefix = "Calendar";
        Long calendarCount = calendarRepository.countCalendars();
        String calendarSuffix = String.format("%04d", calendarCount);

        String finalReference = referencePrefix + "_" + calendarSuffix;
        savedCalendar.setReference(finalReference);

       if (holidays != null) {
            for (Holiday holiday : holidays) {
                holiday.setCalendar(savedCalendar);
                //holiday.setId(null);
                holidayRepository.save(holiday);
            }
        }

        return modelMapper.map(savedCalendar, CalendarResponse.class);
    }


    @Override
    public List<CalendarResponse> getAllCalendars() {
        List<Calendar> calendars = calendarRepository.findAll();
        List<CalendarResponse> calendarResponses = new ArrayList<>();


        for (Calendar calendar: calendars) {
            CalendarResponse response = modelMapper.map(calendar, CalendarResponse.class);
            calendarResponses.add(response);
        }

        return calendarResponses;
    }

    @Override
    public CalendarResponse getCalendarById(Long id) {
        Calendar calendar =calendarRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Calendar with id " +id+ " not found"));
        CalendarResponse calendarResponse = modelMapper.map(calendar, CalendarResponse.class);
        return calendarResponse;
    }

    @Override
    public CalendarResponse updateCalendar(CalendarRequest request, Long id) {
        Calendar existingCalendar = calendarRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Calendar with id: " + id + " not found"));
        modelMapper.map(request, existingCalendar);
        Calendar savedCalendar = calendarRepository.save(existingCalendar);
        return modelMapper.map(savedCalendar, CalendarResponse.class);
    }

    @Override
    public void deleteCalendar(Long id) {
        calendarRepository.deleteById(id);

    }

    @Override
    public Long countCalendars() {
       return calendarRepository.countCalendars();
    }
}
