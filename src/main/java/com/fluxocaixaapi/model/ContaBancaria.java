package com.fluxocaixaapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contabancarias")
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String banco;
    @Column(nullable = false)
    private String agencia;
    @Column(nullable = false)
    private String conta;
    @Column(nullable = false)
    private String tipo;
    private Double saldoInicial;
    private Double limite;
    private Boolean ativa;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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
