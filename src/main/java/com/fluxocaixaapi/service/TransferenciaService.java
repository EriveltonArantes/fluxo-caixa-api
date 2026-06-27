package com.fluxocaixaapi.service;

import com.fluxocaixaapi.dto.TransferenciaRequestDTO;
import com.fluxocaixaapi.dto.TransferenciaResponseDTO;
import com.fluxocaixaapi.exception.ResourceNotFoundException;
import com.fluxocaixaapi.mapper.TransferenciaMapper;
import com.fluxocaixaapi.model.Transferencia;
import com.fluxocaixaapi.repository.TransferenciaRepository;
import com.fluxocaixaapi.repository.ContaBancariaRepository;
import com.fluxocaixaapi.model.ContaBancaria;
import com.fluxocaixaapi.repository.ContaBancariaRepository;
import com.fluxocaixaapi.model.ContaBancaria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository repository;

    @Autowired
    private TransferenciaMapper mapper;

    @Autowired
    private ContaBancariaRepository contaOrigemRepository;

    @Autowired
    private ContaBancariaRepository contaDestinoRepository;

    @Transactional(readOnly = true)
    public List<TransferenciaResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TransferenciaResponseDTO buscar(Long id) {
        Transferencia entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Transferencia não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public TransferenciaResponseDTO criar(TransferenciaRequestDTO dto) {
        Transferencia entity = mapper.toEntity(dto);
        ContaBancaria contaOrigem = contaOrigemRepository.findById(dto.getContaOrigemId())
            .orElseThrow(() -> new ResourceNotFoundException("ContaBancaria não encontrado com id: " + dto.getContaOrigemId()));
        entity.setContaOrigem(contaOrigem);
        ContaBancaria contaDestino = contaDestinoRepository.findById(dto.getContaDestinoId())
            .orElseThrow(() -> new ResourceNotFoundException("ContaBancaria não encontrado com id: " + dto.getContaDestinoId()));
        entity.setContaDestino(contaDestino);
        Transferencia salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public TransferenciaResponseDTO atualizar(Long id, TransferenciaRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Transferencia não encontrado com id: " + id);
        }
        Transferencia entity = mapper.toEntity(dto);
        entity.setId(id);
        ContaBancaria contaOrigem = contaOrigemRepository.findById(dto.getContaOrigemId())
            .orElseThrow(() -> new ResourceNotFoundException("ContaBancaria não encontrado com id: " + dto.getContaOrigemId()));
        entity.setContaOrigem(contaOrigem);
        ContaBancaria contaDestino = contaDestinoRepository.findById(dto.getContaDestinoId())
            .orElseThrow(() -> new ResourceNotFoundException("ContaBancaria não encontrado com id: " + dto.getContaDestinoId()));
        entity.setContaDestino(contaDestino);
        Transferencia salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Transferencia não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
