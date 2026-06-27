package com.fluxocaixaapi.service;

import com.fluxocaixaapi.dto.ContaBancariaRequestDTO;
import com.fluxocaixaapi.dto.ContaBancariaResponseDTO;
import com.fluxocaixaapi.exception.ResourceNotFoundException;
import com.fluxocaixaapi.mapper.ContaBancariaMapper;
import com.fluxocaixaapi.model.ContaBancaria;
import com.fluxocaixaapi.repository.ContaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContaBancariaService {

    @Autowired
    private ContaBancariaRepository repository;

    @Autowired
    private ContaBancariaMapper mapper;

    @Transactional(readOnly = true)
    public List<ContaBancariaResponseDTO> listar() {
        return repository.findAll().stream().map(mapper::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ContaBancariaResponseDTO buscar(Long id) {
        ContaBancaria entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("ContaBancaria não encontrado com id: " + id));
        return mapper.toResponseDTO(entity);
    }

    public ContaBancariaResponseDTO criar(ContaBancariaRequestDTO dto) {
        ContaBancaria entity = mapper.toEntity(dto);
        ContaBancaria salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public ContaBancariaResponseDTO atualizar(Long id, ContaBancariaRequestDTO dto) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("ContaBancaria não encontrado com id: " + id);
        }
        ContaBancaria entity = mapper.toEntity(dto);
        entity.setId(id);
        ContaBancaria salvo = repository.save(entity);
        return mapper.toResponseDTO(salvo);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("ContaBancaria não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
