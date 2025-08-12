package desafio.petize.springboot.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacaoUsuario(

        @NotBlank(message = "O campo username não pode estar em branco")
        String username,

        @NotBlank(message = "O campo password não pode estar em branco")
        String password
) {
}
