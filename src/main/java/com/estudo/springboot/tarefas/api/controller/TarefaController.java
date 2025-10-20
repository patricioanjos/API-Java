package com.estudo.springboot.tarefas.api.controller;

import com.estudo.springboot.tarefas.api.model.Tarefa;
import com.estudo.springboot.tarefas.api.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Combina @Controller e @ResponseBody, simplificando a criação de APIs REST
@RequestMapping("/tarefas") // Define a URL base para todos os endpoints deste controller
public class TarefaController {

    @Autowired // Injeção de dependência: o Spring fornecerá uma instância de TarefaRepository
    private TarefaRepository tarefaRepository;

    // 1. Criar uma nova tarefa
    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = tarefaRepository.save(tarefa);
        return new ResponseEntity<>(novaTarefa, HttpStatus.CREATED); // Retorna 201 Created
    }

    // 2. Consultar todas as tarefas
    @GetMapping
    public List<Tarefa> listarTarefas() {
        return tarefaRepository.findAll();
    }

    // 3. Consultar uma tarefa específica pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        return tarefa.map(ResponseEntity::ok) // Se encontrar, retorna 200 OK com a tarefa
                     .orElseGet(() -> ResponseEntity.notFound().build()); // Senão, retorna 404 Not Found
    }

    // 4. Atualizar uma tarefa existente
    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tarefaDetalhes) {
        return tarefaRepository.findById(id)
                .map(tarefaExistente -> {
                    tarefaExistente.setNome(tarefaDetalhes.getNome());
                    tarefaExistente.setDataEntrega(tarefaDetalhes.getDataEntrega());
                    tarefaExistente.setResponsavel(tarefaDetalhes.getResponsavel());
                    Tarefa tarefaAtualizada = tarefaRepository.save(tarefaExistente);
                    return ResponseEntity.ok(tarefaAtualizada);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 5. Remover uma tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerTarefa(@PathVariable Long id) {
        if (!tarefaRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // Retorna 404 se a tarefa não existir
        }
        tarefaRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content, indicando sucesso na remoção
    }
}
