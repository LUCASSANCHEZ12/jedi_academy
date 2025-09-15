package com.academia.jedi.business.FactoryMethod;

import com.academia.jedi.domain.entity.Jedi;

public abstract class JediFactory {
    /*
    * private long id;
    * private String name;
    * private int age;
    * private String lightSaberColor;
    * private int midiChlorianCount;
    */
    public abstract Jedi createJedi(Long id, String name, int age, String lightsaberColor, int midiChlorianCount);
}
