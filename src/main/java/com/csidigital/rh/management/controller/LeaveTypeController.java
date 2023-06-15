package com.csidigital.rh.management.controller;

import com.csidigital.rh.management.service.impl.LeaveTypeImpl;
import com.csidigital.rh.management.service.impl.RecoveryLeaveImpl;
import com.csidigital.rh.shared.dto.request.LeaveTypeRequest;
import com.csidigital.rh.shared.dto.request.RecoveryLeaveRequest;
import com.csidigital.rh.shared.dto.response.LeaveTypeResponse;
import com.csidigital.rh.shared.dto.response.RecoveryLeaveResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rh/leaveType")
public class LeaveTypeController {

    @Autowired
    private LeaveTypeImpl leaveTypeImpl ;

    @GetMapping("getAllLeaveTypes")
    public List<LeaveTypeResponse> getAllLeaveTypes() {
        return leaveTypeImpl.getAllLeaveTypes();
    }

    @GetMapping("/getLeaveTypeById/{id}")
    public LeaveTypeResponse getLeaveTypeById(@PathVariable Long id){
        return leaveTypeImpl.getLeaveTypeById(id);
    }

    @PostMapping("/addLeaveType")
    public LeaveTypeResponse createLeaveType(@Valid @RequestBody LeaveTypeRequest leaveTypeRequest){
        return leaveTypeImpl.createLeaveType(leaveTypeRequest);
    }

    @PutMapping("/updateRecoveryLeave/{id}")
    public LeaveTypeResponse updateLeaveType(@Valid @RequestBody LeaveTypeRequest leaveTypeRequest,
                                                     @PathVariable Long id){
        return leaveTypeImpl.updateLeaveType(leaveTypeRequest, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLeaveType(@PathVariable Long id){
        leaveTypeImpl.deleteLeaveType(id);
    }
}
