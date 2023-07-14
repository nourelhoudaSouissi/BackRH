package com.csidigital.rh.management.controller;

import com.csidigital.rh.dao.entity.Holiday;
import com.csidigital.rh.dao.entity.WeekendUpdated;
import com.csidigital.rh.management.service.impl.CalendarImpl;
import com.csidigital.rh.shared.dto.request.CalendarRequest;
import com.csidigital.rh.shared.dto.response.CalendarResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rh/calendar")
public class CalendarController {
    @Autowired
    private CalendarImpl calendarImpl ;
    @GetMapping("getAllCalendars")
    public List<CalendarResponse> getAllCalendars() {
        return calendarImpl.getAllCalendars();
    }

    @GetMapping("/getCalendarById/{id}")
    public CalendarResponse getCalendarById(@PathVariable Long id){

        return calendarImpl.getCalendarById(id);
    }

     @PostMapping( "/createCalendar")
    public CalendarResponse createCalendar(@Valid @RequestBody CalendarRequest calendarRequest){
        System.out.println(calendarRequest);
        return calendarImpl.createCalendar(calendarRequest);
    }

    @PutMapping("/updateCalendar/{id}")
    public CalendarResponse updateCalendar(@Valid @RequestBody CalendarRequest calendarRequest,
                                           @PathVariable Long id){
        return calendarImpl.updateCalendar(calendarRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCalendar(@PathVariable Long id){

        calendarImpl.deleteCalendar(id);
    }

    @GetMapping("/getCalendarCount")
    public Long countEquipment() {
        return calendarImpl.countCalendars();
    }
    @GetMapping("/{id}/getCalendarWeekends")
    public List<WeekendUpdated> getCalendarWeekends(@PathVariable Long id) {
        return calendarImpl.getCalendarWeekends(id);
    }
    @GetMapping("/{id}/getCalendarHolidays")
    public List<Holiday> getCalendarHolidays(@PathVariable Long id) {
        return calendarImpl.getCalendarHolidays(id);
    }
}

