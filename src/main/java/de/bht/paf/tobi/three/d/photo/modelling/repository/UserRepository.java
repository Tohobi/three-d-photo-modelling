package de.bht.paf.tobi.three.d.photo.modelling.repository;

import de.bht.paf.tobi.three.d.photo.modelling.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}