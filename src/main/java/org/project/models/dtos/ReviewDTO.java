package org.project.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ReviewDTO {

    private Long id;

    @NotNull
    @Min(1)
    @Max(5)
    private float rating;

    private String comment = "";

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate creationDate;

    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate updateDate;

    public ReviewDTO() {}

    public ReviewDTO(float rating, String comment) {
        this.rating = rating;
        this.comment = comment;
        this.creationDate = LocalDate.now();
        this.updateDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return String.format("ReviewDTO [Rating: %.1f, Comment: %s]", rating, comment);
    }
}
