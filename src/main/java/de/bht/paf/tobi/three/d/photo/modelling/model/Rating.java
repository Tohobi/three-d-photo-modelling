package de.bht.paf.tobi.three.d.photo.modelling.model;

import jakarta.persistence.*;

@Entity
@Table(name = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer score;

    private String comment;

    private String modelReference;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getModelReference() {
        return modelReference;
    }

    public void setModelReference(String modelReference) {
        this.modelReference = modelReference;
    }
}
