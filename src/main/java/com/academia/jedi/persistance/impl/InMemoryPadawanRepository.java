package com.academia.jedi.persistance.impl;

import com.academia.jedi.domain.entity.JediMaster;
import com.academia.jedi.domain.entity.Padawan;
import com.academia.jedi.persistance.PadawanRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class InMemoryPadawanRepository extends InMemoryJediRepository implements PadawanRepository {

    @Override
    public List<Padawan> findAll() {
        return academy.getPadawans();
    }

    @Override
    public List<Padawan> findReadyForTrials() {
        return academy.getPadawans().stream()
                .filter(Padawan::isReadyForTrials)
                .toList();
    }

    @Override
    public void AssignMaster(Long masterId, Padawan padawan) {
        List<JediMaster> jediMasterList = academy.getJediMaster();
        List<Padawan> padawans = academy.getPadawans();
        for (JediMaster master : jediMasterList) {
            if (Objects.equals(master.getId(), masterId)) {
                padawan.setMaster(master);
                padawans.stream()
                        .filter(p -> Objects.equals(p.getId(), padawan.getId()))
                        .findFirst()
                        .map(padawans::remove);
                padawans.add(padawan);
                break;
            }
        }
        academy.setJediMaster(jediMasterList);
        academy.setPadawans(padawans);
    }

    @Override
    public Padawan update(Padawan padawan) {
        return academy.getPadawans().stream()
                .filter(p -> Objects.equals(p.getId(), padawan.getId()))
                .peek(p-> {
                    academy.getPadawans().remove(p);
                    academy.getPadawans().add(padawan);
                })
                .findFirst()
                .orElse(null);
    }
}