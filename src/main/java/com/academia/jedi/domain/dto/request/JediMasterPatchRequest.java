package com.academia.jedi.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JediMasterPatchRequest {
    private String name;
    private int age;
    private String lightSaberColor;
    private int midiChlorianCount;
    private boolean memberOfCouncil = false;
    private List<Long> padawanIds; // padawan IDs for search and assignation
}
