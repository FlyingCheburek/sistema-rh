package com.apprh.app_rh.models;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

public class Vaga implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id //ID da tabela 
    @GeneratedValue(strategy = GenerationType.AUTO) //AUTO_INCREMENT
    private long codigo;

    @NotEmpty //NOT_NULL
    private String nome;

    @NotEmpty
    private String descricao;

    @NotEmpty
    private String data;

    @NotEmpty
    private String salario;

    @OneToMany(mappedBy = "vaga", cascade = CascadeType.REMOVE) //relação UM-PARA-MUITOS entre tabela "Vaga" e "Candidato". Remove os "Candidato" caso a vaga seja excluída
    private List<Candidato>candidatos; 

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
    public long getCodigo() {
        return codigo;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getData() {
        return data;
    }
    public void setSalario(String salario) {
        this.salario = salario;
    }
    public String getSalario() {
        return salario;
    }
    public void setCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos;
    }
    public List<Candidato> getCandidatos() {
        return candidatos;
    }
}
