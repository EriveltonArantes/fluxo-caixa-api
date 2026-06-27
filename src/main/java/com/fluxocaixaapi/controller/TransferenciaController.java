package com.fluxocaixaapi.controller;

import com.fluxocaixaapi.dto.TransferenciaRequestDTO;
import com.fluxocaixaapi.dto.TransferenciaResponseDTO;
import com.fluxocaixaapi.service.TransferenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Transferencia", description = "Gerenciamento de transferencias")
@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {

    @Autowired
    private TransferenciaService service;

    @Operation(summary = "Listar todos os Transferencia")
    @GetMapping
    public List<TransferenciaResponseDTO> listar(@RequestParam(required = false) String descricao, @RequestParam(required = false) Long contaOrigemId, @RequestParam(required = false) Long contaDestinoId) {
        List<TransferenciaResponseDTO> resultado = service.listar();
        if (descricao != null && !descricao.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getDescricao() != null &&
                item.getDescricao().toLowerCase().contains(descricao.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        if (contaOrigemId != null) {
            resultado = resultado.stream().filter(item -> contaOrigemId.equals(item.getContaOrigemId())).collect(java.util.stream.Collectors.toList());
        }
        if (contaDestinoId != null) {
            resultado = resultado.stream().filter(item -> contaDestinoId.equals(item.getContaDestinoId())).collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Transferencia por ID")
    @GetMapping("/{id}")
    public TransferenciaResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Transferencia")
    @PostMapping
    public ResponseEntity<TransferenciaResponseDTO> criar(@Valid @RequestBody TransferenciaRequestDTO transferencia) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(transferencia));
    }

    @Operation(summary = "Atualizar Transferencia")
    @PutMapping("/{id}")
    public TransferenciaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody TransferenciaRequestDTO transferencia) {
        return service.atualizar(id, transferencia);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Transferencia")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
