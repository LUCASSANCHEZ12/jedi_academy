package com.academia.jedi.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PadawanPatchRequest {
    private String name;
    private int age;
    private String lightSaberColor;
    private int midiChlorianCount;
    private boolean readyForTrials = false;
    private long masterID; // Master ID for searching
}
