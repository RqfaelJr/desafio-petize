package desafio.petize.springboot.domain.usuario;

public record DadosDetalhamentoUsuario(Long id, String username, String password) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getUsername(), usuario.getPassword());
    }
}
