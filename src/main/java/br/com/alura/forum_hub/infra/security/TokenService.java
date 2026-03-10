package br.com.alura.forum_hub.infra.security;

import br.com.alura.forum_hub.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service // Diz ao Spring que esta é uma classe de serviço
public class TokenService {

    // Essa anotação vai ler a senha que colocaremos no application.properties
    @Value("${api.security.token.secret}")
    private String secret;

    // MÉTODO PARA CRIAR O TOKEN (O "Crachá")
    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Forum Hub") // Identifica quem criou o token
                    .withSubject(usuario.getLogin()) // Salva o login do usuário dentro do token
                    .withExpiresAt(dataExpiracao()) // Define quando o token vence
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token jwt", exception);
        }
    }

    // MÉTODO PARA LER O TOKEN (Ver se o crachá é verdadeiro)
    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("API Forum Hub")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    // Configura o token para vencer em 2 horas
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}