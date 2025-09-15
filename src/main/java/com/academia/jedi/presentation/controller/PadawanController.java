package com.academia.jedi.presentation.controller;


import com.academia.jedi.business.service.PadawanService;
import com.academia.jedi.common.exception.BadMasterRequest;
import com.academia.jedi.common.exception.BadPadawanRequest;
import com.academia.jedi.common.exception.JediNotFoundException;
import com.academia.jedi.common.exception.NullIDException;
import com.academia.jedi.domain.dto.JediMasterDTO;
import com.academia.jedi.domain.dto.PadawanDTO;
import com.academia.jedi.domain.dto.request.PadawanPatchRequest;
import com.academia.jedi.domain.dto.request.PadawanRequest;
import com.academia.jedi.domain.entity.Jedi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jedi/padawans")
public class PadawanController {

    private final PadawanService service;

    public PadawanController(PadawanService service) {
        this.service = service;
    }

    /*
    *
    * Gestión de Padawans
    * GET /padawans → devuelve lista de todos los aprendices. (200 OK)
    * GET /padawans/{id} → devuelve un aprendiz por ID. (200 OK / 404 Not Found)
    * POST /padawans → crea un nuevo aprendiz. (201 Created / 400 Bad Request)
    * PUT /padawans/{id} → reemplaza toda la información de un aprendiz. (200 OK / 404 Not Found)
    * PATCH /padawans/{id} → actualiza solo un campo (ej: rango). (200 OK / 400 Bad Request / 404 Not Found)
    * DELETE /padawans/{id} → elimina un aprendiz. (204 No Content / 404 Not Found)
    * */
    @Operation(
            summary = "Obtener todos los padawans",
            description = "Devuelve lista de todos los padawans"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Padawans"),
    })
    @GetMapping("/get/all")
    public List<PadawanDTO> getPadawans(){
        return service.getAll();
    }

    @Operation(
            summary = "Obtener un aprendiz por ID",
            description = "Devuelve un aprendiz por ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Padawan encontrado"),
            @ApiResponse(responseCode = "404", description = "El padawan no existe"),
    })
    @GetMapping("/get/{id}")
    public PadawanDTO getPadawan(
            @Parameter(description = "Identificador de un padawan", example = "100")
            @PathVariable Long id) throws NullIDException {
        if (id == null) {
            throw new NullIDException("Id null");
        }
        PadawanDTO padawan;
        try {
            padawan = service.findById(id);
        }catch (JediNotFoundException e){
            throw new JediNotFoundException("Padawan no encontrado");
        }

        return padawan;
    }

    @Operation(
            summary = "Registra un nuevo maestro.",
            description = "Agrega un nuevo maestro a la academia"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Maestro registrado")
    })
    @PostMapping("/create")
    public PadawanDTO createPadawan(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "Ejemplo de Padawan",
                                            summary = "",
                                            value = "{}"),
                                    @ExampleObject(name = "Ejemplo de Padawan",
                                            summary = "",
                                            value = "{}"),

                            }
                    )
            )
            @RequestBody PadawanRequest request) throws BadPadawanRequest {
        PadawanDTO padawan;
        try{
            padawan = service.create(request);
        } catch (Exception e) {
            throw new BadPadawanRequest("Bad request body");
        }
        return padawan;
    }

    @Operation(
            summary = "Actualiza o cambia la informacion de un maestro",
            description = "Reemplaza la información completa de un maestro"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Maestro actualizado"),
            @ApiResponse(responseCode = "400", description = "Mal envio de json")
    })
    @PutMapping("/update/{id}")
    public PadawanDTO updatePadawan(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "Ejemplo de Padawan",
                                            summary = "Padawan aprendiz de la academia Jedi",
                                            value = "{ \"name\": \"Anakin Skywalker\", \"age\": 12, \"lightSaberColor\": \"Blue\", \"midiChlorianCount\": 20000, \"readyForTrials\": false, \"masterID\": 201 }"),
                                    @ExampleObject(name = "Ejemplo de Padawan avanzado",
                                            summary = "Padawan próximo a convertirse en Jedi Knight",
                                            value = "{ \"name\": \"Ahsoka Tano\", \"age\": 17, \"lightSaberColor\": \"Green\", \"midiChlorianCount\": 14500, \"readyForTrials\": true, \"masterID\": 202 }")
                            }
                    )
            )
            @Parameter(description = "Identificador de un padawan", example = "100")
            @PathVariable Long id, @RequestBody PadawanRequest request) throws NullIDException {
        if (id == null) {
            throw new NullIDException("Id null");
        }
        PadawanDTO padawan;
        try {
            padawan = service.findById(id);
        }catch (JediNotFoundException e){
            throw new JediNotFoundException("Padawan no encontrado");
        }
        return service.update(id, request);
    }

    @Operation(
            summary = "Actualiza o cambia la informacion de un padawan",
            description = "Reemplaza la información de forma parcial de un padawan"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Informacion de Padawan actualizado"),
            @ApiResponse(responseCode = "400", description = "Mal envio de json")
    })
    @PatchMapping("/patch/{id}")
    public PadawanDTO patchPadawan(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(name = "Ejemplo de Padawan",
                                            summary = "Padawan aprendiz de la academia Jedi",
                                            value = "{ \"age\": 18, \"lightSaberColor\": \"Blue\", \"readyForTrials\": true }"),
                                    @ExampleObject(name = "Ejemplo de Padawan avanzado",
                                            summary = "Padawan próximo a convertirse en Jedi Knight",
                                            value = "{ \"age\": 18, \"lightSaberColor\": \"White\", \"masterID\": 204 }")
                            }
                    )
            )
            @Parameter(description = "Identificador de un padawan", example = "100")
            @PathVariable Long id, @RequestBody PadawanPatchRequest request) throws NullIDException {
        if (id == null) {
            throw new NullIDException("Id null");
        }
        PadawanDTO padawan;
        try {
            padawan = service.findById(id);
        }catch (JediNotFoundException e){
            throw new JediNotFoundException("Padawan no encontrado");
        }

        return service.patch(id, request);
    }

    @Operation(
            summary = "Elimina un padawan de la lista.",
            description = "Elimina un padawan de la orden."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Padawan eliminado"),
            @ApiResponse(responseCode = "400", description = "Mal envio de datos")
    })
    @DeleteMapping("/delete/{id}")
    public void deletePadawan(
            @Parameter(description = "Identificador de un padawan", example = "100")
            @PathVariable Long id) throws NullIDException {
        if (id == null) {
            throw new NullIDException("Id null");
        }
        PadawanDTO padawan;
        try {
            padawan = service.findById(id);
        }catch (JediNotFoundException e){
            throw new JediNotFoundException("Padawan no encontrado");
        }
        service.deleteById(id);
    }
}
