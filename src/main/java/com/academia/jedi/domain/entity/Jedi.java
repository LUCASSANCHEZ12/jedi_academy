package com.academia.jedi.domain.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Schema(description = "Entidad que representa a un Jedi")
public class Jedi {
    private Long id;
    @Schema(description = "Nombre de un Jedi", example = "Obiwan Kenobi")
    private String name;
    private int age;
    @Schema(description = "Color del sable de luz de un Jedi", example = "Morado")
    private String lightSaberColor;
    @Schema(description = "Conteo de midichlorias de un Jedi", example = "20000")
    private int midiChlorianCount;
}
