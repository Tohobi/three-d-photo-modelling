package de.bht.paf.tobi.three.d.photo.modelling.service;

import de.bht.paf.tobi.three.d.photo.modelling.model.Project;
import de.bht.paf.tobi.three.d.photo.modelling.model.Rating;
import de.bht.paf.tobi.three.d.photo.modelling.model.User;
import de.bht.paf.tobi.three.d.photo.modelling.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public boolean hasUserAlreadyRated(User user, Project project) {
        return ratingRepository.findByUserAndProject(user, project).isPresent();
    }
}
