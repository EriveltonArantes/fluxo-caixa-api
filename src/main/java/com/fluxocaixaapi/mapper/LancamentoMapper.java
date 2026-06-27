package com.fluxocaixaapi.mapper;

import com.fluxocaixaapi.dto.LancamentoRequestDTO;
import com.fluxocaixaapi.dto.LancamentoResponseDTO;
import com.fluxocaixaapi.model.Lancamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LancamentoMapper {

    @Mapping(target = "contaBancaria", ignore = true)
    Lancamento toEntity(LancamentoRequestDTO dto);

    @Mapping(target = "contaBancariaId", source = "contaBancaria.id")
    LancamentoResponseDTO toResponseDTO(Lancamento entity);
}
