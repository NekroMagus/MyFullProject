package net.skideo.dto;

public class GetRatingDto {

    private int rating;

    public GetRatingDto() {}

    public GetRatingDto(int rating) {
        this.rating=rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
