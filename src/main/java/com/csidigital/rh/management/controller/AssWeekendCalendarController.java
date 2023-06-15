package com.csidigital.rh.management.controller;

import com.csidigital.rh.management.service.impl.AssWeekendCalendarImpl;
import com.csidigital.rh.management.service.impl.HolidayImpl;
import com.csidigital.rh.shared.dto.request.AssWeekendCalendarRequest;
import com.csidigital.rh.shared.dto.request.HolidayRequest;
import com.csidigital.rh.shared.dto.response.AssWeekendCalendarResponse;
import com.csidigital.rh.shared.dto.response.HolidayResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rh/assWeekendCalendar")
public class AssWeekendCalendarController {
    @Autowired
    private AssWeekendCalendarImpl assWeekendCalendarImpl ;

    @GetMapping("getAllAssWeekendCalendar")
    public List<AssWeekendCalendarResponse> getAllAssWeekendCalendar() {
        return assWeekendCalendarImpl.getAllAssWeekendCalendar();
    }

    @GetMapping("/getAssWeekendCalendarById/{id}")
    public AssWeekendCalendarResponse getAssWeekendCalendarById(@PathVariable Long id){

        return assWeekendCalendarImpl.getAssWeekendCalendarById(id);
    }

    @PostMapping("/createAssWeekendCalendar")
    public AssWeekendCalendarResponse createAssWeekendCalendar(@Valid @RequestBody AssWeekendCalendarRequest assWeekendCalendarRequest){
        return assWeekendCalendarImpl.createAssWeekendCalendar(assWeekendCalendarRequest);
    }

    @PutMapping("/updateAssWeekendCalendar/{id}")
    public AssWeekendCalendarResponse updateAssWeekendCalendar(@Valid @RequestBody AssWeekendCalendarRequest assWeekendCalendarRequest,
                                         @PathVariable Long id){
        return assWeekendCalendarImpl.updateAssWeekendCalendar(assWeekendCalendarRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAssWeekendCalendar(@PathVariable Long id){

        assWeekendCalendarImpl.deleteAssWeekendCalendar(id);
    }
}
