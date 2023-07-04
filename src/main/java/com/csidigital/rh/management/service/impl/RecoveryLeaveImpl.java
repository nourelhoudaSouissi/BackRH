package com.csidigital.rh.management.service.impl;

import com.csidigital.rh.dao.entity.*;
import com.csidigital.rh.dao.repository.EmployeeRepository;
import com.csidigital.rh.dao.repository.HolidayRepository;
import com.csidigital.rh.dao.repository.RecoveryLeaveRepository;
import com.csidigital.rh.management.service.RecoveryLeaveService;
import com.csidigital.rh.shared.dto.request.RecoveryLeaveRequest;
import com.csidigital.rh.shared.dto.response.RecoveryLeaveResponse;
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
public class RecoveryLeaveImpl  implements RecoveryLeaveService {

    @Autowired
    private RecoveryLeaveRepository recoveryLeaveRepository ;
    @Autowired
    private HolidayRepository holidayRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmailImpl emailService;


    @Override
    public RecoveryLeaveResponse createRecoveryLeave(RecoveryLeaveRequest request) {
        Holiday holiday = null;
        Employee employee = null;
        if(request.getHolidayNum()!=null) {
            holiday = holidayRepository.findById(request.getHolidayNum())
                    .orElseThrow();
        }

        if(request.getEmployeeNum()!=null) {
            employee = employeeRepository.findById(request.getEmployeeNum())
                    .orElseThrow();
        }

        RecoveryLeave recoveryLeave = modelMapper.map(request, RecoveryLeave.class);
        recoveryLeave.setInputDate(LocalDate.now());
        recoveryLeave.setRequestStatus(RequestStatus.PENDING);
        recoveryLeave.setHoliday(holiday);
        recoveryLeave.setEmployee(employee);


        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(recoveryLeave.getEmployee().getEmailOne());
       // emailDetails.setMsgBody("Email body");
        emailDetails.setMsgBody("Je tiens par la présente à vous informerque j'ai travailler");
        emailDetails.setSubject("Validation de récupération");
        //emailDetails.setAttachment("path/to/attachment");

        emailService.sendSimpleMail(emailDetails);



        RecoveryLeave savedRecoveryLeave = recoveryLeaveRepository.save(recoveryLeave);
        return modelMapper.map(savedRecoveryLeave, RecoveryLeaveResponse.class);
    }

    @Override
    public List<RecoveryLeaveResponse> getAllRecoveryLeaves() {
        List<RecoveryLeave> recoveryLeaves = recoveryLeaveRepository.findAll();
        List<RecoveryLeaveResponse> recoveryLeaveResponses = new ArrayList<>();
        for (RecoveryLeave recoveryLeave: recoveryLeaves) {
            RecoveryLeaveResponse response = modelMapper.map(recoveryLeave, RecoveryLeaveResponse.class);
            recoveryLeaveResponses.add(response);
        }
        return recoveryLeaveResponses;
    }

    @Override
    public RecoveryLeaveResponse getRecoveryLeaveById(Long id) {
        RecoveryLeave recoveryLeave =recoveryLeaveRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Offer with id " +id+ " not found"));
        RecoveryLeaveResponse recoveryLeaveResponse = modelMapper.map(recoveryLeave, RecoveryLeaveResponse.class);
        return recoveryLeaveResponse;
    }

    @Override
    public RecoveryLeaveResponse updateRecoveryLeave(RecoveryLeaveRequest request, Long id) {
        RecoveryLeave existingRecoveryLeave = recoveryLeaveRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Offer with id: " + id + " not found"));
        modelMapper.map(request, existingRecoveryLeave);
        RecoveryLeave savedRecoveryLeave = recoveryLeaveRepository.save(existingRecoveryLeave);
        return modelMapper.map(savedRecoveryLeave, RecoveryLeaveResponse.class);
    }

    @Override
    public void deleteRecoveryLeave(Long id) {
        recoveryLeaveRepository.deleteById(id);
    }

    @Override
    public void updateStatusToValidatedById(Long id) {
         recoveryLeaveRepository.updateStatusToValidatedById(id);
    }

    @Override
    public void updateStatusToRejectedById(Long id) {
         recoveryLeaveRepository.updateStatusToRejectedById(id);
    }
}
