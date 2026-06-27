package com.fluxocaixaapi.controller;

import com.fluxocaixaapi.model.ContaBancaria;
import com.fluxocaixaapi.model.Lancamento;
import com.fluxocaixaapi.model.Transferencia;
import com.fluxocaixaapi.model.PrevisaoFinanceira;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Tag(name = "Dashboard", description = "KPIs e totais do sistema")
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private com.fluxocaixaapi.repository.ContaBancariaRepository contaBancariaRepository;

    @Autowired
    private com.fluxocaixaapi.repository.LancamentoRepository lancamentoRepository;

    @Autowired
    private com.fluxocaixaapi.repository.TransferenciaRepository transferenciaRepository;

    @Autowired
    private com.fluxocaixaapi.repository.PrevisaoFinanceiraRepository previsaoFinanceiraRepository;

    @Operation(summary = "Resumo com totais, somas e gráficos de status")
    @Transactional(readOnly = true)
    @GetMapping("/resumo")
    public Map<String, Object> resumo() {
        Map<String, Object> resumo = new LinkedHashMap<>();
        resumo.put("totalContaBancaria", contaBancariaRepository.count());
        resumo.put("somaSaldoInicialContaBancaria", contaBancariaRepository.findAll().stream().filter(e -> e.getSaldoInicial() != null).mapToDouble(e -> e.getSaldoInicial()).sum());
        resumo.put("totalLancamento", lancamentoRepository.count());
        resumo.put("somaValorLancamento", lancamentoRepository.findAll().stream().map(e -> e.getValor()).filter(v -> v != null).reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add));
        resumo.put("graficoLancamento", lancamentoRepository.findAll().stream()
            .collect(java.util.stream.Collectors.groupingBy(
                item -> String.valueOf(item.getStatus()),
                java.util.LinkedHashMap::new,
                java.util.stream.Collectors.counting())));
        resumo.put("totalTransferencia", transferenciaRepository.count());
        resumo.put("somaValorTransferencia", transferenciaRepository.findAll().stream().map(e -> e.getValor()).filter(v -> v != null).reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add));
        resumo.put("graficoTransferencia", transferenciaRepository.findAll().stream()
            .collect(java.util.stream.Collectors.groupingBy(
                item -> String.valueOf(item.getStatus()),
                java.util.LinkedHashMap::new,
                java.util.stream.Collectors.counting())));
        resumo.put("totalPrevisaoFinanceira", previsaoFinanceiraRepository.count());
        resumo.put("somaValorPrevistoPrevisaoFinanceira", previsaoFinanceiraRepository.findAll().stream().filter(e -> e.getValorPrevisto() != null).mapToDouble(e -> e.getValorPrevisto()).sum());
        resumo.put("graficoPrevisaoFinanceira", previsaoFinanceiraRepository.findAll().stream()
            .collect(java.util.stream.Collectors.groupingBy(
                item -> String.valueOf(item.getStatus()),
                java.util.LinkedHashMap::new,
                java.util.stream.Collectors.counting())));
        return resumo;
    }
}
