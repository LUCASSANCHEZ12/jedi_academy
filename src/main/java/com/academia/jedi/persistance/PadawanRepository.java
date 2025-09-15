package com.academia.jedi.persistance;

import com.academia.jedi.domain.entity.Padawan;

import java.util.List;

public interface PadawanRepository extends JediRepository {
    List<Padawan> findAll();
    List<Padawan> findReadyForTrials();
    void AssignMaster(Long masterId, Padawan padawan);
    Padawan update(Padawan padawan);
}
