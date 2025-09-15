package com.academia.jedi.business.service.impl;

import com.academia.jedi.business.FactoryMethod.PadawanFactory;
import com.academia.jedi.business.service.PadawanService;
import com.academia.jedi.domain.dto.PadawanDTO;
import com.academia.jedi.domain.dto.request.PadawanPatchRequest;
import com.academia.jedi.domain.dto.request.PadawanRequest;
import com.academia.jedi.domain.entity.JediMaster;
import com.academia.jedi.domain.entity.Padawan;
import com.academia.jedi.persistance.PadawanRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PadawanServiceImpl implements PadawanService {

    private final PadawanRepository repository;

    private Long count = 0L;
    private final PadawanFactory factory = new PadawanFactory();

    public PadawanServiceImpl(PadawanRepository repository) {
        this.repository = repository;
    }

    @Override
    public PadawanDTO create(PadawanRequest request) {
        Padawan padawan = (Padawan) factory.createJedi(count,
                request.getName(),
                request.getAge(),
                request.getLightSaberColor(),
                request.getMidiChlorianCount()
        );
        count++;
        return toDto(padawan);
    }

    @Override
    public PadawanDTO findById(Long id) {
        return toDto((Padawan)repository.findById(id));
    }

    @Override
    public List<PadawanDTO> getAll() {
        return repository.findAll().stream()
                .map(PadawanServiceImpl::toDto)
                .toList();

    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public PadawanDTO update(Long id, PadawanRequest request) {
        Padawan padawan = (Padawan) factory.createJedi(count,
                request.getName(),
                request.getAge(),
                request.getLightSaberColor(),
                request.getMidiChlorianCount()
        );
        padawan.setMaster((JediMaster) repository.findById(request.getMasterID()));
        padawan.setReadyForTrials(request.isReadyForTrials());
        return toDto(repository.update(padawan));
    }

    @Override
    public PadawanDTO patch(Long id, PadawanPatchRequest request) {
        Padawan padawan = (Padawan) factory.createJedi(count,
                request.getName(),
                request.getAge(),
                request.getLightSaberColor(),
                request.getMidiChlorianCount()
        );
        padawan.setMaster((JediMaster) repository.findById(request.getMasterID()));
        padawan.setReadyForTrials(request.isReadyForTrials());
        return toDto(repository.update(padawan));
    }

    @Override
    public List<PadawanDTO> findByIds(List<Long> ids) {
        return repository.findByIds(ids).stream().map( j -> toDto((Padawan) j)).toList();
    }

    @Override
    public PadawanDTO assignMaster(Long padawanId, Long masterId) {
        Padawan padawan = (Padawan) repository.findById(padawanId);
        JediMaster master = (JediMaster) repository.findById(masterId);
        padawan.setMaster(master);
        return toDto(padawan);
    }

    @Override
    public List<PadawanDTO> getReadyForTrials(List<Long> padawanIds) {
        return padawanIds.stream()
                .map(p-> (Padawan)repository.findById(p))
                .map(PadawanServiceImpl::toDto)
                .toList();
    }

    private static PadawanDTO toDto(Padawan padawan) {
        return PadawanDTO.builder()
                .id(padawan.getId())
                .name(padawan.getName())
                .age(padawan.getAge())
                .lightSaberColor(padawan.getLightSaberColor())
                .midiChlorianCount(padawan.getMidiChlorianCount())
                .master(padawan.getMaster().getName())
                .masterId(padawan.getMaster().getId())
                .readyForTrials(padawan.isReadyForTrials())
                .build();
    }
}
