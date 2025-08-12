package desafio.petize.springboot.controller;

import desafio.petize.springboot.domain.tarefa.*;
import desafio.petize.springboot.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    @Transactional
    @Operation(summary = "Criar uma nova tarefa")
    public ResponseEntity<DadosDetalhamentoTarefa> adicionar(@RequestBody @Valid DadosCadastroTarefa dados, UriComponentsBuilder uriBuilder) {
        var dto = tarefaService.adicionar(dados);
        var uri = uriBuilder.path("/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Alterar o status de uma tarefa")
    public ResponseEntity<DadosDetalhamentoTarefa> atualizar(@RequestBody @Valid DadosAtualizacaoTarefa dados) {
        var dto = tarefaService.atualizar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Deletar uma tarefa")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tarefaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Listar tarefas com filtros")
    public ResponseEntity<Page<DadosListagemTarefa>> filtrar(@Schema(allowableValues = {"NAO_INICIADO", "EM_ANDAMENTO", "CONCLUIDO"}) @RequestParam(required = false) Status status,
                                                             @RequestParam(required = false) Prioridade prioridade,
                                                             @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataVencimento,
                                                             @PageableDefault(size = 5, sort = "dataVencimento") Pageable pageable) {
        var page = tarefaService.filtrar(status, prioridade, dataVencimento, pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping("/{id}/subtarefa")
    @Transactional
    @Operation(summary = "Criar uma nova subtarefa")
    public ResponseEntity<DadosDetalhamentoTarefa> adicionarSubtarefa(@PathVariable Long id, @RequestBody @Valid DadosCadastroTarefa dados, UriComponentsBuilder uriBuilder) {
        var dto = tarefaService.adicionarSubtarefa(id, dados);

        var uri = uriBuilder.path("/{id}").buildAndExpand(dto.id()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }
}
