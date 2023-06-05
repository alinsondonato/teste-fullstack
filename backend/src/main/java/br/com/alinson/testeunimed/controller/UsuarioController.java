package br.com.alinson.testeunimed.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.alinson.testeunimed.config.security.JwtService;
import br.com.alinson.testeunimed.dto.TokenDTO;
import br.com.alinson.testeunimed.dto.UserDTO;
import br.com.alinson.testeunimed.exception.SenhaInvalidaException;
import br.com.alinson.testeunimed.model.Usuario;
import br.com.alinson.testeunimed.service.impl.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UsuarioController {
	
	private final UserService userService;
    private final JwtService jwtService;

	@PostMapping("/login/auth")
    public TokenDTO login(@RequestBody UserDTO user){
        try{
            Usuario usuario = Usuario.builder()
                    .login(user.getLogin())
                    .senha(user.getSenha()).build();
            UserDetails usuarioLogado = userService.login(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
	
}
