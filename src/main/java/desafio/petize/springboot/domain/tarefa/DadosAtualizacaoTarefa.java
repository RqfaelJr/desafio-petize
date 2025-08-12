package desafio.petize.springboot.domain.tarefa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTarefa(

        @NotNull(message = "O id da tarefa precisa ser indicado")
        Long id,

        @NotBlank(message = "O campo status não pode estar em branco")
        Status status
) {
}
