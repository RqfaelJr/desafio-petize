package desafio.petize.springboot.domain.tarefa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {


    @Query("""
    SELECT new desafio.petize.springboot.domain.tarefa.DadosListagemTarefa(
            t.titulo, t.descricao, t.dataVencimento, t.status, t.prioridade
    ) FROM Tarefa t
    WHERE (:status IS NULL OR t.status = :status)
    AND (:prioridade IS NULL OR t.prioridade = :prioridade)
    AND (:vencimento IS NULL OR t.dataVencimento = :dataVencimento)
""")
    Page<DadosListagemTarefa> findByFiltros(
            @Param("status") Status status,
            @Param("prioridade") Prioridade prioridade,
            @Param("data_vencimento") LocalDate dataVencimento,
            Pageable pageable
    );
}
