package com.fluxocaixaapi.service;

import com.fluxocaixaapi.dto.LancamentoRequestDTO;
import com.fluxocaixaapi.dto.LancamentoResponseDTO;
import com.fluxocaixaapi.exception.ResourceNotFoundException;
import com.fluxocaixaapi.mapper.LancamentoMapper;
import com.fluxocaixaapi.model.Lancamento;
import com.fluxocaixaapi.repository.LancamentoRepository;
import com.fluxocaixaapi.repository.ContaBancariaRepository;
import com.fluxocaixaapi.model.ContaBancaria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LancamentoService {

    @Autowired
    private LancamentoRepository repository;

    @Autowired
    private LancamentoMapper mapper;

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Transactional(readOnly = true)
    public List<LancamentoResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LancamentoResponseDTO buscar(Long id) {
        Lancamento entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Lancamento não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public LancamentoResponseDTO criar(LancamentoRequestDTO dto) {
        Lancamento entity = mapper.toEntity(dto);
        ContaBancaria contaBancaria = contaBancariaRepository.findById(dto.getContaBancariaId())
            .orElseThrow(() -> new ResourceNotFoundException("ContaBancaria não encontrado com id: " + dto.getContaBancariaId()));
        entity.setContaBancaria(contaBancaria);
        Lancamento salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public LancamentoResponseDTO atualizar(Long id, LancamentoRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Lancamento não encontrado com id: " + id);
        }
        Lancamento entity = mapper.toEntity(dto);
        entity.setId(id);
        ContaBancaria contaBancaria = contaBancariaRepository.findById(dto.getContaBancariaId())
            .orElseThrow(() -> new ResourceNotFoundException("ContaBancaria não encontrado com id: " + dto.getContaBancariaId()));
        entity.setContaBancaria(contaBancaria);
        Lancamento salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Lancamento não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
