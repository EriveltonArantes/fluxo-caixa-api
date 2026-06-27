package com.fluxocaixaapi.controller;

import com.fluxocaixaapi.dto.LancamentoRequestDTO;
import com.fluxocaixaapi.dto.LancamentoResponseDTO;
import com.fluxocaixaapi.service.LancamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Lancamento", description = "Gerenciamento de lancamentos")
@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoService service;

    @Operation(summary = "Listar todos os Lancamento")
    @GetMapping
    public List<LancamentoResponseDTO> listar(@RequestParam(required = false) String descricao, @RequestParam(required = false) Long contaBancariaId) {
        List<LancamentoResponseDTO> resultado = service.listar();
        if (descricao != null && !descricao.isBlank()) {
            resultado = resultado.stream().filter(item -> item.getDescricao() != null &&
                item.getDescricao().toLowerCase().contains(descricao.toLowerCase()))
                .collect(java.util.stream.Collectors.toList());
        }
        if (contaBancariaId != null) {
            resultado = resultado.stream().filter(item -> contaBancariaId.equals(item.getContaBancariaId())).collect(java.util.stream.Collectors.toList());
        }
        return resultado;
    }

    @Operation(summary = "Buscar Lancamento por ID")
    @GetMapping("/{id}")
    public LancamentoResponseDTO buscar(@PathVariable Long id) { return service.buscar(id); }

    @Operation(summary = "Criar Lancamento")
    @PostMapping
    public ResponseEntity<LancamentoResponseDTO> criar(@Valid @RequestBody LancamentoRequestDTO lancamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(lancamento));
    }

    @Operation(summary = "Atualizar Lancamento")
    @PutMapping("/{id}")
    public LancamentoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody LancamentoRequestDTO lancamento) {
        return service.atualizar(id, lancamento);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Excluir Lancamento")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
