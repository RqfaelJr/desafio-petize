package desafio.petize.springboot.domain.tarefa;

import java.util.Date;

public record DadosDetalhamentoTarefa(Long id, String titulo, String descricao, Date dataVencimento, Status status, Prioridade prioridade) {
    public DadosDetalhamentoTarefa(Tarefa tarefa) {
        this(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataVencimento(), tarefa.getStatus(), tarefa.getPrioridade());
    }
}
