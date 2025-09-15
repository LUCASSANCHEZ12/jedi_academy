package com.academia.jedi.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JediMasterRequest extends JediRequest{
    private boolean memberOfCouncil = false;
    @NotNull(message = "List of padawan Id's can't be null")
    @Size(min = 1, message = "List of padawan Id's can't be empty")
    private List<Long> padawanIds; // padawan IDs for search and assignation
}
