package com.csidigital.rh.management.controller;

import com.csidigital.rh.management.service.impl.CalendarImpl;
import com.csidigital.rh.management.service.impl.HolidayImpl;
import com.csidigital.rh.shared.dto.request.CalendarRequest;
import com.csidigital.rh.shared.dto.request.HolidayRequest;
import com.csidigital.rh.shared.dto.response.CalendarResponse;
import com.csidigital.rh.shared.dto.response.HolidayResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/createCalendar")
    public CalendarResponse createCalendar(@Valid @RequestBody CalendarRequest calendarRequest){
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
}
