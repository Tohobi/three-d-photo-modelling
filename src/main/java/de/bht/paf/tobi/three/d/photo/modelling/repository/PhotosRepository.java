package de.bht.paf.tobi.three.d.photo.modelling.repository;

import de.bht.paf.tobi.three.d.photo.modelling.model.Photo;
import org.springframework.data.repository.CrudRepository;

public interface PhotosRepository extends CrudRepository<Photo, Integer> {
}
