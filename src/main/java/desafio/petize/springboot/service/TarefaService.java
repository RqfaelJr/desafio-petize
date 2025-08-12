package desafio.petize.springboot.service;

import desafio.petize.springboot.domain.tarefa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public DadosDetalhamentoTarefa adicionar(DadosCadastroTarefa dados) {
        Tarefa tarefa = new Tarefa(dados);
        tarefaRepository.save(tarefa);
        return new DadosDetalhamentoTarefa(tarefa);
    }

    public DadosDetalhamentoTarefa atualizar(DadosAtualizacaoTarefa dados) {
        var tarefa = tarefaRepository.findById(dados.id()).orElse(null);
        if (tarefa == null || tarefa.getStatus() == Status.DELETADO) {
            throw new TarefaNaoEncontradaException("A tarefa não foi encontrada");
        }

        if (dados.status() == Status.CONCLUIDO) {
            boolean pendentes = tarefa.getSubtarefas().stream().anyMatch(status -> !(Status.CONCLUIDO == status.getStatus()));
            if (pendentes) {
                throw new IllegalStateException("Não é possível concluir uma tarefa com subtarefas pendentes!");
            }
        }

        tarefa.setStatus(dados.status());
        return new DadosDetalhamentoTarefa(tarefa);
    }

    public void deletar(Long id) {
        var tarefa = tarefaRepository.findById(id).orElse(null);
        if (tarefa == null) {
            throw new TarefaNaoEncontradaException("A tarefa não foi encontrada");
        }
        tarefa.setStatus(Status.DELETADO);
    }

    public Page<DadosListagemTarefa> filtrar(Status status, Prioridade prioridade, LocalDate dataVencimento, Pageable pageable) {
        return tarefaRepository.findByFiltros(status, prioridade, dataVencimento, pageable);
    }

    public DadosDetalhamentoTarefa adicionarSubtarefa(Long id, DadosCadastroTarefa dados) {
        Tarefa tarefaPai = tarefaRepository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa pai não encontrada"));
        Tarefa subtarefa = new Tarefa(dados);
        subtarefa.setTarefaPai(tarefaPai);
        tarefaRepository.save(subtarefa);

        return new DadosDetalhamentoTarefa(subtarefa);
    }
}
