package desafio.petize.springboot.service;

import desafio.petize.springboot.domain.usuario.DadosAutenticacaoUsuario;
import desafio.petize.springboot.domain.usuario.DadosDetalhamentoUsuario;
import desafio.petize.springboot.domain.usuario.Usuario;
import desafio.petize.springboot.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public DadosDetalhamentoUsuario cadastrar(DadosAutenticacaoUsuario dados) {
        Usuario usuario = new Usuario(dados);
        usuarioRepository.save(usuario);
        return new DadosDetalhamentoUsuario(usuario);
    }
}
