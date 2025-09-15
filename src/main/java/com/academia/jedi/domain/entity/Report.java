package com.academia.jedi.domain.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Report {
    /*
     * Total de maestros.
     * Total de padawans.
     * Promedio de padawans por maestro. (200 OK)
     */
    private int total_masters;
    private int total_padawans;
    private int avg_padawansPerMaster;
}
