package com.fluxocaixaapi.dto;

import jakarta.validation.constraints.*;

public class ContaBancariaRequestDTO {

    @NotBlank(message = "banco não pode estar em branco")
    private String banco;
    @NotBlank(message = "agencia não pode estar em branco")
    private String agencia;
    @NotBlank(message = "conta não pode estar em branco")
    private String conta;
    @NotBlank(message = "tipo não pode estar em branco")
    private String tipo;
    @NotNull(message = "saldo inicial não pode ser nulo")
    private Double saldoInicial;
    @NotNull(message = "limite não pode ser nulo")
    private Double limite;
    @NotNull(message = "ativa não pode ser nulo")
    private Boolean ativa;

    public String getBanco() { return banco; }
    public void setBanco(String banco) { this.banco = banco; }
    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }
    public String getConta() { return conta; }
    public void setConta(String conta) { this.conta = conta; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Double getSaldoInicial() { return saldoInicial; }
    public void setSaldoInicial(Double saldoInicial) { this.saldoInicial = saldoInicial; }
    public Double getLimite() { return limite; }
    public void setLimite(Double limite) { this.limite = limite; }
    public Boolean getAtiva() { return ativa; }
    public void setAtiva(Boolean ativa) { this.ativa = ativa; }
}
