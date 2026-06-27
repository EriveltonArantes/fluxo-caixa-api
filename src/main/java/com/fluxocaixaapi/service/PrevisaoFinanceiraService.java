package com.fluxocaixaapi.service;

import com.fluxocaixaapi.dto.PrevisaoFinanceiraRequestDTO;
import com.fluxocaixaapi.dto.PrevisaoFinanceiraResponseDTO;
import com.fluxocaixaapi.exception.ResourceNotFoundException;
import com.fluxocaixaapi.mapper.PrevisaoFinanceiraMapper;
import com.fluxocaixaapi.model.PrevisaoFinanceira;
import com.fluxocaixaapi.repository.PrevisaoFinanceiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PrevisaoFinanceiraService {

    @Autowired
    private PrevisaoFinanceiraRepository repository;

    @Autowired
    private PrevisaoFinanceiraMapper mapper;

    @Transactional(readOnly = true)
    public List<PrevisaoFinanceiraResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PrevisaoFinanceiraResponseDTO buscar(Long id) {
        PrevisaoFinanceira entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("PrevisaoFinanceira não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public PrevisaoFinanceiraResponseDTO criar(PrevisaoFinanceiraRequestDTO dto) {
        PrevisaoFinanceira entity = mapper.toEntity(dto);
        PrevisaoFinanceira salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public PrevisaoFinanceiraResponseDTO atualizar(Long id, PrevisaoFinanceiraRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("PrevisaoFinanceira não encontrado com id: " + id);
        }
        PrevisaoFinanceira entity = mapper.toEntity(dto);
        entity.setId(id);
        PrevisaoFinanceira salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("PrevisaoFinanceira não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
