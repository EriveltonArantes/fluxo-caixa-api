package com.fluxocaixaapi.mapper;

import com.fluxocaixaapi.dto.ContaBancariaRequestDTO;
import com.fluxocaixaapi.dto.ContaBancariaResponseDTO;
import com.fluxocaixaapi.model.ContaBancaria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContaBancariaMapper {

    ContaBancaria toEntity(ContaBancariaRequestDTO dto);

    ContaBancariaResponseDTO toResponseDTO(ContaBancaria entity);
}
