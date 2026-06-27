package com.fluxocaixaapi.controller;

import com.fluxocaixaapi.dto.PrevisaoFinanceiraRequestDTO;
import com.fluxocaixaapi.dto.PrevisaoFinanceiraResponseDTO;
import com.fluxocaixaapi.service.PrevisaoFinanceiraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "PrevisaoFinanceira", description = "Gerenciamento de previsaofinanceiras")
@RestController
@RequestMapping("/api/previsaofinanceiras")
public class PrevisaoFinanceiraController {

    @Autowired
    private PrevisaoFinanceiraService service;

    @Operation(summary = "Listar todos os PrevisaoFinanceira")
    @GetMapping
    public List<PrevisaoFinanceiraResponseDTO> listar(@RequestParam(required = false) String mes) {
        List<PrevisaoFinanceiraResponseDTO> resultado = service.listar();
        if (mes != null && !mes.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getMes() != null &&
                item.getMes().toLowerCase().contains(mes.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar PrevisaoFinanceira por ID")
    @GetMapping("/{id}")
    public PrevisaoFinanceiraResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar PrevisaoFinanceira")
    @PostMapping
    public ResponseEntity<PrevisaoFinanceiraResponseDTO> criar(@Valid @RequestBody PrevisaoFinanceiraRequestDTO previsaoFinanceira) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(previsaoFinanceira));
    }

    @Operation(summary = "Atualizar PrevisaoFinanceira")
    @PutMapping("/{id}")
    public PrevisaoFinanceiraResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody PrevisaoFinanceiraRequestDTO previsaoFinanceira) {
        return service.atualizar(id, previsaoFinanceira);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir PrevisaoFinanceira")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
