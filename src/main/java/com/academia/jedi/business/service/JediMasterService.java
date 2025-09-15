package com.academia.jedi.business.service;

import com.academia.jedi.domain.dto.JediDTO;
import com.academia.jedi.domain.dto.JediMasterDTO;
import com.academia.jedi.domain.dto.PadawanDTO;
import com.academia.jedi.domain.dto.request.JediMasterPatchRequest;
import com.academia.jedi.domain.dto.request.JediMasterRequest;
import com.academia.jedi.domain.dto.request.JediRequest;

import java.util.List;

public interface JediMasterService {
    JediMasterDTO create(JediMasterRequest request);
    JediMasterDTO findById(Long id);
    List<JediMasterDTO> getAll();
    void deleteById(Long id);
    JediMasterDTO update(Long id, JediMasterRequest request);
    JediMasterDTO patch(Long id, JediMasterPatchRequest request);
    List<JediMasterDTO> findByIds(List<Long> ids);
    JediMasterDTO assignPadawan(Long masterId, Long padawanId);
    List<JediMasterDTO> getCouncilMembers();
}
