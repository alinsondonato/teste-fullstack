package br.com.alinson.testeunimed.config.security;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.alinson.testeunimed.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

    @Value("${jwt.validade}")
    private String validade;

    @Value("${jwt.client-id}")
    private String clientId;

    public String gerarToken(Usuario usuario){
        LocalDateTime dhExpiracao = LocalDateTime.now().plusMinutes(Long.valueOf(validade));
        Date dataExpiracao = Date.from(dhExpiracao.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts
                .builder()
                .setSubject(usuario.getLogin())
                .setExpiration(dataExpiracao)
                .signWith( SignatureAlgorithm.HS512, clientId )
                .compact();
    }

    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                 .parser()
                 .setSigningKey(clientId)
                 .parseClaimsJws(token)
                 .getBody();
    }

    public boolean isTokenValido( String token ){
        try{
            Claims claims = obterClaims(token);
            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            
            return LocalDateTime.now().isBefore(data);
        }catch (Exception e){
            return false;
        }
    }

    public String obterUsuarioLogado(String token) throws ExpiredJwtException{
        return (String) obterClaims(token).getSubject();
    }
}