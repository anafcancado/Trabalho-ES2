package com.laura.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Usuario cliente;

   
    @ElementCollection
    private List<String> itens = new ArrayList<>();

    private double valorTotal;
    
    private String metodoPagamento;
    
    private String endereco;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date tempoEstimadoEntrega;

    // --- Métodos de negócios ---

    public void calcularTotal() {
        
        this.valorTotal = itens.size() * 10.0; 
    }

    public void adicionarItem(String item) {
        itens.add(item);
        calcularTotal();
    }

    public void removerItem(String item) {
        itens.remove(item);
        calcularTotal();
    }

  
    public Date getTempoEstimadoEntrega() {
        return tempoEstimadoEntrega;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente.getUsername() +
                ", valorTotal=" + valorTotal +
                ", metodoPagamento='" + metodoPagamento + '\'' +
                '}';
    }

    // --- Getters e Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }


    public List<String> getItens() {
        return itens;
    }

    public void setItens(List<String> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setTempoEstimadoEntrega(Date tempoEstimadoEntrega) {
        this.tempoEstimadoEntrega = tempoEstimadoEntrega;
    }

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}