package com.csidigital.rh.management.service;

import com.csidigital.rh.shared.dto.request.LeaveTypeRequest;
import com.csidigital.rh.shared.dto.response.LeaveTypeResponse;


import java.util.List;

public interface LeaveTypeService {

    LeaveTypeResponse createLeaveType(LeaveTypeRequest request);
    List<LeaveTypeResponse> getAllLeaveTypes();
    LeaveTypeResponse getLeaveTypeById(Long id);

    LeaveTypeResponse updateLeaveType(LeaveTypeRequest request, Long id);

    void deleteLeaveType(Long id);

    Double getTotalDurationSicknessLeave();

    Double getTotalDurationSpecialPaidLeave();

    List<Object[]>  getSicknessLeaveList();
    List<Object[]>  getSpecialPaidLeaveList();


}
