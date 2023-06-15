package com.csidigital.rh.management.service.impl;

import com.csidigital.rh.dao.entity.Employee;
import com.csidigital.rh.dao.entity.LeaveType;
import com.csidigital.rh.dao.entity.TimeOff;
import com.csidigital.rh.dao.repository.EmployeeRepository;
import com.csidigital.rh.dao.repository.LeaveTypeRepository;
import com.csidigital.rh.dao.repository.TimeOffRepository;
import com.csidigital.rh.management.service.TimeOffService;
import com.csidigital.rh.shared.dto.request.TimeOffRequest;
import com.csidigital.rh.shared.dto.response.TimeOffResponse;
import com.csidigital.rh.shared.enumeration.RequestStatus;
import com.csidigital.rh.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class TimeOffImpl implements TimeOffService {
    @Autowired
    private TimeOffRepository timeOffRepository ;

    @Autowired
    private LeaveTypeRepository leaveTypeRepository ;
    @Autowired
    private EmployeeRepository employeeRepository ;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TimeOffResponse createTimeOff(TimeOffRequest request) {

        LeaveType leaveType = null;
        Employee employee = null;
        if(request.getLeaveTypeNum()!=null) {
            leaveType = leaveTypeRepository.findById(request.getLeaveTypeNum())
                    .orElseThrow();
        }
        if(request.getEmployeeNum()!=null) {
            employee = employeeRepository.findById(request.getEmployeeNum())
                    .orElseThrow();
        }
        TimeOff timeOff = modelMapper.map(request, TimeOff.class);
        timeOff.setRequestStatus(RequestStatus.PENDING);
        timeOff.setLeaveType(leaveType);
        timeOff.setEmployee(employee);
        timeOff.setRequestInputDate(LocalDate.now());
        TimeOff TimeOffSaved = timeOffRepository.save(timeOff);
        return modelMapper.map(TimeOffSaved, TimeOffResponse.class);
    }

    @Override
    public List<TimeOffResponse> getAllTimeOff() {
        List<TimeOff> timeOff = timeOffRepository.findAll();
        List<TimeOffResponse> timeOffList = new ArrayList<>();

        for (TimeOff timeOffGet: timeOff) {
            TimeOffResponse response = modelMapper.map(timeOffGet, TimeOffResponse.class);
            response.setLeaveTypeName(timeOffGet.getLeaveType().getName());
            response.setLeaveType(timeOffGet.getLeaveType().getTimeOffType());

            response.setEmployeeFirstName(timeOffGet.getEmployee().getFirstName());
            response.setEmployeeLastName(timeOffGet.getEmployee().getLastName());

            timeOffList.add(response);
        }
        return timeOffList;
    }



    @Override
    public TimeOffResponse getTimeOffById(Long id) {
        TimeOff timeOff =timeOffRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("TimeOff with id " +id+ " not found"));
        TimeOffResponse timeOffResponse = modelMapper.map(timeOff, TimeOffResponse.class);
        return timeOffResponse;
    }

    @Override
    public TimeOffResponse updateTimeOff(TimeOffRequest request, Long id) {
        TimeOff existingTimeOff = timeOffRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("TimeOff with id: " + id + " not found"));

        modelMapper.map(request, existingTimeOff);
        TimeOff savedTimeOff = timeOffRepository.save(existingTimeOff);
        existingTimeOff.setRequestStatus(RequestStatus.PENDING);
        return modelMapper.map(savedTimeOff, TimeOffResponse.class);
    }

    @Override
    public void deleteTimeOff(Long id) {
        timeOffRepository.deleteById(id);
    }

    @Override
    public void updateStatusToValidatedById(Long id) {
        timeOffRepository.updateStatusToValidatedById(id);   }

    @Override
    public void updateStatusToRejectedById(Long id) {
        timeOffRepository.updateStatusToRejectedById(id);
    }
}
