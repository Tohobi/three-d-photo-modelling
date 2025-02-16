package de.bht.paf.tobi.three.d.photo.modelling.repository;

import de.bht.paf.tobi.three.d.photo.modelling.model.Project;
import de.bht.paf.tobi.three.d.photo.modelling.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByOwner(User owner);
    List<Project> findByOwnerNot(User owner);
}