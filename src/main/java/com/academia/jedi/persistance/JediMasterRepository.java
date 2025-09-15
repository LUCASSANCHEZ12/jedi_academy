package com.academia.jedi.persistance;

import com.academia.jedi.domain.entity.JediMaster;
import com.academia.jedi.domain.entity.Padawan;

import java.util.List;

public interface JediMasterRepository extends JediRepository {
    List<JediMaster> findAll();
    List<JediMaster> findCouncilMembers();
    void AssignPadawan(Long masterId, Padawan padawan);
    JediMaster update(JediMaster jediMaster);
}
