package com.academia.jedi.business.service.impl;

import com.academia.jedi.business.FactoryMethod.JediFactory;
import com.academia.jedi.business.FactoryMethod.JediMasterFactory;
import com.academia.jedi.business.service.JediMasterService;
import com.academia.jedi.domain.dto.JediMasterDTO;
import com.academia.jedi.domain.dto.request.JediMasterPatchRequest;
import com.academia.jedi.domain.dto.request.JediMasterRequest;
import com.academia.jedi.domain.entity.JediMaster;
import com.academia.jedi.domain.entity.Padawan;
import com.academia.jedi.persistance.JediMasterRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JediMasterImpl implements JediMasterService {
    private final JediMasterRepository repository;
    private Long count = 0L;

    private final JediFactory factory = new JediMasterFactory();

    public JediMasterImpl( JediMasterRepository jediMasterRepository) {
        this.repository = jediMasterRepository;
    }

    @Override
    public JediMasterDTO create(JediMasterRequest request) {
        JediMaster master = (JediMaster) factory.createJedi(
                count,
                request.getName(),
                request.getAge(),
                request.getLightSaberColor(),
                request.getMidiChlorianCount()
        );
        count++;
        return toDto(master);
    }

    @Override
    public JediMasterDTO findById(Long id) {
        return toDto((JediMaster) repository.findById(id));
    }

    @Override
    public List<JediMasterDTO> getAll() {
        return repository.findAll().stream()
                .map(JediMasterImpl::toDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public JediMasterDTO update(Long id, JediMasterRequest request) {
        JediMaster master = (JediMaster) factory.createJedi(
                id,
                request.getName(),
                request.getAge(),
                request.getLightSaberColor(),
                request.getMidiChlorianCount()
        );
        master.setPadawans(repository.findByIds(request.getPadawanIds()).stream()
                .map( j -> (Padawan)j)
                .toList());
        master.setMemberOfCouncil(request.isMemberOfCouncil());
        return toDto(repository.update(master));
    }

    @Override
    public JediMasterDTO patch(Long id, JediMasterPatchRequest request) {
        JediMaster master = (JediMaster) factory.createJedi(
                id,
                request.getName(),
                request.getAge(),
                request.getLightSaberColor(),
                request.getMidiChlorianCount()
        );
        master.setPadawans(repository.findByIds(request.getPadawanIds()).stream()
                .map( j -> (Padawan)j)
                .toList());
        master.setMemberOfCouncil(request.isMemberOfCouncil());
        return toDto(repository.update(master));
    }

    @Override
    public List<JediMasterDTO> findByIds(List<Long> ids) {
        return repository.findByIds(ids).stream().map( j -> toDto((JediMaster) j)).toList();
    }

    @Override
    public JediMasterDTO assignPadawan(Long masterId, Long padawanId) {
        Padawan padawan = (Padawan)  repository.findById(padawanId);
        repository.AssignPadawan(masterId,padawan);
        JediMaster master = (JediMaster) repository.findById(masterId);
        return toDto(master);
    }

    @Override
    public List<JediMasterDTO> getCouncilMembers() {
        return repository.findCouncilMembers().stream()
                .map(JediMasterImpl::toDto)
                .toList();
    }

    private static JediMasterDTO toDto(JediMaster jediMaster) {
        Map<Long, String> padawans = new HashMap<>();
        jediMaster.getPadawans().forEach(p -> padawans.put(p.getId(), p.getName()));
        return JediMasterDTO.builder()
                .id(jediMaster.getId())
                .name(jediMaster.getName())
                .age(jediMaster.getAge())
                .lightSaberColor(jediMaster.getLightSaberColor())
                .midiChlorianCount(jediMaster.getMidiChlorianCount())
                .padawans(padawans)
                .memberOfCouncil(jediMaster.isMemberOfCouncil())
                .build();
    }
}
