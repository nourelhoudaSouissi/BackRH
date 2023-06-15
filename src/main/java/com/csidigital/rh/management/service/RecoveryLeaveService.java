package com.csidigital.rh.management.service;

import com.csidigital.rh.shared.dto.request.OfferRequest;
import com.csidigital.rh.shared.dto.request.RecoveryLeaveRequest;
import com.csidigital.rh.shared.dto.response.OfferResponse;
import com.csidigital.rh.shared.dto.response.RecoveryLeaveResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecoveryLeaveService {
    RecoveryLeaveResponse createRecoveryLeave(RecoveryLeaveRequest request);
    List<RecoveryLeaveResponse> getAllRecoveryLeaves();
    RecoveryLeaveResponse getRecoveryLeaveById(Long id);

    RecoveryLeaveResponse updateRecoveryLeave(RecoveryLeaveRequest request, Long id);

    void deleteRecoveryLeave(Long id);

    void updateStatusToValidatedById( Long id);

    void updateStatusToRejectedById(Long id);
}
