package com.csidigital.rh.management.controller;

import com.csidigital.rh.management.service.impl.HolidayImpl;
import com.csidigital.rh.management.service.impl.RecoveryLeaveImpl;
import com.csidigital.rh.shared.dto.request.HolidayRequest;
import com.csidigital.rh.shared.dto.request.RecoveryLeaveRequest;
import com.csidigital.rh.shared.dto.response.HolidayResponse;
import com.csidigital.rh.shared.dto.response.RecoveryLeaveResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rh/recoveryLeave")
public class RecoveryLeaveController {

    @Autowired
    private RecoveryLeaveImpl recoveryLeaveImpl ;

    @GetMapping("getAllRecoveryLeaves")
    public List<RecoveryLeaveResponse> getAllRecoveryLeaves() {
        return recoveryLeaveImpl.getAllRecoveryLeaves();
    }

    @GetMapping("/getRecoveryLeaveById/{id}")
    public RecoveryLeaveResponse getRecoveryLeaveById(@PathVariable Long id){
        return recoveryLeaveImpl.getRecoveryLeaveById(id);
    }

    @PostMapping("/addRecoveryLeave")
    public RecoveryLeaveResponse createRecoveryLeave(@Valid @RequestBody RecoveryLeaveRequest recoveryLeaveRequest){
        return recoveryLeaveImpl.createRecoveryLeave(recoveryLeaveRequest);
    }

    @PutMapping("/updateRecoveryLeave/{id}")
    public RecoveryLeaveResponse updateRecoveryLeave(@Valid @RequestBody RecoveryLeaveRequest recoveryLeaveRequest,
                                         @PathVariable Long id){
        return recoveryLeaveImpl.updateRecoveryLeave(recoveryLeaveRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRecoveryLeave(@PathVariable Long id){
        recoveryLeaveImpl.deleteRecoveryLeave(id);
    }

    @PutMapping("/updateStatusToValidatedById/{id}")
    void updateStatusToValidatedById(@PathVariable Long id){
        recoveryLeaveImpl.updateStatusToValidatedById(id);
    }
    @PutMapping("/updateStatusToRejectedById/{id}")
    void updateStatusToRejectedById(@PathVariable Long id){
        recoveryLeaveImpl.updateStatusToRejectedById(id);
    }
}
