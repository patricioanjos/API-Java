// O Eclipse já deve ter colocado esta linha, verifique se está correta
package com.estudo.springboot.tarefas.api.model;

// Importações necessárias
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity // Indica ao JPA que esta classe é uma tabela no banco
public class Tarefa {

    @Id // Marca este campo como a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Pede ao banco para gerar o ID automaticamente
    private Long id;

    private String nome;
    private LocalDate dataEntrega;
    private String responsavel;

    // --- Getters e Setters ---
    // Você pode pedir ao Eclipse para gerar isso:
    // Clique com o botão direito no meio do código -> Source -> Generate Getters and Setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }
}