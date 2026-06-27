package com.fluxocaixaapi.mapper;

import com.fluxocaixaapi.dto.PrevisaoFinanceiraRequestDTO;
import com.fluxocaixaapi.dto.PrevisaoFinanceiraResponseDTO;
import com.fluxocaixaapi.model.PrevisaoFinanceira;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrevisaoFinanceiraMapper {

    PrevisaoFinanceira toEntity(PrevisaoFinanceiraRequestDTO dto);

    PrevisaoFinanceiraResponseDTO toResponseDTO(PrevisaoFinanceira entity);
}
