package com.fluxocaixaapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "lancamentos")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "contaBancaria_id")
    private ContaBancaria contaBancaria;
    private java.time.LocalDateTime data;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private String categoria;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String descricao;
    private java.math.BigDecimal valor;
    @Column(nullable = false)
    private String status;
    @Column(nullable = false)
    private String documento;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ContaBancaria getContaBancaria() { return contaBancaria; }
    public void setContaBancaria(ContaBancaria contaBancaria) { this.contaBancaria = contaBancaria; }
    public java.time.LocalDateTime getData() { return data; }
    public void setData(java.time.LocalDateTime data) { this.data = data; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public java.math.BigDecimal getValor() { return valor; }
    public void setValor(java.math.BigDecimal valor) { this.valor = valor; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
}
