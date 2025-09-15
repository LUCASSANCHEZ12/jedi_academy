package com.academia.jedi.domain.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JediRequest {
    @NotNull(message = "Name is required")
    @Size(min = 2, max = 50)
    private String name;
    @Min(value = 10, message = "Age must be at least 10")
    private int age;
    @NotNull(message = "Lightsaber color is required")
    private String lightSaberColor;
    @Min(value = 100, message = "Midichlorian count can't be less than 100")
    private int midiChlorianCount;
}
