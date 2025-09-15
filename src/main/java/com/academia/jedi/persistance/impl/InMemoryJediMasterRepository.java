package com.academia.jedi.persistance.impl;

import com.academia.jedi.domain.entity.JediMaster;
import com.academia.jedi.domain.entity.Padawan;
import com.academia.jedi.persistance.JediMasterRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class InMemoryJediMasterRepository extends InMemoryJediRepository implements JediMasterRepository{


    @Override
    public List<JediMaster> findAll() {
        return academy.getJediMaster();
    }

    @Override
    public List<JediMaster> findCouncilMembers() {
        return academy.getJediMaster().stream()
                .filter(JediMaster::isMemberOfCouncil)
                .toList();
    }

    @Override
    public void AssignPadawan(Long masterId, Padawan padawan) {
        List<JediMaster> jediMasterList = academy.getJediMaster();
        for (JediMaster master : jediMasterList) {
            if (Objects.equals(master.getId(), masterId)) {
                List<Padawan> padawans = master.getPadawans();
                padawans.add(padawan);
                master.setPadawans(padawans);
                break;
            }
        }
        academy.setJediMaster(jediMasterList);
    }

    @Override
    public JediMaster update(JediMaster jediMaster) {
        return academy.getJediMaster().stream()
                .filter(p -> Objects.equals(p.getId(), jediMaster.getId()))
                .peek(m-> {
                    academy.getJediMaster().remove(m);
                    academy.getJediMaster().add(jediMaster);
                })
                .findFirst()
                .orElse(null);
    }
}
