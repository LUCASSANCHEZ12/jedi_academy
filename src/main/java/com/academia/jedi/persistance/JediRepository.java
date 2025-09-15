package com.academia.jedi.persistance;

import com.academia.jedi.domain.entity.Jedi;
import com.academia.jedi.domain.entity.Report;

import java.util.List;
import java.util.Optional;

public interface JediRepository {
    Jedi save(Jedi jedi);
    Jedi findById(Long id);
    void deleteById(Long id);
    List<Jedi> findByIds(List<Long> ids);

    Report getReport();
}
