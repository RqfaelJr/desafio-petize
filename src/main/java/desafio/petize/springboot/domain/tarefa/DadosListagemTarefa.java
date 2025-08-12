package desafio.petize.springboot.domain.tarefa;

import java.time.LocalDate;
import java.util.Date;

public record DadosListagemTarefa(String titulo, String descricao, LocalDate dataVencimento, Status status, Prioridade prioridade) {
    public DadosListagemTarefa(Tarefa tarefa) {
        this(tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataVencimento(), tarefa.getStatus(), tarefa.getPrioridade());
    }
}
