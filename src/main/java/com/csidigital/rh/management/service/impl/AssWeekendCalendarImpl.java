package com.csidigital.rh.management.service.impl;

import com.csidigital.rh.dao.entity.AssWeekendCalendar;
import com.csidigital.rh.dao.entity.Holiday;
import com.csidigital.rh.dao.repository.AssWeekendCalendarRepository;
import com.csidigital.rh.dao.repository.HolidayRepository;
import com.csidigital.rh.management.service.AssOfferCandidateService;
import com.csidigital.rh.management.service.AssWeekendCalendarService;
import com.csidigital.rh.shared.dto.request.AssOfferCandidateRequest;
import com.csidigital.rh.shared.dto.request.AssWeekendCalendarRequest;
import com.csidigital.rh.shared.dto.response.AssOfferCandidateResponse;
import com.csidigital.rh.shared.dto.response.AssWeekendCalendarResponse;
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
public class AssWeekendCalendarImpl implements AssWeekendCalendarService {

    @Autowired
    private AssWeekendCalendarRepository assWeekendCalendarRepository ;
    @Autowired
    private ModelMapper modelMapper;



    @Override
    public AssWeekendCalendarResponse createAssWeekendCalendar(AssWeekendCalendarRequest request) {
        AssWeekendCalendar assWeekendCalendar = modelMapper.map(request, AssWeekendCalendar.class);
        AssWeekendCalendar savedAssWeekendCalendar = assWeekendCalendarRepository.save(assWeekendCalendar);
        return modelMapper.map(savedAssWeekendCalendar, AssWeekendCalendarResponse.class);
    }

    @Override
    public List<AssWeekendCalendarResponse> getAllAssWeekendCalendar() {
        List<AssWeekendCalendar> assWeekendCalendars = assWeekendCalendarRepository.findAll();
        List<AssWeekendCalendarResponse> assWeekendCalendarResponses = new ArrayList<>();

        for (AssWeekendCalendar assWeekendCalendar: assWeekendCalendars) {
            AssWeekendCalendarResponse response = modelMapper.map(assWeekendCalendar, AssWeekendCalendarResponse.class);
            assWeekendCalendarResponses.add(response);
        }

        return assWeekendCalendarResponses;
    }

    @Override
    public AssWeekendCalendarResponse getAssWeekendCalendarById(Long id) {
        AssWeekendCalendar assWeekendCalendar =assWeekendCalendarRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Holiday with id " +id+ " not found"));
        AssWeekendCalendarResponse assWeekendCalendarResponse = modelMapper.map(assWeekendCalendar, AssWeekendCalendarResponse.class);
        return assWeekendCalendarResponse;
    }

    @Override
    public AssWeekendCalendarResponse updateAssWeekendCalendar(AssWeekendCalendarRequest request, Long id) {
        AssWeekendCalendar existingAssWeekendCalendar = assWeekendCalendarRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("AssWeekendCalendar with id: " + id + " not found"));
        modelMapper.map(request, existingAssWeekendCalendar);
        AssWeekendCalendar savedAssWeekendCalendary = assWeekendCalendarRepository.save(existingAssWeekendCalendar);
        return modelMapper.map(savedAssWeekendCalendary, AssWeekendCalendarResponse.class);
    }

    @Override
    public void deleteAssWeekendCalendar(Long id) {
        assWeekendCalendarRepository.deleteById(id);
    }
}
