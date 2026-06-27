package com.fluxocaixaapi.dto;

import jakarta.validation.constraints.*;

public class TransferenciaRequestDTO {

    @NotNull(message = "ContaOrigemId é obrigatório")
    @Positive(message = "ContaOrigemId deve ser um ID válido (positivo)")
    private Long contaOrigemId;
    @NotNull(message = "ContaDestinoId é obrigatório")
    @Positive(message = "ContaDestinoId deve ser um ID válido (positivo)")
    private Long contaDestinoId;
    @NotNull(message = "data não pode ser nulo")
    private java.time.LocalDateTime data;
    @DecimalMin(value = "0.0", message = "valor não pode ser negativo")
    @NotNull(message = "valor não pode ser nulo")
    private java.math.BigDecimal valor;
    @NotBlank(message = "descricao não pode estar em branco")
    private String descricao;
    @NotBlank(message = "status não pode estar em branco")
    private String status;

    public Long getContaOrigemId() { return contaOrigemId; }
    public void setContaOrigemId(Long contaOrigemId) { this.contaOrigemId = contaOrigemId; }
    public Long getContaDestinoId() { return contaDestinoId; }
    public void setContaDestinoId(Long contaDestinoId) { this.contaDestinoId = contaDestinoId; }
    public java.time.LocalDateTime getData() { return data; }
    public void setData(java.time.LocalDateTime data) { this.data = data; }
    public java.math.BigDecimal getValor() { return valor; }
    public void setValor(java.math.BigDecimal valor) { this.valor = valor; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
