package com.fluxocaixaapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "previsaofinanceiras")
public class PrevisaoFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mes;
    private Integer ano;
    @Column(nullable = false)
    private String categoria;
    @Column(nullable = false)
    private String tipo;
    private Double valorPrevisto;
    private Double valorRealizado;
    @Column(nullable = false)
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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
