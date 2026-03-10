package br.com.alura.forum_hub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Garanta que o retorno é 'UserDetails' para o Spring Security entender
    UserDetails findByLogin(String login);
}