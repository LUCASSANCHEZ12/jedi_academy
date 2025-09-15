package com.academia.jedi.domain.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class JediMasterDTO extends JediDTO {
    // A list of padawans
    private Map<Long, String> padawans;
    private boolean memberOfCouncil;

}