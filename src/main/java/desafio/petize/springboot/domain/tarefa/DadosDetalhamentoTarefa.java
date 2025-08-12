package desafio.petize.springboot.domain.tarefa;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record DadosDetalhamentoTarefa(Long id, String titulo, String descricao, LocalDate dataVencimento, Status status, Prioridade prioridade, Tarefa tarefaPai, List<Tarefa> subtarefas) {
    public DadosDetalhamentoTarefa(Tarefa tarefa) {
        this(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataVencimento(), tarefa.getStatus(), tarefa.getPrioridade(), tarefa.getTarefaPai(), tarefa.getSubtarefas());
    }
}
