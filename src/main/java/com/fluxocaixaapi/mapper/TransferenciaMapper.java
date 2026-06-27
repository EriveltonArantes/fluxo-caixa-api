package com.fluxocaixaapi.mapper;

import com.fluxocaixaapi.dto.TransferenciaRequestDTO;
import com.fluxocaixaapi.dto.TransferenciaResponseDTO;
import com.fluxocaixaapi.model.Transferencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferenciaMapper {

    @Mapping(target = "contaOrigem", ignore = true)
    @Mapping(target = "contaDestino", ignore = true)
    Transferencia toEntity(TransferenciaRequestDTO dto);

    @Mapping(target = "contaOrigemId", source = "contaOrigem.id")
    @Mapping(target = "contaDestinoId", source = "contaDestino.id")
    TransferenciaResponseDTO toResponseDTO(Transferencia entity);
}
