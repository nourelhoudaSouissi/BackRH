package com.csidigital.rh.management.service.impl;

import com.csidigital.rh.dao.entity.Calendar;
import com.csidigital.rh.dao.entity.Holiday;
import com.csidigital.rh.dao.entity.WeekendUpdated;
import com.csidigital.rh.dao.repository.CalendarRepository;
import com.csidigital.rh.dao.repository.HolidayRepository;
import com.csidigital.rh.dao.repository.WeekendUpdatedRepository;
import com.csidigital.rh.management.service.CalendarService;
import com.csidigital.rh.shared.dto.request.CalendarRequest;
import com.csidigital.rh.shared.dto.response.CalendarResponse;
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
    private WeekendUpdatedRepository weekendUpdatedRepository ;
    @Autowired
    private HolidayRepository holidayRepository ;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    @Transactional
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
            for (int i = 0; i < request.getWeekendUpdateds().size(); i++) {
                //savedCalendar.getWeekendUpdateds().add(request.getWeekendUpdateds().get(i));
                request.getWeekendUpdateds().get(i).setCalendar(savedCalendar);
             //   request.getWeekendUpdateds().get(i).setId(null);
                weekendUpdatedRepository.save(request.getWeekendUpdateds().get(i));
        }
        return modelMapper.map(savedCalendar, CalendarResponse.class);
    }


    @Override
    public List<CalendarResponse> getAllCalendars() {
        List<Calendar> calendars = calendarRepository.findAll();
        List<CalendarResponse> cdrRespons = new ArrayList<>();

        for (Calendar calendar : calendars) {
            CalendarResponse response = modelMapper.map(calendar, CalendarResponse.class);
            cdrRespons.add(response);
        }

        return cdrRespons;
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
        List<Holiday> holidays = request.getHolidays();
        List<WeekendUpdated> weekendUpdateds = request.getWeekendUpdateds();

        for (Holiday holiday:
                holidays) {
            holiday.setCalendar(savedCalendar);
            holidayRepository.save(holiday);
        }

        for (WeekendUpdated weekendUpdated :
                weekendUpdateds) {
            weekendUpdated.setCalendar(savedCalendar);
            weekendUpdatedRepository.save(weekendUpdated);
        }
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

    @Override
    public List<WeekendUpdated> getCalendarWeekends(Long id) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calendar with id " + id + " not found"));
        List<WeekendUpdated> weekendUpdateds = calendar.getWeekendUpdateds();

        return weekendUpdateds;
    }

    @Override
    public List<Holiday> getCalendarHolidays(Long id) {
        Calendar calendar = calendarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Calendar with id " + id + " not found"));
        List<Holiday> holidays = calendar.getHolidays();

        return holidays;
    }
}
