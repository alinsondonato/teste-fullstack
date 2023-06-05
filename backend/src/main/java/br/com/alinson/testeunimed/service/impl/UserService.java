package br.com.alinson.testeunimed.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alinson.testeunimed.exception.SenhaInvalidaException;
import br.com.alinson.testeunimed.model.Usuario;
import br.com.alinson.testeunimed.repository.UsuarioRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario salvar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    
    public UserDetails login( Usuario usuario ){
        UserDetails user = loadUserByUsername(usuario.getLogin());

        if(!encoder.matches(usuario.getSenha(), user.getPassword())){
        	throw new SenhaInvalidaException();
        }

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(usuario.getRoles().split(","))
                .build();
    }
}
