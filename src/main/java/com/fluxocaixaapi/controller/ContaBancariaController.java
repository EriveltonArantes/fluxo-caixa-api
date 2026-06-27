package com.fluxocaixaapi.controller;

import com.fluxocaixaapi.dto.ContaBancariaRequestDTO;
import com.fluxocaixaapi.dto.ContaBancariaResponseDTO;
import com.fluxocaixaapi.service.ContaBancariaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "ContaBancaria", description = "Gerenciamento de contabancarias")
@RestController
@RequestMapping("/api/contabancarias")
public class ContaBancariaController {

    @Autowired
    private ContaBancariaService service;

    @Operation(summary = "Listar todos os ContaBancaria")
    @GetMapping
    public List<ContaBancariaResponseDTO> listar(@RequestParam(required = false) String banco) {
        List<ContaBancariaResponseDTO> resultado = service.listar();
        if (banco != null && !banco.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getBanco() != null &&
                item.getBanco().toLowerCase().contains(banco.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar ContaBancaria por ID")
    @GetMapping("/{id}")
    public ContaBancariaResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar ContaBancaria")
    @PostMapping
    public ResponseEntity<ContaBancariaResponseDTO> criar(@Valid @RequestBody ContaBancariaRequestDTO contaBancaria) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(contaBancaria));
    }

    @Operation(summary = "Atualizar ContaBancaria")
    @PutMapping("/{id}")
    public ContaBancariaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ContaBancariaRequestDTO contaBancaria) {
        return service.atualizar(id, contaBancaria);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir ContaBancaria")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
