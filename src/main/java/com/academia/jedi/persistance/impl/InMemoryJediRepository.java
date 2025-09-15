package com.academia.jedi.persistance.impl;

import com.academia.jedi.business.Singleton.JediAcademySingleton;
import com.academia.jedi.domain.entity.Jedi;
import com.academia.jedi.domain.entity.JediMaster;
import com.academia.jedi.domain.entity.Padawan;
import com.academia.jedi.domain.entity.Report;
import com.academia.jedi.persistance.JediRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryJediRepository implements JediRepository {

    protected final JediAcademySingleton academy = JediAcademySingleton.getInstance();
    private long idCounter = 1;

    @PostConstruct
    void seed() {
        // Create masters
        JediMaster yoda = JediMaster.builder()
                .id(1L)
                .name("Yoda")
                .age(900)
                .lightSaberColor("Green")
                .midiChlorianCount(20000)
                .memberOfCouncil(true)
                .padawans(null)
                .build();

        JediMaster quiGon = JediMaster.builder()
                .id(2L)
                .name("Qui-Gon Jinn")
                .age(60)
                .lightSaberColor("Green")
                .midiChlorianCount(15000)
                .memberOfCouncil(false)
                .padawans(null)
                .build();

        // Create padawans
        Padawan obiWan = Padawan.builder()
                .id(101L)
                .name("Obi-Wan Kenobi")
                .age(25)
                .lightSaberColor("Blue")
                .midiChlorianCount(13000)
                .readyForTrials(true)
                .master(quiGon)
                .build();

        Padawan luke = Padawan.builder()
                .id(102L)
                .name("Luke Skywalker")
                .age(20)
                .lightSaberColor("Green")
                .midiChlorianCount(18000)
                .readyForTrials(false)
                .master(yoda)
                .build();

        // Assign padawans to jedi masters
        yoda.setPadawans(List.of(luke));
        quiGon.setPadawans(List.of(obiWan));

        // Add to the academy
        academy.getJediMaster().add(yoda);
        academy.getJediMaster().add(quiGon);

        academy.getPadawans().add(luke);
        academy.getPadawans().add(obiWan);
    }


    @Override
    public Jedi save(Jedi jedi) {
        if (jedi.getId() == 0) {
            jedi.setId(idCounter++);
        }

        if(jedi instanceof JediMaster){
            academy.getJediMaster().add((JediMaster) jedi);
        }else {
            academy.getPadawans().add((Padawan) jedi);
        }
        return jedi;
    }


    @Override
    public Jedi findById(Long id) {
        //return Optional.ofNullable(store.get(id));

        JediMaster master = academy.getJediMaster().stream()
                .filter(m -> Objects.equals(m.getId(), id))
                .findFirst()
                .orElse(null);
        Padawan padawan = academy.getPadawans().stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst()
                .orElse(null);

        if(master != null){
            return master;
        } else return padawan;
    }

    @Override
    public void deleteById(Long id) {

        JediMaster master = academy.getJediMaster().stream()
                .filter(m -> Objects.equals(m.getId(), id))
                .findFirst()
                .orElse(null);
        Padawan padawan = academy.getPadawans().stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst()
                .orElse(null);

        if(master != null){
            academy.getJediMaster().remove(master);
        } else if (padawan != null) {
            academy.getPadawans().remove(padawan);
        }
    }

    @Override
    public List<Jedi> findByIds(List<Long> ids) {

        ArrayList<Jedi> jediList = new ArrayList<>();
        ArrayList<Jedi> aux = new ArrayList<>();
        aux.addAll(academy.getJediMaster());
        aux.addAll(academy.getPadawans());
        for (Long id : ids) {
            aux.stream()
                    .filter(j -> Objects.equals(j.getId(), id))
                    .findFirst()
                    .map(jediList::add);
        }
        return jediList;
    }

    @Override
    public Report getReport() {

        List<JediMaster> masters = academy.getJediMaster();
        List<Padawan> padawans = academy.getPadawans();

        int count_masters = masters.size();
        int count_padawans = padawans.size();
        int avg_padawansPerMaster = 0;

        int avg = 0;
        for(JediMaster master: masters){
            avg += master.getPadawans().size();
        }
        avg_padawansPerMaster = avg/count_masters;

        return Report.builder()
                .total_masters(count_masters)
                .total_padawans(count_padawans)
                .avg_padawansPerMaster(avg_padawansPerMaster)
                .build();

    }
}
