package com.academia.jedi.business.service.impl;

import com.academia.jedi.business.service.JediService;
import com.academia.jedi.domain.entity.Report;
import com.academia.jedi.persistance.JediMasterRepository;
import org.springframework.stereotype.Service;

@Service
public class JediServiceImpl implements JediService {

    private final JediMasterRepository repository;

    public JediServiceImpl(JediMasterRepository repository) {
        this.repository = repository;
    }

    @Override
    public Report getReport() {
        return repository.getReport();
    }
}
