package desafio.petize.springboot.domain.tarefa;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record DadosCadastroTarefa(

        @NotBlank(message = "O campo título não pode estar em branco!")
        String titulo,

        @NotBlank(message = "O campo descrição não pode estar em branco!")
        String descricao,

        @Future(message = "A data de vencimento não pode ser no passado!")
        @NotNull(message = "O campo data de vencimento não pode estar em branco!")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataVencimento,

        @NotNull(message = "O campo status não pode estar em branco!")
        Status status,

        @NotNull(message = "O campo prioridade não pode estar em branco!")
        Prioridade prioridade
) {
}
