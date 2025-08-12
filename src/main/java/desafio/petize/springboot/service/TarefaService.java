package desafio.petize.springboot.service;

import desafio.petize.springboot.domain.specification.TarefaSpecification;
import desafio.petize.springboot.domain.tarefa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
        var tarefa = buscar(dados.id());

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
        var tarefa = buscar(id);
        deletarSubtarefas(tarefa);
    }

    private void deletarSubtarefas(Tarefa tarefa) {
        tarefa.setStatus(Status.DELETADO);

        if (tarefa.getSubtarefas() != null) {
            for (Tarefa subtarefa : tarefa.getSubtarefas()) {
                deletarSubtarefas(subtarefa);
            }
        }
    }

    public Page<DadosListagemTarefa> filtrar(Status status, Prioridade prioridade, LocalDate dataVencimento, Pageable pageable) {
        Specification<Tarefa> specification = Specification.where(TarefaSpecification.naoDeletado())
                .and((TarefaSpecification.comStatus(status)))
                .and(TarefaSpecification.comPrioridade(prioridade))
                .and(TarefaSpecification.comDataVencimento(dataVencimento));
        var page = tarefaRepository.findAll(specification, pageable);

        return page.map(DadosListagemTarefa::new);
    }

    public DadosDetalhamentoTarefa adicionarSubtarefa(Long id, DadosCadastroTarefa dados) {
        Tarefa tarefaPai = buscar(id);
        Tarefa subtarefa = new Tarefa(dados);
        subtarefa.setTarefaPai(tarefaPai);
        tarefaRepository.save(subtarefa);

        return new DadosDetalhamentoTarefa(subtarefa);
    }

    private Tarefa buscar(Long id) {
        return tarefaRepository.findByIdAndStatusNot(id, Status.DELETADO).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }
}
