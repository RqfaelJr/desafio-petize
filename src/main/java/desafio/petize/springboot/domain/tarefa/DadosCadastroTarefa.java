package desafio.petize.springboot.domain.tarefa;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record DadosCadastroTarefa(

        @NotBlank(message = "O campo título não pode estar em branco!")
        String titulo,

        @NotBlank(message = "O campo descrição não pode estar em branco!")
        String descricao,

        @Future(message = "A data de vencimento não pode ser no passado!")
        @NotBlank(message = "O campo data de vencimento não pode estar em branco!")
        Date dataVencimento,

        @NotBlank(message = "O campo status não pode estar em branco!")
        Status status,

        @NotBlank(message = "O campo prioridade não pode estar em branco!")
        Prioridade prioridade
) {
}
