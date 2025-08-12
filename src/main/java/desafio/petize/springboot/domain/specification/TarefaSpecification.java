package desafio.petize.springboot.domain.specification;

import desafio.petize.springboot.domain.tarefa.Prioridade;
import desafio.petize.springboot.domain.tarefa.Status;
import desafio.petize.springboot.domain.tarefa.Tarefa;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class TarefaSpecification {

    public static Specification<Tarefa> doUsuario(Long id) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("usuario").get("id"), id);
    }

    public static Specification<Tarefa> naoDeletado() {
        return (root, query, cb) -> cb.notEqual(root.get("status"), Status.DELETADO);
    }

    public static Specification<Tarefa> comStatus(Status status) {
        return (root, query, criteriaBuilder) -> status == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("status"), status);
    }

    public static Specification<Tarefa> comPrioridade(Prioridade prioridade) {
        return (root, query, criteriaBuilder) -> prioridade == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("prioridade"), prioridade);
    }

    public static Specification<Tarefa> comDataVencimento(LocalDate dataVencimento) {
        return (root, query, criteriaBuilder) -> dataVencimento == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("dataVencimento"), dataVencimento);
    }
}
