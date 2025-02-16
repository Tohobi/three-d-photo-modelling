package de.bht.paf.tobi.three.d.photo.modelling.repository;

import de.bht.paf.tobi.three.d.photo.modelling.model.Project;
import de.bht.paf.tobi.three.d.photo.modelling.model.Rating;
import de.bht.paf.tobi.three.d.photo.modelling.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    Optional<Rating> findByUserAndProject(User user, Project project);
}
