package com.academia.jedi.business.FactoryMethod;

import com.academia.jedi.domain.entity.Jedi;
import com.academia.jedi.domain.entity.JediMaster;
import com.academia.jedi.domain.entity.Padawan;

import java.util.ArrayList;
import java.util.List;

public class JediMasterFactory extends JediFactory {

    /*
    * // A list of padawans
    * private List<Padawan> padawans;
    * private boolean memberOfCouncil;
    */
    @Override
    public Jedi createJedi(Long id, String name, int age, String lightsaberColor, int midiChlorianCount) {
        return JediMaster.builder() // Get the builder instance
                .id(id)
                .name(name)
                .age(age)
                .lightSaberColor(lightsaberColor)
                .midiChlorianCount(midiChlorianCount)
                .padawans(new ArrayList<>())
                .memberOfCouncil(false)
                .build();
    }
}
