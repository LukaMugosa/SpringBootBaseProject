package me.amplitudo.stanley.repository;

import me.amplitudo.stanley.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findOneWithAuthoritiesByEmailIgnoreCase(String login);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findOneWithAuthoritiesByUsername(String login);

    boolean existsByEmail(String email);

}
