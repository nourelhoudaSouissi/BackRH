package com.csidigital.rh.management.service.impl;

import com.csidigital.rh.dao.entity.Weekend;
import com.csidigital.rh.dao.entity.WeekendUpdated;
import com.csidigital.rh.dao.repository.WeekendRepository;
import com.csidigital.rh.dao.repository.WeekendUpdatedRepository;
import com.csidigital.rh.management.service.WeekendUpdatedService;
import com.csidigital.rh.shared.dto.request.WeekendUpdatedRequest;
import com.csidigital.rh.shared.dto.response.WeekendResponse;
import com.csidigital.rh.shared.dto.response.WeekendUpdatedResponse;
import com.csidigital.rh.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class WeekendUpdatedImpl implements WeekendUpdatedService {

    @Autowired
    private WeekendUpdatedRepository weekendUpdatedRepository ;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public WeekendUpdatedResponse createWeekendUpdated(WeekendUpdatedRequest request) {
        WeekendUpdated weekendUpdated = modelMapper.map(request, WeekendUpdated.class);
        WeekendUpdated savedWeekendUpdated = weekendUpdatedRepository.save(weekendUpdated);
        return modelMapper.map(savedWeekendUpdated, WeekendUpdatedResponse.class);
    }

    @Override
    public List<WeekendUpdatedResponse> getAllWeekendUpdated() {
        List<WeekendUpdated> weekendUpdatedList = weekendUpdatedRepository.findAll();
        List<WeekendUpdatedResponse> weekendUpdatedResponses = new ArrayList<>();


        for (WeekendUpdated weekendUpdated: weekendUpdatedList) {
            WeekendUpdatedResponse response = modelMapper.map(weekendUpdated, WeekendUpdatedResponse.class);
            weekendUpdatedResponses.add(response);
        }

        return weekendUpdatedResponses;
    }

    @Override
    public WeekendUpdatedResponse getWeekendUpdatedById(Long id) {
        WeekendUpdated weekendUpdated = weekendUpdatedRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Weekend Updated with id " +id+ " not found"));
        WeekendUpdatedResponse weekendUpdatedResponse = modelMapper.map(weekendUpdated, WeekendUpdatedResponse.class);
        return weekendUpdatedResponse;
    }

    @Override
    public WeekendUpdatedResponse updateWeekendUpdated(WeekendUpdatedRequest request, Long id) {
        WeekendUpdated existingWeekendUpdated = weekendUpdatedRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Weekend Updated with id: " + id + " not found"));
        modelMapper.map(request, existingWeekendUpdated);
        WeekendUpdated savedWeekendUpdated = weekendUpdatedRepository.save(existingWeekendUpdated);
        return modelMapper.map(savedWeekendUpdated, WeekendUpdatedResponse.class);
    }

    @Override
    public void deleteWeekendUpdated(Long id) {
        weekendUpdatedRepository.deleteById(id);
    }


}
