package desafio.petize.springboot.domain.tarefa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tarefa {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String descricao;

    private Date dataVencimento;

    @Setter
    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Setter
    @ManyToOne
    @JoinColumn(name = "tarefa_pai_id")
    private Tarefa tarefaPai;

    @OneToMany(mappedBy = "tarefaPai", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> subtarefas = new ArrayList<>();

    public Tarefa(DadosCadastroTarefa dados) {
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.dataVencimento = dados.dataVencimento();
        this.status = dados.status();
        this.prioridade = dados.prioridade();
    }

}
