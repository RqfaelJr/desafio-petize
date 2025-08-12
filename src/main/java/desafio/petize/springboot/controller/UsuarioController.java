package desafio.petize.springboot.controller;

import desafio.petize.springboot.domain.usuario.DadosAutenticacaoUsuario;
import desafio.petize.springboot.domain.usuario.DadosDetalhamentoUsuario;
import desafio.petize.springboot.domain.usuario.Usuario;
import desafio.petize.springboot.infra.security.DadosTokenJWT;
import desafio.petize.springboot.service.TokenService;
import desafio.petize.springboot.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/cadastro")
    @Operation(summary = "Cadastro de novos usuários")
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrar(@RequestBody @Valid DadosAutenticacaoUsuario dados, UriComponentsBuilder uriBuilder) {
        var dto = usuarioService.cadastrar(dados);
        var uri = uriBuilder.path("/{id}").buildAndExpand(dto.id()).toUri();

        return  ResponseEntity.created(uri).body(dto);
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuário")
    public ResponseEntity<DadosTokenJWT> login(@RequestBody @Valid DadosAutenticacaoUsuario dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.username(), dados.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
