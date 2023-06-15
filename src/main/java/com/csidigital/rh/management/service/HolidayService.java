package com.csidigital.rh.management.service;

import com.csidigital.rh.shared.dto.request.HolidayRequest;
import com.csidigital.rh.shared.dto.response.HolidayResponse;

import java.util.List;

public interface HolidayService {
    HolidayResponse createHoliday(HolidayRequest request);
    List<HolidayResponse> getAllHolidays();
    HolidayResponse getHolidayById(Long id);

    HolidayResponse updateHoliday(HolidayRequest request, Long id);

    void deleteHoliday(Long id);
}
