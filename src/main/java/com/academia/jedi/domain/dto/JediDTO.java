package com.academia.jedi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class JediDTO {
    private long id;
    private String name;
    private int age;
    private String lightSaberColor;
    private int midiChlorianCount;
}