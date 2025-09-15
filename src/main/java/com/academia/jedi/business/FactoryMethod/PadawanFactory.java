package com.academia.jedi.business.FactoryMethod;

import com.academia.jedi.domain.entity.Jedi;
import com.academia.jedi.domain.entity.Padawan;
import lombok.Data;

public class PadawanFactory extends JediFactory {
    /*
    * // 1 Master
    * private JediMaster master;
    * private boolean readyForTrials;
    */
    @Override
    public Jedi createJedi(Long id, String name, int age, String lightsaberColor, int midiChlorianCount) {
        return Padawan.builder() // Get the builder instance
                .id(id)
                .name(name)
                .age(age)
                .lightSaberColor(lightsaberColor)
                .midiChlorianCount(midiChlorianCount)
                .master(null)
                .readyForTrials(false)
                .build();
    }
}
