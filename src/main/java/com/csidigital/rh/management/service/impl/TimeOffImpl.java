package com.csidigital.rh.management.service.impl;

import com.csidigital.rh.dao.entity.EmailDetails;
import com.csidigital.rh.dao.entity.Employee;
import com.csidigital.rh.dao.entity.LeaveType;
import com.csidigital.rh.dao.entity.TimeOff;
import com.csidigital.rh.dao.repository.EmployeeRepository;
import com.csidigital.rh.dao.repository.LeaveTypeRepository;
import com.csidigital.rh.dao.repository.TimeOffRepository;
import com.csidigital.rh.management.service.EmployeeService;
import com.csidigital.rh.management.service.TimeOffService;
import com.csidigital.rh.shared.dto.request.TimeOffRequest;
import com.csidigital.rh.shared.dto.response.EmployeeResponse;
import com.csidigital.rh.shared.dto.response.TimeOffResponse;
import com.csidigital.rh.shared.enumeration.RequestStatus;
import com.csidigital.rh.shared.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private EmailImpl emailService;
    @Autowired
    private EmployeeService employeeService;


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

        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient(timeOff.getEmployee().getEmailOne());
        emailDetails.setMsgBody("Email body");
        emailDetails.setMsgBody("Je tiens par la présente à vous informer de mon souhait de prendre"+" " + timeOff.getTimeOffPeriod() + " Jours"+ " pour la période allant du "+ timeOff.getStartDate() +" au" + timeOff.getEndDate() + " " +" inclus.\n Je vous remercie par avance de la bienveillance que vous porterez à ma demande, et vous saurais gré de bien vouloir m’informer de votre décision avant" + timeOff.getLeaveType().getAlertNumberDays()+ " "+"jours.\n Dans cette attente, je vous prie d’agréer,"+ timeOff.getEmployee().getCivility()+ " , l’expression de mes respectueuses salutations.");
        emailDetails.setSubject("Demande de congé"+ " " +timeOff.getLeaveType().getName() );
        //emailDetails.setAttachment("path/to/attachment");

        emailService.sendSimpleMail(emailDetails);

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



   /* @Override
    public TimeOffResponse getTimeOffById(Long id) {
        TimeOff timeOff =timeOffRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("TimeOff with id " +id+ " not found"));
        TimeOffResponse timeOffResponse = modelMapper.map(timeOff, TimeOffResponse.class);
        return timeOffResponse;
    }*/
   @Override
   public TimeOffResponse getTimeOffById(Long id) {
       TimeOff timeOff = timeOffRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("TimeOff with id " + id + " not found"));

       TimeOffResponse timeOffResponse = modelMapper.map(timeOff, TimeOffResponse.class);

       // Retrieve associated Employee and calculate remainingPaidLeave
       EmployeeResponse employeeResponse = employeeService.getEmployeeById(timeOff.getEmployee().getId());

       // Set remainingPaidLeave in the TimeOffResponse
       timeOffResponse.setRemainingPaidLeave(employeeResponse.getRemainingPaidLeave());

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
    @Transactional
    public void updateStatusToValidatedById(Long id) {
        TimeOff timeOff = timeOffRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TimeOff with id " + id + " not found"));

        LeaveType leaveType = timeOff.getLeaveType();
        Employee employee = timeOff.getEmployee();

        int leaveTypeDuration = leaveType.getDuration();
        double timeOffDuration = timeOff.getTimeOffPeriod();

        double remainingBalance = leaveTypeDuration - timeOffDuration;
        // Perform further actions with the remaining balance

        try {
            // Update the leave balance for the leave type in the employee object
            employee.updateLeaveBalance(leaveType, remainingBalance);

            // Save the updated employee entity
            employeeRepository.save(employee);
        } catch (Exception e) {
            // Handle the exception appropriately, such as logging the error or rolling back the transaction
            // You can throw a custom exception or take any other necessary action
            throw new ServiceException("Error occurred while updating leave balance for TimeOff with id " + id, e);
        }

        // Update the status to validated in the repository
        timeOffRepository.updateStatusToValidatedById(id);
    }


    @Override
    public void updateStatusToRejectedById(Long id) {
        timeOffRepository.updateStatusToRejectedById(id);
    }

    @Override
    public List<Object[]> getTotalDurationByLeaveTypeAndEmployeeId(Long employeeId) {
        return timeOffRepository.getTotalDurationByLeaveTypeAndEmployeeId(employeeId);

    }

    @Override
    public List<Object[]> getTotalDurationSpecialPaidLeaveByLeaveTypeAndEmployeeId(Long employeeId) {
        return timeOffRepository.getTotalDurationSpecialPaidLeaveByLeaveTypeAndEmployeeId(employeeId);
    }

    @Override
    public List<Object[]> getTotalDurationSicknessLeaveByLeaveTypeAndEmployeeId(Long employeeId) {
        return timeOffRepository.getTotalDurationSicknessLeaveByLeaveTypeAndEmployeeId(employeeId);
    }

    @Override
    public Double getTotalDurationSpecialPaidLeaveEmployeeId(Long employeeId) {
        return timeOffRepository.getTotalDurationSpecialPaidLeaveEmployeeId(employeeId);
    }

    @Override
    public Double getTotalDurationSicknessLeaveEmployeeId(Long employeeId) {
        return timeOffRepository.getTotalDurationSicknessLeaveEmployeeId(employeeId);
    }
}
