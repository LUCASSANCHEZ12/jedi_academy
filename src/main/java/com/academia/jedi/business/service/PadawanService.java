package com.academia.jedi.business.service;

import com.academia.jedi.domain.dto.PadawanDTO;
import com.academia.jedi.domain.dto.request.PadawanPatchRequest;
import com.academia.jedi.domain.dto.request.PadawanRequest;

import java.util.List;

public interface PadawanService {
    PadawanDTO create(PadawanRequest request);
    PadawanDTO findById(Long id);
    List<PadawanDTO> getAll();
    void deleteById(Long id);
    PadawanDTO update(Long id, PadawanRequest request);
    PadawanDTO patch(Long id, PadawanPatchRequest request);
    List<PadawanDTO> findByIds(List<Long> ids);
    PadawanDTO assignMaster(Long padawanId, Long masterId);
    List<PadawanDTO> getReadyForTrials(List<Long> padawanIds);
}
