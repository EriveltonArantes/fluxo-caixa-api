package com.fluxocaixaapi.dto;

import jakarta.validation.constraints.*;

public class PrevisaoFinanceiraRequestDTO {

    @NotBlank(message = "mes não pode estar em branco")
    private String mes;
    @Min(value = 0, message = "ano não pode ser negativo")
    @NotNull(message = "ano não pode ser nulo")
    private Integer ano;
    @NotBlank(message = "categoria não pode estar em branco")
    private String categoria;
    @NotBlank(message = "tipo não pode estar em branco")
    private String tipo;
    @DecimalMin(value = "0.0", message = "valor previsto não pode ser negativo")
    @NotNull(message = "valor previsto não pode ser nulo")
    private Double valorPrevisto;
    @DecimalMin(value = "0.0", message = "valor realizado não pode ser negativo")
    @NotNull(message = "valor realizado não pode ser nulo")
    private Double valorRealizado;
    @NotBlank(message = "status não pode estar em branco")
    private String status;

    public String getMes() { return mes; }
    public void setMes(String mes) { this.mes = mes; }
    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Double getValorPrevisto() { return valorPrevisto; }
    public void setValorPrevisto(Double valorPrevisto) { this.valorPrevisto = valorPrevisto; }
    public Double getValorRealizado() { return valorRealizado; }
    public void setValorRealizado(Double valorRealizado) { this.valorRealizado = valorRealizado; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
