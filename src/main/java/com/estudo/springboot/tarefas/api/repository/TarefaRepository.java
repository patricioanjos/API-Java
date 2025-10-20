package com.estudo.springboot.tarefas.api.repository;

import com.estudo.springboot.tarefas.api.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Anotação opcional, mas boa prática para indicar um bean de repositório
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    // A interface JpaRepository já nos fornece todos os métodos CRUD básicos:
    // save(), findAll(), findById(), deleteById(), etc.
    // O <Tarefa, Long> indica que este repositório gerencia a entidade Tarefa,
    // cuja chave primária é do tipo Long.
}
